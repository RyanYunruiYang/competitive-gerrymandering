import java.util.*;

public class dp {
	public static int max(int[] lst){
		int max = -1;
		for (int i=0;i<lst.length ;i++ ) {
			if(lst[i]>max){
				max = lst[i];
			}
		}
		return max;
	}

	public static int mex(int[] lst) {
		int[] appearances = new int[max(lst)+2];

		for (int i=0;i<lst.length ;i++ ) {
			appearances[lst[i]]+=1;
		}
		for (int i=0; i<appearances.length ;i++ ) {
			if(appearances[i]==0){
				return i;
			}
		}
		System.out.println("L y propocion");
		return -1;
	}

	public static int[][][] returnPieces(int d) {
		if(d==2){
			int[][][] pieces = {{{0,0},{1,0}},{{0,0},{0,1}}};
			return pieces;
		}

		// if(d==3){
		// 	return [[[0,0],[1,0],[2,0]],[[0,0],[0,1],[0,2]], [[0,0],[1,0],[1,1]],[[0,0],[1,0],[0,1]],[[0,0],[0,1],[1,1]],[[0,0],[0,1],[-1,1]]];
		// }
		if(d==5){
			pentomino worker = new pentomino(5);
			return worker.pentomino_pieces();
			// return [[[0, 0], [1, 0], [2, 0], [3, 0], [4, 0]], [[0, 0], [1, 0], [2, 0], [3, 0], [3, 1]], [[0, 0], [0, 1], [1, 0], [2, 0], [3, 0]], [[0, 0], [0, 1], [0, 2], [1, 2], [0, 3]], [[0, 0], [0, 1], [1, 1], [0, 2], [0, 3]], [[0, 0], [0, 1], [1, 0], [0, 2], [2, 0]], [[0, 0], [1, 0], [2, 0], [1, 1], [2, 1]], [[0, 0], [1, 0], [2, 0], [0, 1], [1, 1]], [[0, 0], [0, 1], [0, 2], [0, 3], [0, 4]], [[0, 0], [1, 0], [0, 1], [0, 2], [0, 3]], [[0, 0], [0, 1], [0, 2], [0, 3], [1, 3]], [[0, 0], [1, 0], [0, 1], [0, 2], [1, 2]], [[0, 0], [0, 1], [0, 2], [1, 2], [0, 3]], [[0, 0], [0, 1], [0, 2], [-1, 2], [0, 3]], [[0, 0], [0, 1], [1, 1], [0, 2], [0, 3]], [[0, 0], [0, 1], [-1, 1], [0, 2], [0, 3]], [[0, 0], [1, 0], [2, 0], [3, 0], [2, -1]], [[0, 0], [1, 0], [1, -1], [2, 0], [3, 0]], [[0, 0], [1, 0], [0, 1], [1, 1], [0, 2]], [[0, 0], [1, 0], [0, 1], [1, 1], [1, 2]], [[0, 0], [0, 1], [1, 1], [2, 1], [2, 0]], [[0, 0], [1, 0], [1, 1], [1, 2], [0, 2]], [[0, 0], [1, 0], [1, 1], [2, 1], [2, 2]], [[0, 0], [1, 0], [0, 1], [-1, 1], [-1, 2]], [[0, 0], [0, 1], [1, 1], [1, 2], [2, 2]], [[0, 0], [0, 1], [-1, 1], [-1, 2], [-2, 2]], [[0, 0], [0, 1], [-1, 1], [0, 2], [1, 2]], [[0, 0], [0, 1], [1, 1], [0, 2], [-1, 2]], [[0, 0], [1, 0], [2, 0], [1, 1], [2, -1]], [[0, 0], [0, 1], [1, 1], [2, 1], [1, 2]], [[0, 0], [0, 1], [1, 1], [2, 1], [2, 2]], [[0, 0], [0, 1], [-1, 1], [-2, 1], [-2, 2]], [[0, 0], [1, 0], [2, 0], [2, 1], [2, -1]], [[0, 0], [0, 1], [0, 2], [1, 2], [2, 2]], [[0, 0], [0, 1], [0, 2], [-1, 2], [1, 2]], [[0, 0], [1, 0], [2, 0], [1, 1], [1, 2]], [[0, 0], [0, 1], [-1, 1], [1, 1], [1, 2]], [[0, 0], [0, 1], [1, 1], [1, 2], [1, 3]], [[0, 0], [0, 1], [-1, 1], [-1, 2], [-1, 3]], [[0, 0], [1, 0], [1, -1], [2, -1], [3, -1]], [[0, 0], [1, 0], [2, 0], [2, 1], [3, 1]], [[0, 0], [1, 0], [2, 0], [2, -1], [3, -1]], [[0, 0], [1, 0], [1, 1], [2, 1], [3, 1]], [[0, 0], [0, 1], [0, 2], [-1, 2], [-1, 3]], [[0, 0], [0, 1], [0, 2], [1, 2], [1, 3]], [[0, 0], [1, 0], [0, 1], [0, 2], [-1, 2]], [[0, 0], [1, 0], [1, 1], [1, 2], [2, 2]], [[0, 0], [1, 0], [1, 1], [2, 1], [1, 2]], [[0, 0], [1, 0], [0, 1], [0, 2], [-1, 1]], [[0, 0], [0, 1], [1, 0], [2, 0], [1, -1]], [[0, 0], [1, 0], [1, -1], [2, 0], [2, 1]], [[0, 0], [0, 1], [0, 2], [0, 3], [-1, 3]], [[0, 0], [1, 0], [1, 1], [1, 2], [1, 3]], [[0, 0], [0, 1], [1, 1], [2, 1], [3, 1]], [[0, 0], [1, 0], [2, 0], [3, 0], [3, -1]], [[0, 0], [1, 0], [2, 0], [-1, 0], [-1, 1]], [[0, 0], [1, 0], [2, 0], [1, -1], [2, -1]], [[0, 0], [0, 1], [1, 1], [0, 2], [1, 2]], [[0, 0], [0, 1], [-1, 1], [0, 2], [-1, 2]], [[0, 0], [0, 1], [0,2 ], [1, 2], [2, 2]], [[0, 0], [0, 1], [0, 2], [-1, 2], [-2, 2]], [[0, 0], [1, 0], [2, 0], [2, 1], [2, 2]], [[0, 0], [0, 1], [0, 2], [1, 0], [2, 0]]];
		}
		int[][][] voiart = {{{0}}};
		return voiart;
	}

	public static int BoardToInt(int[][] board,int a,int b) {
		int val = 0;
		for (int i=0;i<a ;i++ ) {
			for (int j=0;j<b ;j++ ) {
				val += board[i][j] * (Math.pow(2,b*i+j));
			}
		}
		return val;
	}

	public static int hammingWeight(int o){
		int sum = 0;
		while(o>0) {
			sum += o%2;
			o = o/2;
		}
		return sum;
	}


	public static int dp(int a, int b, int d){
		final long startTime = System.currentTimeMillis();		
		int[][][] gamemoves = returnPieces(d);
		int[] dpvalues = new int[(int) Math.pow(2,a*b)];

		for (int o=0;o<Math.pow(2,a*b) ;o++ ) {
			int[][] board = new int[a][b];
			int p = 10;
			if((o % (Math.pow(2,a*b-p))) == 0){
				System.out.println(o + " out of " + Math.pow(2,a*b) + " (out of " + Math.pow(2,p) + " this is " + o/Math.pow(2,a*b-p) + ")");
				System.out.println("Total execution time so far (seconds): " + ((System.currentTimeMillis() - startTime))/1000.0);						
			}

			if (((a*b-hammingWeight(o))%d)==0) {
				// System.out.println(o + " is a plausible position");
				int bitmask = o;
				for (int i=0;i<a ;i++ ) {
					for (int j=0;j<b ;j++ ) {
						board[i][j] = bitmask %2;
						// System.out.println("board[i][j]: " + board[i][j]);
						bitmask = bitmask/2;
					}
				}


				ArrayList<Integer> states = new ArrayList<>();
				for (int m=0;m<gamemoves.length; m++ ) {
					// considering the piece gamemove[m], which is a list of points.
					for (int i=0;i<a ;i++ ) {
						for (int j=0;j<b ;j++ ) {
							int[][] move = new int[d][2];
							for (int k=0;k<d ;k++ ) {
								move[k][0] = i + gamemoves[m][k][0];
								move[k][1] = j + gamemoves[m][k][1];
							}

							int[][] newboard = new int[a][b];
							for (int x=0;x<a ;x++ ) {
								for (int y=0;y<b ;y++ ) {
									newboard[x][y] = board[x][y];
								}
							}

							boolean valid = true;
							for (int k=0;k<d; k++ ) {
								if (0<= move[k][0] && move[k][0]<a && 0<=move[k][1] && (move[k][1]<b && newboard[move[k][0]][move[k][1]]==1)) {
									newboard[move[k][0]][move[k][1]]=0;
								}
								else {
									valid = false;
								}
							}
													
							if(valid) {
								states.add(dpvalues[BoardToInt(newboard,a,b)]);
							}
						}
					}
				}
				// states.add(100);
				// System.out.println(states);
				int[] statesArray = new int[states.size()];
				for (int i=0;i<states.size() ;i++ ) {
					statesArray[i] = states.get(i);
				}

				dpvalues[o] = mex(statesArray);
			}
			// System.out.println(o + " " + dpvalues[o]);
		}
		return dpvalues[(int) Math.pow(2,a*b)-1];
	}

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int a = console.nextInt();
		int b = console.nextInt();
		int d = console.nextInt();
		System.out.println(dp(a,b,d));

	}
}