#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <random>
#include <ctime>
#include <iomanip>

struct Process {
    int id, arrival, burst, waiting;
};

std::vector<Process> generate_processes(int num_processes, int max_arrival = 100, double mean_burst = 10.0) {
    std::vector<Process> processes;
    std::default_random_engine gen(time(nullptr));
    std::uniform_int_distribution<int> arrival_dist(0, max_arrival);
    std::exponential_distribution<double> burst_dist(1.0 / mean_burst);

    for (int i = 0; i < num_processes; ++i) {
        int arrival = arrival_dist(gen);
        int burst = std::max(1, static_cast<int>(burst_dist(gen)));
        processes.push_back({i, arrival, burst, 0});
    }

    std::sort(processes.begin(), processes.end(), [](const Process &a, const Process &b) {
        return a.arrival < b.arrival;
    });

    return processes;
}

double average_waiting_time(const std::vector<Process>& processes) {
    int total = 0;
    for (const auto& p : processes)
        total += p.waiting;
    return static_cast<double>(total) / processes.size();
}

// FCFS Scheduling
std::pair<double, std::vector<Process>> fcfs_scheduling(std::vector<Process> processes) {
    int time = 0;
    for (auto& p : processes) {
        p.waiting = std::max(0, time - p.arrival);
        time = std::max(time, p.arrival) + p.burst;
    }
    return {average_waiting_time(processes), processes};
}

// SJF Non-Preemptive
std::pair<double, std::vector<Process>> sjf_non_preemptive(std::vector<Process> processes) {
    int time = 0;
    std::vector<Process> completed;
    std::vector<Process> ready;
    std::vector<Process> remaining = processes;

    while (!remaining.empty() || !ready.empty()) {
        for (auto it = remaining.begin(); it != remaining.end();) {
            if (it->arrival <= time) {
                ready.push_back(*it);
                it = remaining.erase(it);
            } else {
                ++it;
            }
        }

        if (!ready.empty()) {
            std::sort(ready.begin(), ready.end(), [](const Process& a, const Process& b) {
                return a.burst < b.burst;
            });
            Process p = ready.front();
            ready.erase(ready.begin());

            if (time < p.arrival)
                time = p.arrival;
            p.waiting = time - p.arrival;
            time += p.burst;
            completed.push_back(p);
        } else if (!remaining.empty()) {
            time = std::min_element(remaining.begin(), remaining.end(), [](const Process& a, const Process& b) {
                return a.arrival < b.arrival;
            })->arrival;
        }
    }
    return {average_waiting_time(completed), completed};
}

// SJF Preemptive (SRTF)
std::pair<double, std::vector<Process>> sjf_preemptive(std::vector<Process> processes) {
    int time = 0;
    int n = processes.size();
    std::vector<int> remaining(n);
    std::vector<int> waiting(n, 0);
    std::vector<bool> started(n, false);
    int completed = 0;

    for (int i = 0; i < n; ++i)
        remaining[i] = processes[i].burst;

    while (completed < n) {
        int idx = -1;
        int min_rem = INT32_MAX;
        for (int i = 0; i < n; ++i) {
            if (processes[i].arrival <= time && remaining[i] > 0 && remaining[i] < min_rem) {
                min_rem = remaining[i];
                idx = i;
            }
        }

        if (idx != -1) {
            remaining[idx]--;
            for (int i = 0; i < n; ++i) {
                if (i != idx && processes[i].arrival <= time && remaining[i] > 0)
                    waiting[i]++;
            }
            if (remaining[idx] == 0)
                completed++;
            time++;
        } else {
            time++;
        }
    }

    for (int i = 0; i < n; ++i)
        processes[i].waiting = waiting[i];

    return {average_waiting_time(processes), processes};
}

// Round Robin
std::pair<double, std::vector<Process>> round_robin(std::vector<Process> processes, int quantum = 4) {
    int n = processes.size();
    std::vector<int> remaining(n);
    std::vector<int> waiting(n, 0);
    std::queue<int> q;
    int time = 0, idx = 0;

    for (int i = 0; i < n; ++i)
        remaining[i] = processes[i].burst;

    std::sort(processes.begin(), processes.end(), [](const Process& a, const Process& b) {
        return a.arrival < b.arrival;
    });

    while (idx < n && processes[idx].arrival <= time)
        q.push(idx++);

    while (!q.empty() || idx < n) {
        if (!q.empty()) {
            int current = q.front();
            q.pop();
            int run_time = std::min(quantum, remaining[current]);

            for (int i = 0; i < n; ++i) {
                if (i != current && remaining[i] > 0 && processes[i].arrival <= time)
                    waiting[i] += run_time;
            }

            time += run_time;
            remaining[current] -= run_time;

            while (idx < n && processes[idx].arrival <= time)
                q.push(idx++);
            if (remaining[current] > 0)
                q.push(current);
        } else {
            time = processes[idx].arrival;
            q.push(idx++);
        }
    }

    for (int i = 0; i < n; ++i)
        processes[i].waiting = waiting[i];

    return {average_waiting_time(processes), processes};
}

void simulate() {
    int num_processes = 100;
    auto processes = generate_processes(num_processes);

    std::cout << "Generated Processes:\n";
    for (const auto& p : processes)
        std::cout << "Process " << p.id << ": Arrival = " << p.arrival << ", Burst = " << p.burst << "\n";

    std::cout << "\n--- Scheduling Results ---\n\n";

    auto [avg_fcfs, fcfs_procs] = fcfs_scheduling(processes);
    std::cout << "FCFS Average Waiting Time: " << std::fixed << std::setprecision(2) << avg_fcfs << "\n";

    auto [avg_sjf_np, sjf_np_procs] = sjf_non_preemptive(processes);
    std::cout << "SJF Non-Preemptive Average Waiting Time: " << avg_sjf_np << "\n";

    auto [avg_sjf_p, sjf_p_procs] = sjf_preemptive(processes);
    std::cout << "SJF Preemptive Average Waiting Time: " << avg_sjf_p << "\n";

    int quantum = 25;
    auto [avg_rr, rr_procs] = round_robin(processes, quantum);
    std::cout << "Round Robin (Quantum = " << quantum << ") Average Waiting Time: " << avg_rr << "\n";
}

int main() {
    simulate();
    return 0;
}
