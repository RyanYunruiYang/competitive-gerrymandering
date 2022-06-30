import matplotlib.pyplot as plt
import sys
import time

from more_itertools import last
def mexOld(lst):
	if(len(lst)==0):
		return 0
	else:
		for i in range(0, max(lst)+1):
			if i not in lst:
				return i
		return max(lst)+1
def old(d,n):
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
            memoization[i] = mexOld(states)
            if(i==d):
                memoization[i]=1

    lastappearance = 0 
    for i in range(len(memoization)-2):
        print(str(i)+ ": " + str(memoization[i]))
        # if((memoization[i] == memoization[i+1]) and (memoization[i+1]==memoization[i+2])):
        #     print(str(i)+ "," + str(i+1)+ "," + str(i+2)+": " + str(memoization[i]))
        # if(memoization[i]==0):
        #     # print(str(i-lastappearance))
        #     print(i)
        #     lastappearance = i

    plt.plot([i for i in range(len(memoization))], memoization)
    plt.show()
    plt.ylabel("d: " + str(d+1))

def mex(lst):
    for i in range(len(lst)+1):
        if(lst[i]==0):
            return i
    return len(lst)+1

def main():
    maxsize = 1000
    memoization = [[0 for i in range(maxsize)] for j in range(maxsize)]

    for d in range(1,maxsize):
        for i in range(d, maxsize): #game on 1xi with pieces of size d.
            # print("i: " + str(i))
            states = [0 for i in range(int(i/d)+5)]
            for k in range(i-d):
                x= memoization[k][d]^memoization[i-d-k][d]
                states[x] += 1

            # print(states)
            memoization[i][d] = mex(states)
            if(i==d):
                memoization[i][d]=1
            # print("memoization["+str(i)+"]["+str(d)+"] = "+ str(memoization[i][d]))

    lastappearance = 0
    for n in range(1,maxsize):
        allP1 = True
        for d in range(1,n+1):
            if(memoization[n][d]==0):
                # print("n="+str(n)+" d: "+str(d))
                allP1=False
        if(allP1):
            print(str(n)+", ", end='')
            # print(n-lastappearance)
            # lastappearance = n

    # lastappearance = 0 
    # for i in range(len(memoization)-2):
    #     print(str(i)+ ": " + str(memoization[i]))

    if(False):
        plt.plot([i for i in range(len(memoization))], memoization)
        plt.show()
        plt.ylabel("d: " + str(d+1))


if __name__ == "__main__":
    start = time.time()
    # main()
    d = int(input())
    old(d,1000)
    print(time.time()-start)
    # indivValue = True
    # if(indivValue):
    #     d=int(sys.argv[1])
    #     n=int(sys.argv[2])
    #     main(d,n)
    # else:
    #     for d in range(2,20):
    #         main(d,5000)


#i/d+5 : 39.6673309803009
#size=n: 44.81200313568115
#lst: 47.51109504699707