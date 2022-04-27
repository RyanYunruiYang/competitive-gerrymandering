import copy
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

debug = True
def solve(board,gamemoves,level): #returns nim value
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
					states.append(solve(newboard,gamemoves,level+1))
	if(level<=1 and debug):
		print("one branch done")
		# print("\n level: " + str(level))
		# print(board)
		# print("states: ")
		# print(states)
		# print("mex(states): " + str(mex(states)))
		# print(".")
	return mex(states)

def rectboard(a,b,gamemoves):
	board = [[1 for j in range(b)] for i in range(a)]

	return solve(board,gamemoves,0)

def main():
	#tetromino:
	tetromino_gamemoves = [[(0,0),(1,0),(2,0)],[(0,0),(0,1),(0,2)], [(0,0),(1,0),(1,1)],[(0,0),(1,0),(0,1)],[(0,0),(0,1),(1,1)],[(0,0),(0,1),(-1,1)]]
	pentomino_gamemoves = pentomino_pieces()

	x = list(map(int, input("Enter multiple value: ").split()))
	
	# board = [(0, 0), (0, 1), (1, 0), (1, 1), (2, 0)]
	# for i in range(1,6):
	# 	for j in range(1,6):
	# 		print(rectboard(i,j, gamemoves),end=' ')
	# 	print()
	print(rectboard(x[0],x[1],pentomino_gamemoves))

if __name__ == "__main__":
	main()