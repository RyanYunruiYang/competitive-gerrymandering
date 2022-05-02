import matplotlib.pyplot as plt
import sys

from more_itertools import last
def mex(lst):
	if(len(lst)==0):
		return 0
	else:
		for i in range(0, max(lst)+1):
			if i not in lst:
				return i
		return max(lst)+1

def main(d,n):
    memoization = [0 for i in range(n)]

    for i in range(n):
        # print("i: " + str(i))
        if(i<d):
            memoization[i]=0

        else: #i>=d
            states = []

            for k in range(i-d):
                x= memoization[k]^memoization[i-d-k]
                states.append(x)
            # print(states)
            memoization[i] = mex(states)

            if(i==d):
                memoization[i]=1

    lastappearance = 0 
    for i in range(len(memoization)-2):
        # print(str(i)+ ": " + str(memoization[i]))
        if((memoization[i] == memoization[i+1]) and (memoization[i+1]==memoization[i+2])):
            print(str(i)+ "," + str(i+1)+ "," + str(i+2)+": " + str(memoization[i]))
        # if(memoization[i]==12):
        #     print(str(i-lastappearance))
        #     lastappearance = i

    plt.plot([i for i in range(len(memoization))], memoization)
    plt.show()
    plt.ylabel("d: " + str(d+1))


if __name__ == "__main__":
    indivValue = False
    if(indivValue):
        d=int(sys.argv[1])
        n=int(sys.argv[2])        
        main(d,n)
    else:
        for d in range(2,20):
            main(d,5000)