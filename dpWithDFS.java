import java.util.*;

public class dpWithDFS {
	public static int mexOcc(int[] lst, int n) {
		for (int i=0;i<n ;i++ ) {
			if(lst[i]==0){
				return i;
			}
		}
		return n;
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

	public static int[] dfs(int n, int[][] movesearchInput, int d, int i, int j, int[] dpvalues, int[][] boardInput, boolean debug) {
		int[][] movesearch = new int[movesearchInput.length][movesearchInput[0].length];
		int[][] board = new int[boardInput.length][boardInput[0].length];
		for (int x=0;x<board.length ;x++ ) {
			for (int y=0;y<board[0].length ;y++) {
				movesearch[x][y] = movesearchInput[x][y];
				board[x][y] = boardInput[x][y];
			}
		}
		movesearch[i][j] = 0;
		board[i][j] = 0;
		int[] nimocc = new int[n];


		// System.out.println("Movesearch: ");
		// for (int x=0;x<board.length ;x++ ) {
		// 	for (int y=0;y<board[0].length ;y++) {
		// 		System.out.print(movesearch[x][y]+" ");
		// 	}
		// 	System.out.println();
		// }
		// System.out.println("--\n");	
		if(d==1){
			nimocc[dpvalues[BoardToInt(board,board.length, board[0].length)]]++;

			if(debug) {
				System.out.println("at level:" + d);
				for (int x=0;x<board.length ;x++ ) {
					for (int y=0;y<board[0].length ;y++) {
						System.out.print(board[x][y]+" ");
					}
					System.out.println();
				}			
				System.out.println();				
			}
		}
		else {
			int[][] directions = {{1,0},{0,1},{-1,0},{0,-1}};
			for (int c=0;c<4 ;c++ ) {
				int x = directions[c][0];
				int y = directions[c][1];

				if(0<=i+x && i+x<board.length && 0<=j+y && j+y<board[0].length && movesearch[i+x][j+y] == 1){
					movesearch[i+x][j+y] = 0;
					int[] branchValues = dfs(n, movesearch, d-1, i+x, j+y, dpvalues, board, debug);
					movesearch[i+x][j+y] = 1;
					for (int k=0;k<n ;k++ ) {
						nimocc[k] += branchValues[k];
					}
				}				
			}
	
		}
		return nimocc;
	}

	public static int dp(int a, int b, int d){
		boolean debug = false;
		boolean percents = false;
		final long startTime = System.currentTimeMillis();		
		int[] dpvalues = new int[(int) Math.pow(2,a*b)];

		for (int o=0;o<Math.pow(2,a*b) ;o++ ) {
			int[][] board = new int[a][b];
			if(percents){
				int p = 7;
				if((o % (Math.pow(2,a*b-p))) == 0){
					System.out.println(o + " out of " + Math.pow(2,a*b) + " (out of " + Math.pow(2,p) + " this is " + o/Math.pow(2,a*b-p) + ")");
					System.out.println("Total execution time so far (seconds): " + ((System.currentTimeMillis() - startTime))/1000.0);						
				}
			}

			if (((a*b-hammingWeight(o))%d)==0) {
				// System.out.println(o + " Hamming Weight: " + hammingWeight(o));
				int bitmask = o;
				for (int i=0;i<a ;i++ ) {
					for (int j=0;j<b ;j++ ) {
						board[i][j] = bitmask %2;
						// System.out.println("board[i][j]: " + board[i][j]);
						bitmask = bitmask/2;
					}
				}

				int[][] movesearch = new int[a][b];
				for (int x=0;x<a ;x++ ) {
					for (int y=0;y<b ;y++ ) {
						movesearch[x][y] = board[x][y];
					}
				}	
				
				int n = (a*b)/d;
				int[] nimocc = new int[n];
				int numLegalMoves = 0;
				for (int i=0;i<a ;i++) {
					for (int j=0;j<b ;j++ ) {
						if(movesearch[i][j]==1){
							int[] positionValues = dfs(n, movesearch, d, i, j, dpvalues, board,debug);							
							// movesearch[i][j] = 0;

							for (int k=0;k<n ;k++ ) {
								if(debug){
									System.out.print(positionValues[k] + " ");									
								}
								nimocc[k] += positionValues[k];
								numLegalMoves += positionValues[k];
							}
							if(debug){
								System.out.println();								
							}
						}
					}
				}
				if(debug || (percents && o==(int)Math.pow(2,a*b)-1)) {
					for (int i=0;i<n ;i++ ) {
						System.out.print(nimocc[i] + " ");
					}
					System.out.println("numLegalMoves: " + numLegalMoves);
					System.out.println();					
				}
				dpvalues[o] = mexOcc(nimocc,n);
			}
			// System.out.println(o + " " + dpvalues[o]);
		}
		return dpvalues[(int) Math.pow(2,a*b)-1];
	}

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		final long globalStartTime = System.currentTimeMillis();	
		// Test 1:	
		// int a = console.nextInt();
		// int b = console.nextInt();
		// int d = console.nextInt();
		// System.out.println(dp(a,b,d));

		// Test 2
		// int d = console.nextInt();
		int tablesize = 8;
		int maxboardsize = 25;
		// int[][] nimvalues = new int[tablesize+1][tablesize+1];

		for(int prod=1; prod<=maxboardsize; prod++ ){
			for(int a=1; a<=tablesize; a++ ) {
				for(int b=1; b<=tablesize; b++) {
					if(a*b==prod){
						System.out.println(a+"x"+b);
						boolean allP1 = true;
						for(int d=1; d<=a*b; d++){
							System.out.println(d+": " + dp(a,b,d));
							if(dp(a,b,d)==0){
								allP1 = false;
							}
						}
						if(allP1){
							System.out.println(a+"x"+b +" board is all P1 win");						
						}						
					}
				}
			}
		}
		// for(int a=1; a<=tablesize; a++ ) {
		// 	for(int b=1; b<=tablesize; b++) {
		// 		if(a*b<=maxboardsize) {//next available is 27
		// 			// nimvalues[a][b] = dp(a,b,d);
		// 			System.out.println(a+"x"+b);
		// 			boolean allP1 = true;
		// 			for(int d=1; d<=maxboardsize; d++){
		// 				System.out.println(d+": " + dp(a,b,d));
		// 				if(dp(a,b,d)==0){
		// 					allP1 = false;
		// 				}
		// 			}
		// 			if(allP1){
		// 				System.out.println(a+"x"+b +" board is all P1 win");						
		// 			}
		// 		}
		// 	}
		// }
		// for(int a=1; a<=tablesize; a++ ) {
		// 	System.out.print(a+" & ");
		// 	for(int b=1; b<=tablesize; b++) {
		// 		if(a*b<=maxboardsize) {
		// 			if(a<=b){
		// 				System.out.print(nimvalues[a][b]);
		// 			}
		// 			else {
		// 				System.out.print("\\s{" +nimvalues[a][b]+"}");
		// 			}
		// 		}
		// 		if(b<tablesize){
		// 			System.out.print(" & ");
		// 		}				
		// 	}
		// 	System.out.println("\\\\");
		// }
		System.out.println("Total execution time (seconds): " + ((System.currentTimeMillis() - globalStartTime))/1000.0);

		// Test 3


	}

}