#include <iostream>
#include <vector>
#include <algorithm>
#include <cstdlib>
#include <ctime>
#include <cmath>

using namespace std;

struct RTRequest {
    int block;
    int deadline;
};

// ---------- Disk Scheduling Algorithms ----------

pair<vector<int>, int> fcfs(int initial_pos, const vector<int>& requests) {
    int current = initial_pos;
    int total_movement = 0;
    vector<int> order;

    for (int req : requests) {
        total_movement += abs(req - current);
        order.push_back(req);
        current = req;
    }
    return {order, total_movement};
}

pair<vector<int>, int> sstf(int initial_pos, vector<int> requests) {
    int current = initial_pos;
    int total_movement = 0;
    vector<int> order;

    while (!requests.empty()) {
        auto nearest_it = min_element(requests.begin(), requests.end(),
            [current](int a, int b) {
                return abs(a - current) < abs(b - current);
            });
        int nearest = *nearest_it;
        total_movement += abs(nearest - current);
        order.push_back(nearest);
        current = nearest;
        requests.erase(nearest_it);
    }
    return {order, total_movement};
}

pair<vector<int>, int> scan(int initial_pos, vector<int> requests, int disk_size, string direction = "up") {
    int current = initial_pos;
    int total_movement = 0;
    vector<int> order;

    sort(requests.begin(), requests.end());
    vector<int> up, down;
    for (int r : requests) {
        if (r >= current) up.push_back(r);
        else down.push_back(r);
    }

    if (direction == "up") {
        for (int r : up) {
            total_movement += abs(r - current);
            order.push_back(r);
            current = r;
        }
        if (!down.empty()) {
            if (current != disk_size) {
                total_movement += abs(disk_size - current);
                current = disk_size;
            }
            for (auto it = down.rbegin(); it != down.rend(); ++it) {
                total_movement += abs(current - *it);
                order.push_back(*it);
                current = *it;
            }
        }
    }
    // "down" direction can be added here similarly if needed

    return {order, total_movement};
}

pair<vector<int>, int> c_scan(int initial_pos, vector<int> requests, int disk_size) {
    int current = initial_pos;
    int total_movement = 0;
    vector<int> order;

    sort(requests.begin(), requests.end());
    vector<int> right, left;
    for (int r : requests) {
        if (r >= current) right.push_back(r);
        else left.push_back(r);
    }

    for (int r : right) {
        total_movement += abs(r - current);
        order.push_back(r);
        current = r;
    }

    if (!left.empty()) {
        total_movement += abs(disk_size - current);
        total_movement += disk_size - 1;  // simulate jump
        current = 1;
        for (int r : left) {
            total_movement += abs(r - current);
            order.push_back(r);
            current = r;
        }
    }

    return {order, total_movement};
}

// ---------- Real-Time Scheduling ----------

tuple<vector<int>, int, vector<RTRequest>> edf(int initial_pos, vector<RTRequest> rt_requests) {
    int current = initial_pos;
    int total_movement = 0;
    vector<int> order;
    vector<RTRequest> missed;

    sort(rt_requests.begin(), rt_requests.end(),
         [](const RTRequest& a, const RTRequest& b) {
             return a.deadline < b.deadline;
         });

    for (const auto& req : rt_requests) {
        int move = abs(req.block - current);
        if (move > req.deadline) {
            missed.push_back(req);
        } else {
            total_movement += move;
            order.push_back(req.block);
            current = req.block;
        }
    }

    return {order, total_movement, missed};
}

// ---------- Main Simulation ----------

int main() {
    srand(static_cast<unsigned>(time(nullptr)));

    int disk_size = 200;
    int initial_position = rand() % disk_size + 1;
    int num_requests = 50;
    vector<int> requests;

    for (int i = 0; i < num_requests; ++i)
        requests.push_back(rand() % disk_size + 1);

    cout << "==== Disk Scheduling Simulation ====\n";
    cout << "Disk size: " << disk_size << "\n";
    cout << "Initial head position: " << initial_position << "\n";
    cout << "Disk requests: ";
    for (int r : requests) cout << r << " ";
    cout << "\n\n";

    auto [fcfs_order, fcfs_movement] = fcfs(initial_position, requests);
    cout << "FCFS Total Head Movement: " << fcfs_movement << "\n";

    auto [sstf_order, sstf_movement] = sstf(initial_position, requests);
    cout << "SSTF Total Head Movement: " << sstf_movement << "\n";

    auto [scan_order, scan_movement] = scan(initial_position, requests, disk_size);
    cout << "SCAN Total Head Movement: " << scan_movement << "\n";

    auto [cscan_order, cscan_movement] = c_scan(initial_position, requests, disk_size);
    cout << "C-SCAN Total Head Movement: " << cscan_movement << "\n\n";

    int num_rt_requests = 5;
    vector<RTRequest> rt_requests;

    for (int i = 0; i < num_rt_requests; ++i) {
        int block = rand() % disk_size + 1;
        int base = abs(block - initial_position);
        int slack = rand() % 51;
        rt_requests.push_back({block, base + slack});
    }

    cout << "Real-Time Requests (block, deadline): ";
    for (const auto& r : rt_requests)
        cout << "(" << r.block << ", " << r.deadline << ") ";
    cout << "\n";

    auto [edf_order, edf_movement, edf_missed] = edf(initial_position, rt_requests);
    cout << "EDF Total Head Movement: " << edf_movement << "\n";
    cout << "EDF Missed Requests: ";
    for (const auto& r : edf_missed)
        cout << "(" << r.block << ", " << r.deadline << ") ";
    cout << "\n";

    return 0;
}
