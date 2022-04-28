import copy
import time
from pentomino import pentomino_pieces
#todo: check the size of connected components, 
#if size <d: nimval = 0
#if d<=size<2*d:nimval = 1
#todo: switch to DP.
def mex(lst):
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



def dp(a,b,gamemoves):
	dpvalues = [0 for i in range(2**(a*b))]
	
	for o in range(2**(a*b)):
		board = [[0 for j in range(b)] for i in range(a)]
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
						# print(BoardToInt(newboard,a,b))
						# print(len(dpvalues))
						# print(dpvalues[256])
						states.append(dpvalues[BoardToInt(newboard,a,b)])
		dpvalues[o] = mex(states)



	return dpvalues[2**(a*b)-1]

def rectboard(a,b,gamemoves):
	board = [[1 for j in range(b)] for i in range(a)]

	return solve(board,a,b,gamemoves,0)

def main():
	#tetromino:
	domino_gamemoves = [[(0,0),(1,0)],[(0,0),(0,1)]]
	tetromino_gamemoves = [[(0,0),(1,0),(2,0)],[(0,0),(0,1),(0,2)], [(0,0),(1,0),(1,1)],[(0,0),(1,0),(0,1)],[(0,0),(0,1),(1,1)],[(0,0),(0,1),(-1,1)]]
	pentomino_gamemoves = pentomino_pieces()

	x = list(map(int, input("Enter multiple value: ").split()))
	
	# board = [(0, 0), (0, 1), (1, 0), (1, 1), (2, 0)]
	# for i in range(1,6):
	# 	for j in range(1,6):
	# 		print(rectboard(i,j, gamemoves),end=' ')
	# 	print()
	init = time.time()
	print("dp solution: " + str(dp(x[0],x[1], domino_gamemoves)))
	print("dp time: "+ str(time.time() - init))
	midpoint = time.time()
	print("recursion solution: " + str(rectboard(x[0],x[1],domino_gamemoves)))
	print("recursion time: " + str(time.time()-midpoint))

if __name__ == "__main__":
	main()