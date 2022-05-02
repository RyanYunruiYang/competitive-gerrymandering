#include <iostream>
#include <algorithm>
#include <cmath>
#include <chrono>
#include <vector>
#include "pentomino.cpp"
using namespace std;

//todo: check the size of connected components, 
//if size <d: nimval = 0
//if d<=size<2*d:nimval = 1
//todo: switch to DP.

int mex(vector<int> lst) {
    if (lst.size() == 0) {
        return 0;
    } else {
        for (int i = 0; i < *max_element(lst.begin(), lst.end()) + 1; i++) {
            if (*std::find(lst.begin(), lst.end(), i) != i) {
                return i;
            }
        }
        return *max_element(lst.begin(), lst.end()) + 1;
    }
}

bool debug = false;
    //Returning nim value of board
// int solve(vector<vector<int>> board, int a, int b, vector<vector<int>> gamemeoves, int level) {
//     vector<int> states; //a = board.size(); b = board[0].size();

//     for (int p = 0; p < gamemeoves.size(); p++) {

//     }

// }

int BoardToTint(vector<vector<int>> board, int a, int b) {
    int val = 0;
    for (int i = 0; i < a; i++) {
        for (int j = 0; j < b; j++) {
            val += board[i][j] * pow(2, b*i + j);
        }
    }
    return val;
}

//Sending Game Values
int dp(int a, int b, vector<vector<int>> gamemoves) {
    vector<int> dpvalues = {};
    for (int i = 0; i < pow(2, a*b); i++) {
        dpvalues.push_back(0);
    }

    for (int o = 0; o < pow(2, a*b); o++) {
        vector<vector<int>> board = {{}};
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                board[i].push_back(j);
            }
        }
        int bitmask = o;
        for (int x = 0; x < a; x++) {
            for (int y = 0; y < b; y++) {
                board[x][y] = bitmask%2;
                bitmask = bitmask >> 1;   
            }
        }

        vector<int> states = {}; //a = board.size(); b = board[0].size();
        for (int p = 0; p < gamemoves.size(); p++) {
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    vector<int> move = {};
                    for (int c = 0; c < gamemoves[p].size(); c++) {
                        move.push_back(gamemoves[c][0]+i, gamemoves[c][1]+j);
                    }
                }
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(NULL);
    int a, b, d;
    cout << "Enter multiple value: "; cin >> a >> b;
    cout << "piece size: "; cin >> d;

    vector<vector<int>> domino_gamesmoves_moves = {{(0,0),(1,0)},{(0,0),(0,1)}};
	vector<vector<int>> tetromino_gamemoves = {{(0,0),(1,0),(2,0)},{(0,0),(0,1),(0,2)}, {(0,0),(1,0),(1,1)},{(0,0),(1,0),(0,1)},{(0,0),(0,1),(1,1)},{(0,0),(0,1),(-1,1)}};
	vector<vector<int>> pentomino_gamemoves = pentomino_pieces();

    std::chrono::time_point<std::chrono::system_clock> init, midpoint;
    init = std::chrono::system_clock::now();
    printf("dp solution: " + (dp(a, b, domino_gamesmoves_moves)));
    printf("dp time: " + std::string(std::chrono::system_clock::now() - init));
    midpoint = std::chrono::system_clock::now();
}