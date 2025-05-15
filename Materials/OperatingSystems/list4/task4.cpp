#include <iostream>
#include <vector>
#include <deque>
#include <unordered_set>
#include <unordered_map>
#include <map>
#include <algorithm>
#include <random>
#include <ctime>
#include <numeric>

using namespace std;

class Process {
public:
    int pid;
    int page_count;
    int ref_count;
    int locality_size;
    vector<int> refs;

    Process(int pid_, int page_count_, int ref_count_, int locality_size_) :
        pid(pid_), page_count(page_count_), ref_count(ref_count_) {
        locality_size = min(locality_size_, page_count_);
        generate_references();
    }

private:
    void generate_references() {
        random_device rd;
        mt19937 gen(rd());
        uniform_int_distribution<> page_dist(0, page_count - 1);

        int generated = 0;
        while (generated < ref_count) {
            int center = page_dist(gen);
            vector<int> window;
            for (int i = 0; i < locality_size; ++i) {
                window.push_back((center + i) % page_count);
            }
            for (int i = 0; i < locality_size && generated < ref_count; ++i) {
                uniform_int_distribution<> window_dist(0, window.size() - 1);
                refs.push_back(window[window_dist(gen)]);
                ++generated;
            }
        }
    }
};

vector<pair<int, int>> merge_references(const vector<Process>& processes) {
    vector<pair<int, int>> merged;
    size_t max_len = 0;
    for (const auto& p : processes) {
        max_len = max(max_len, p.refs.size());
    }
    for (size_t i = 0; i < max_len; ++i) {
        for (const auto& p : processes) {
            if (i < p.refs.size()) {
                merged.emplace_back(p.pid, p.refs[i]);
            }
        }
    }
    return merged;
}

int simulate_lru(int frames_alloc, const vector<int>& refs) {
    deque<int> cache;
    unordered_set<int> in_cache;
    int faults = 0;
    for (int page : refs) {
        if (in_cache.count(page)) {
            cache.erase(remove(cache.begin(), cache.end(), page), cache.end());
            cache.push_back(page);
        } else {
            ++faults;
            if (cache.size() < frames_alloc) {
                cache.push_back(page);
                in_cache.insert(page);
            } else {
                int lru = cache.front();
                cache.pop_front();
                in_cache.erase(lru);
                cache.push_back(page);
                in_cache.insert(page);
            }
        }
    }
    return faults;
}

pair<map<int, int>, map<int, int>> equal_allocation(const vector<Process>& processes, int total_frames, const vector<pair<int, int>>& merged_refs) {
    int n = processes.size();
    int base = total_frames / n;
    int extra = total_frames % n;
    map<int, int> alloc;
    for (int i = 0; i < n; ++i) {
        alloc[processes[i].pid] = base + (i < extra ? 1 : 0);
    }

    map<int, vector<int>> proc_refs;
    for (const auto& pr : merged_refs) {
        proc_refs[pr.first].push_back(pr.second);
    }

    map<int, int> faults;
    for (const auto& p : processes) {
        faults[p.pid] = simulate_lru(alloc[p.pid], proc_refs[p.pid]);
    }
    return {alloc, faults};
}

pair<map<int, int>, map<int, int>> proportional_allocation(const vector<Process>& processes, int total_frames, const vector<pair<int, int>>& merged_refs) {
    map<int, int> sizes;
    int total_size = 0;
    for (const auto& p : processes) {
        sizes[p.pid] = p.page_count;
        total_size += p.page_count;
    }

    map<int, int> alloc;
    int sum_alloc = 0;
    for (const auto& p : processes) {
        int f = (sizes[p.pid] * total_frames) / total_size;
        alloc[p.pid] = f;
        sum_alloc += f;
    }

    int leftover = total_frames - sum_alloc;
    vector<pair<int, int>> sorted_sizes;
    for (const auto& s : sizes) {
        sorted_sizes.emplace_back(s.second, s.first);
    }
    sort(sorted_sizes.rbegin(), sorted_sizes.rend());
    for (int i = 0; i < leftover && i < sorted_sizes.size(); ++i) {
        alloc[sorted_sizes[i].second] += 1;
    }

    map<int, vector<int>> proc_refs;
    for (const auto& pr : merged_refs) {
        proc_refs[pr.first].push_back(pr.second);
    }

    map<int, int> faults;
    for (const auto& p : processes) {
        faults[p.pid] = simulate_lru(alloc[p.pid], proc_refs[p.pid]);
    }
    return {alloc, faults};
}

pair<map<int, int>, map<int, int>> pff_allocation(const vector<Process>& processes, int total_frames, const vector<pair<int, int>>& merged_refs, int win, double high_th, double low_th) {
    int n = processes.size();
    map<int, int> alloc;
    for (const auto& p : processes) {
        alloc[p.pid] = total_frames / n;
    }
    int free_frames = total_frames - accumulate(alloc.begin(), alloc.end(), 0, [](int sum, const pair<int, int>& p) { return sum + p.second; });

    map<int, int> faults;
    map<int, deque<int>> last_k;
    map<int, deque<int>> proc_frames;
    map<int, unordered_set<int>> in_cache;

    for (const auto& pr : merged_refs) {
        int pid = pr.first;
        int page = pr.second;
        if (!in_cache[pid].count(page)) {
            ++faults[pid];
            last_k[pid].push_back(1);
            if (proc_frames[pid].size() < alloc[pid]) {
                proc_frames[pid].push_back(page);
                in_cache[pid].insert(page);
            } else {
                int lru = proc_frames[pid].front();
                proc_frames[pid].pop_front();
                in_cache[pid].erase(lru);
                proc_frames[pid].push_back(page);
                in_cache[pid].insert(page);
            }
        } else {
            proc_frames[pid].erase(remove(proc_frames[pid].begin(), proc_frames[pid].end(), page), proc_frames[pid].end());
            proc_frames[pid].push_back(page);
            last_k[pid].push_back(0);
        }

        if (last_k[pid].size() > win) {
            last_k[pid].pop_front();
        }

        if (last_k[pid].size() == win) {
            double rate = accumulate(last_k[pid].begin(), last_k[pid].end(), 0.0) / win;
            if (rate > high_th && free_frames > 0) {
                ++alloc[pid];
                --free_frames;
            } else if (rate < low_th && alloc[pid] > 1) {
                --alloc[pid];
                ++free_frames;
            }
        }
    }
    return {alloc, faults};
}

pair<map<int, int>, map<int, int>> working_set_allocation(const vector<Process>& processes, int total_frames, const vector<pair<int, int>>& merged_refs, int tau) {
    map<int, deque<int>> ws;
    map<int, int> alloc;
    map<int, int> faults;
    map<int, deque<int>> proc_frames;
    map<int, unordered_set<int>> in_cache;

    for (const auto& pr : merged_refs) {
        int pid = pr.first;
        int page = pr.second;
        ws[pid].push_back(page);
        if (ws[pid].size() > tau) {
            ws[pid].pop_front();
        }

        map<int, int> ws_sizes;
        int total_ws = 0;
        for (const auto& p : processes) {
            unordered_set<int> unique_pages(ws[p.pid].begin(), ws[p.pid].end());
            ws_sizes[p.pid] = unique_pages.size();
            total_ws += unique_pages.size();
        }

        for (const auto& p : processes) {
            alloc[p.pid] = max(1, (ws_sizes[p.pid] * total_frames) / max(1, total_ws));
        }

        if (!in_cache[pid].count(page)) {
            ++faults[pid];
            if (proc_frames[pid].size() < alloc[pid]) {
                proc_frames[pid].push_back(page);
                in_cache[pid].insert(page);
            } else {
                int lru = proc_frames[pid].front();
                proc_frames[pid].pop_front();
                in_cache[pid].erase(lru);
                proc_frames[pid].push_back(page);
                in_cache[pid].insert(page);
            }
        } else {
            proc_frames[pid].erase(remove(proc_frames[pid].begin(), proc_frames[pid].end(), page), proc_frames[pid].end());
            proc_frames[pid].push_back(page);
        }
    }
    return {alloc, faults};
}

void report(const string& name, const map<int, int>& alloc, const map<int, int>& faults, const vector<int>& pages) {
    cout << name << " Allocation:\n";
    for (const auto& [pid, fault] : faults) {
        cout << "    Process " << pid << ": Pages=" << pages[pid] << "  frames=" << alloc.at(pid) << ", faults=" << fault << "\n";
    }
    cout << "\n";
}

int main() {
    int num_processes = 5;
    vector<int> pages = {10, 5, 20, 15, 20};
    int total_frames = 20;
    int ref_count = 25;
    int locality_size = 5;

    vector<Process> processes;
    for (int i = 0; i < num_processes; ++i) {
        processes.emplace_back(i, pages[i], ref_count, locality_size);
    }

    auto merged = merge_references(processes);

    auto [alloc_eq, faults_eq] = equal_allocation(processes, total_frames, merged);
    report("Equal", alloc_eq, faults_eq, pages);

    auto [alloc_prop, faults_prop] = proportional_allocation(processes, total_frames, merged);
    report("Proportional", alloc_prop, faults_prop, pages);

    auto [alloc_pff, faults_pff] = pff_allocation(processes, total_frames, merged, 10, 0.7, 0.3);
    report("PFF", alloc_pff, faults_pff, pages);

    auto [alloc_ws, faults_ws] = working_set_allocation(processes, total_frames, merged, 10);
    report("Working Set", alloc_ws, faults_ws, pages);

    return 0;
}

