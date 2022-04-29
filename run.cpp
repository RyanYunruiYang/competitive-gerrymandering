#include <iostream>
#include <algorithm>
#include <chrono>
#include<vector>
using namespace std;
using namespace std::chrono;


int main():
    cout << "Enter multiple value: "
    cin >> a >> b
    cout << "piece size: "
    cin >> d



def main():
	x = list(map(int, input("Enter multiple value: ").split()))

	d = int(input("piece size: "))
	init = time.time()
	print("dp solution: " + str(dp(x[0],x[1], d)))
	print("dp time: "+ str(time.time() - init))
	midpoint = time.time()
	print("recursion solution: " + str(rectboard(x[0],x[1],returnPieces(d))))
	print("recursion time: " + str(time.time()-midpoint))