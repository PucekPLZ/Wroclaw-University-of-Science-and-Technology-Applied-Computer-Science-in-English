#include <iostream>
#include <vector>
#include <deque>
#include <unordered_set>
#include <unordered_map>
#include <algorithm>
#include <cstdlib>
#include <ctime>

using namespace std;

vector<int> generate_reference_string(int length, int num_pages, int locality_window = 10) {
    vector<int> ref_string;
    for (int i = 0; i < length; ++i) {
        if (!ref_string.empty() && (rand() % 100) < 90) {
            int start = max(0, (int)ref_string.size() - locality_window);
            ref_string.push_back(ref_string[start + rand() % (ref_string.size() - start)]);
        } else {
            ref_string.push_back(rand() % num_pages);
        }
    }
    return ref_string;
}

int simulate_fifo(const vector<int>& ref_string, int num_frames) {
    unordered_set<int> page_set;
    deque<int> queue;
    int page_faults = 0;

    for (int page : ref_string) {
        if (page_set.find(page) == page_set.end()) {
            ++page_faults;
            if ((int)page_set.size() >= num_frames) {
                page_set.erase(queue.front());
                queue.pop_front();
            }
            page_set.insert(page);
            queue.push_back(page);
        }
    }
    return page_faults;
}

int simulate_second_chance(const vector<int>& ref_string, int num_frames) {
    deque<int> queue;
    unordered_map<int, int> ref_bits;
    unordered_set<int> page_set;
    int page_faults = 0;

    for (int page : ref_string) {
        if (page_set.count(page)) {
            ref_bits[page] = 1;
        } else {
            ++page_faults;
            while ((int)queue.size() >= num_frames) {
                int candidate = queue.front();
                if (ref_bits[candidate] == 1) {
                    ref_bits[candidate] = 0;
                    queue.pop_front();
                    queue.push_back(candidate);
                } else {
                    queue.pop_front();
                    page_set.erase(candidate);
                    break;
                }
            }
            page_set.insert(page);
            queue.push_back(page);
            ref_bits[page] = 0;
        }
    }
    return page_faults;
}

int simulate_lru(const vector<int>& ref_string, int num_frames) {
    unordered_map<int, int> cache;
    int page_faults = 0;
    int time = 0;

    for (int page : ref_string) {
        if (cache.find(page) == cache.end()) {
            ++page_faults;
            if ((int)cache.size() >= num_frames) {
                int lru_page = min_element(cache.begin(), cache.end(),
                    [](auto& a, auto& b) { return a.second < b.second; })->first;
                cache.erase(lru_page);
            }
        }
        cache[page] = time++;
    }
    return page_faults;
}

int simulate_opt(const vector<int>& ref_string, int num_frames) {
    unordered_set<int> page_set;
    int page_faults = 0;

    for (size_t i = 0; i < ref_string.size(); ++i) {
        int page = ref_string[i];
        if (page_set.find(page) == page_set.end()) {
            ++page_faults;
            if ((int)page_set.size() >= num_frames) {
                unordered_map<int, int> future_uses;
                for (int p : page_set) {
                    auto it = find(ref_string.begin() + i + 1, ref_string.end(), p);
                    future_uses[p] = (it != ref_string.end()) ? distance(ref_string.begin(), it) : INT_MAX;
                }
                int evict_page = max_element(future_uses.begin(), future_uses.end(),
                    [](auto& a, auto& b) { return a.second < b.second; })->first;
                page_set.erase(evict_page);
            }
            page_set.insert(page);
        }
    }
    return page_faults;
}

int simulate_rand(const vector<int>& ref_string, int num_frames) {
    unordered_set<int> page_set;
    vector<int> page_list;
    int page_faults = 0;

    for (int page : ref_string) {
        if (page_set.find(page) == page_set.end()) {
            ++page_faults;
            if ((int)page_set.size() >= num_frames) {
                int idx = rand() % page_list.size();
                page_set.erase(page_list[idx]);
                page_list.erase(page_list.begin() + idx);
            }
            page_set.insert(page);
            page_list.push_back(page);
        }
    }
    return page_faults;
}

int main() {
    srand(time(nullptr));
    int num_pages = 50;
    int sequence_length = 1000;
    vector<int> ref_string = generate_reference_string(sequence_length, num_pages);
    vector<int> frame_sizes = {40};

    for (int frames : frame_sizes) {
        cout << "\nNumber of frames: " << frames << endl;
        cout << "FIFO: " << simulate_fifo(ref_string, frames) << endl;
        cout << "Second Chance (ALRU): " << simulate_second_chance(ref_string, frames) << endl;
        cout << "LRU: " << simulate_lru(ref_string, frames) << endl;
        cout << "OPT: " << simulate_opt(ref_string, frames) << endl;
        cout << "RAND: " << simulate_rand(ref_string, frames) << endl;
    }
    return 0;
}
