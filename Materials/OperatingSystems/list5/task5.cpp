#include <iostream>
#include <vector>
#include <cstdlib>
#include <ctime>
#include <cmath>
#include <numeric>
#include <algorithm>
using namespace std;

struct Task {
    double load; // % load
};

struct Processor {
    vector<Task> tasks;

    double getLoad() const {
        double total = 0.0;
        for (const auto& task : tasks) total += task.load;
        return total;
    }

    void addTask(const Task& task) {
        tasks.push_back(task);
    }

    void removeTask(size_t index) {
        if (index < tasks.size()) {
            tasks.erase(tasks.begin() + index);
        }
    }
};

class Simulator {
    vector<Processor> processors;
    int N;
    double p, r;
    int z;
    int queries = 0, migrations = 0;
    int fallbackAssignments = 0;
    int totalTasks = 1000;

    double randomLoad() {
        return (rand() % 28 + 3); // Load from 3% to 30%
    }

    int randomProcessor(int exclude = -1) {
        int id;
        do {
            id = rand() % N;
        } while (id == exclude);
        return id;
    }

    void simulateStrategy1() {
        for (int t = 0; t < totalTasks; ++t) {
            Task task{randomLoad()};
            int x = rand() % N;
            bool assigned = false;

            for (int attempt = 0; attempt < z; ++attempt) {
                int y = randomProcessor(x);
                queries++;
                if (processors[y].getLoad() < p) {
                    processors[y].addTask(task);
                    migrations++;
                    assigned = true;
                    break;
                }
            }
            if (!assigned) processors[x].addTask(task);
        }
    }

    void simulateStrategy2() {
        const int maxRetries = 50;

        for (int t = 0; t < totalTasks; ++t) {
            Task task{randomLoad()};
            int x = rand() % N;

            if (processors[x].getLoad() < p) {
                processors[x].addTask(task);
            } else {
                bool assigned = false;
                for (int attempt = 0; attempt < maxRetries; ++attempt) {
                    int y = randomProcessor(x);
                    queries++;
                    if (processors[y].getLoad() < p) {
                        processors[y].addTask(task);
                        migrations++;
                        assigned = true;
                        break;
                    }
                }
                if (!assigned) {
                    processors[x].addTask(task); // fallback to local
                    fallbackAssignments++;
                }
            }
        }
    }


    void simulateStrategy3() {
        simulateStrategy2(); // same as strategy 2 at first

        // Additional load sharing from underloaded processors
        for (int i = 0; i < N; ++i) {
            if (processors[i].getLoad() < r) {
                for (int j = 0; j < 3; ++j) {
                    int y = randomProcessor(i);
                    queries++;
                    if (processors[y].getLoad() > p && !processors[y].tasks.empty()) {
                        size_t idx = rand() % processors[y].tasks.size();
                        Task movedTask = processors[y].tasks[idx];
                        processors[i].addTask(movedTask);
                        processors[y].removeTask(idx);
                        migrations++;
                    }
                }
            }
        }
    }

    void reportStats(const string& strategyName) {
        vector<double> loads;
        for (const auto& proc : processors) {
            loads.push_back(proc.getLoad());
        }

        double avg = accumulate(loads.begin(), loads.end(), 0.0) / N;
        double dev = 0.0;
        for (double l : loads) dev += abs(l - avg);
        dev /= N;

        cout << "\n[" << strategyName << "]\n";
        cout << "Average processor load: " << avg / 10 << "%\n";
        cout << "Average deviation: " << dev << "%\n";
        cout << "Load queries: " << queries << "\n";
        cout << "Task migrations: " << migrations << "\n";
        cout << "Fallback to local execution (after max retries): " << fallbackAssignments << "\n"; 
    }

public:
    Simulator(int _N, double _p, double _r, int _z) : N(_N), p(_p), r(_r), z(_z) {
        processors.resize(N);
        srand(time(nullptr));
    }

    void run(int strategy) {
        processors = vector<Processor>(N); // reset
        queries = migrations = fallbackAssignments = 0;
        queries = migrations = 0;

        if (strategy == 1)
            simulateStrategy1();
        else if (strategy == 2)
            simulateStrategy2();
        else if (strategy == 3)
            simulateStrategy3();

        reportStats("Strategy " + to_string(strategy));
    }
};

int main() {
    int N, z;
    double p, r;
    cout << "Enter number of processors (N): ";
    cin >> N;
    cout << "Enter threshold value p (e.g., 70): ";
    cin >> p;
    cout << "Enter minimum threshold r (e.g., 30): ";
    cin >> r;
    cout << "Enter max query attempts z (e.g., 3): ";
    cin >> z;

    Simulator sim(N, p, r, z);

    sim.run(1);
    sim.run(2);
    sim.run(3);

    return 0;
}
