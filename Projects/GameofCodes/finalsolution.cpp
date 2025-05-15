// you can use includes, for example:
// #include <algorithm>

// you can write to stdout for debugging purposes, e.g.
// cout << "this is a debug message" << endl;

#include <string>
#include <algorithm>  

int solution(std::string &S) {
    int store[3][26] = {0};
    int best[3] = {0};

    for (char& ch : S) {
        int letter = ch - 'a';

        int cand3 = std::max(store[2][letter] + 1, best[1] + 1);
        store[2][letter] = cand3;
        best[2] = std::max(best[2], cand3);

        int cand2 = std::max(store[1][letter] + 1, best[0] + 1);
        store[1][letter] = cand2;
        best[1] = std::max(best[1], cand2);

        int cand1 = store[0][letter] + 1;
        store[0][letter] = cand1;
        best[0] = std::max(best[0], cand1);
    }

    return std::max({best[0], best[1], best[2]});
}