import copy
import time
from pentomino import pentomino_pieces
#todo: check the size of connected components, 
#if size <d: nimval = 0
#if d<=size<2*d:nimval = 1

def mex(lst): #Nim Value of a position is the mex of the states it can reach.
	if(len(lst)==0):
		return 0
	else:
		for i in range(0, max(lst)+1):
			if i not in lst:
				return i
		return max(lst)+1

debug = False
def solve(board,a,b,gamemoves,level): #returns nim value
	states = [] #a=len(board), b = len(board[0])
	for piece in gamemoves:
		for i in range(len(board)):
			for j in range(len(board[0])):
				move = [(c[0]+i,c[1]+j) for c in piece]

				newboard = copy.deepcopy(board)

				valid = True
				for cell in move:
					if(cell[0]>=0 and cell[0]<len(board) and cell[1]>=0 and cell[1]<len(board[0]) and newboard[cell[0]][cell[1]]==1):
						newboard[cell[0]][cell[1]]=0
					else:
						valid = False
				if(valid):
					states.append(solve(newboard,a,b,gamemoves, level+1))
	if(level<=1 and debug):
		print("one branch done")

	return mex(states)

def BoardToInt(board,a,b):
	val = 0
	for i in range(a):
		for j in range(b):
			val += board[i][j] * (2**(b*i + j))
	return val

def hammingWeight(o):
	sum = 0
	while(o>0):
		sum += o%2
		o = o//2
	return sum

def returnPieces(d):
	if(d==2):
		return [[(0,0),(1,0)],[(0,0),(0,1)]]
	if(d==3):
		return [[(0,0),(1,0),(2,0)],[(0,0),(0,1),(0,2)], [(0,0),(1,0),(1,1)],[(0,0),(1,0),(0,1)],[(0,0),(0,1),(1,1)],[(0,0),(0,1),(-1,1)]]
	if(d==5):
		return pentomino_pieces()	


def dp(a,b,d):
	gamemoves = returnPieces(d)
	dpvalues = [0 for i in range(2**(a*b))]	
	percsize = 18
	currtime = 0
	for o in range(2**(a*b)):
		if(o%(2**(a*b-percsize))==0):
			# print(str(o/(2**(a*b-percsize))) + " out of "+ str(2**percsize)+". Seconds needed: " + str(time.time()-currtime))
			currtime = time.time()

		board = [[0 for j in range(b)] for i in range(a)]

		if((a*b-hammingWeight(o))%d ==0 ):
			bitmask = o
			for x in range(a):
				for y in range(b):
					board[x][y] = bitmask%2
					bitmask = bitmask >> 1

			states = [] #a=len(board), b = len(board[0])
			for piece in gamemoves:
				for i in range(a):
					for j in range(b):
						move = [(c[0]+i,c[1]+j) for c in piece]
						newboard = copy.deepcopy(board)

						valid = True
						for cell in move:
							if(cell[0]>=0 and cell[0]<len(board) and cell[1]>=0 and cell[1]<len(board[0]) and newboard[cell[0]][cell[1]]==1):
								newboard[cell[0]][cell[1]]=0
							else:
								valid = False
						if(valid):
							states.append(dpvalues[BoardToInt(newboard,a,b)])
			dpvalues[o] = mex(states)

	# print(dpvalues)
	return dpvalues[2**(a*b)-1]

def rectboard(a,b,gamemoves):
	board = [[1 for j in range(b)] for i in range(a)]
	return solve(board,a,b,gamemoves,0)

def main():
    if(True):
        x = list(map(int, input("Enter a b d: ").split()))
        init = time.time()
        print("dp solution: " + str(dp(x[0],x[1], x[2])))
        print("dp time: "+ str(time.time() - init))
        midpoint = time.time()
        print("recursion solution: " + str(rectboard(x[0],x[1],returnPieces(x[2]))))
        print("recursion time: " + str(time.time()-midpoint))
    else:
        for i in range(100):
            print(str(i)+": " + str(rectboard(1,i, returnPieces(3))))
	
	# d=2
	# n=3
	# table = [[-1 for i in range(n)] for j in range(n)]
	# for i in range(n):
	# 	for j in range(n):
	# 		if(i<=j):
	# 			table[i][j] = dp(2*i+1,2*j+1,d)
	# 		if(i>j):
	# 			table[i][j] = table[j][i]
	# for i in range(n):
	# 	for j in range(n):
	# 		print(table[i][j],end=' ')
	# 	print()

if __name__ == "__main__":
	main()