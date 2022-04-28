import sys
from copy import deepcopy

def generate_minoes(n):
	if n==1:
		return [[[0,0]]]

	possibleminoes = []
	oldminoes = generate_minoes(n-1)
	for smallminoe in oldminoes:
		print(smallminoe)
		for square in smallminoe:
			if [square[0]-1, square[1]] not in smallminoe:
				duplicate_smallminoe = deepcopy(smallminoe)
				duplicate_smallminoe.append([square[0]-1, square[1]])
				if square[0] < 1: 
					for squonk in duplicate_smallminoe:
						squonk[0] = squonk[0]+1
				possibleminoes.append(duplicate_smallminoe)
			if [square[0]+1, square[1]] not in smallminoe:
				duplicate_smallminoe = deepcopy(smallminoe)
				duplicate_smallminoe.append([square[0]+1, square[1]])
				possibleminoes.append(duplicate_smallminoe)
			if [square[0], square[1]-1] not in smallminoe:
				duplicate_smallminoe = deepcopy(smallminoe)
				duplicate_smallminoe.append([square[0], square[1]-1])
				if square[1] < 1: 
					for squonk in duplicate_smallminoe:
						squonk[1] = squonk[1]+1
				possibleminoes.append(duplicate_smallminoe)
			if [square[0], square[1]+1] not in smallminoe:
				duplicate_smallminoe = deepcopy(smallminoe)
				duplicate_smallminoe.append([square[0], square[1]+1])
				possibleminoes.append(duplicate_smallminoe)

	print("test")

	#search for duplicates in possibleminoes
	possibleminoes_again = []
	for possibleminoe in possibleminoes:
		possibleminoe.sort()
		if possibleminoe not in possibleminoes_again:
			possibleminoes_again.append(possibleminoe)

	return(possibleminoes_again)

print(generate_minoes(int(sys.argv[1])))

