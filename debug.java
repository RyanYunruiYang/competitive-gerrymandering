public class debug {
	public static int replace(int[][] inputBoard) {
		int[][] board = new int[5][5];
		for (int i=0; i<5;i++ ) {
			for (int j=0;j<5 ;j++ ) {
				board[i][j] = inputBoard[i][j];
			}
		}
		board[1][1] = 2;
		return 50;
	}
	public static void main(String[] args) {
		// dp test = new dp();
		// // int[][][] states = {{{0,1,1,1,3,4,5}, {0}}, {{0}}};
		// // System.out.println(test.mex(states[0][0])); //mex works.

		// int d = 5;
		// int[][][] gamemoves = test.returnPieces(d);
		// System.out.println(gamemoves.length);
		// for (int x=0;x<gamemoves.length; x++ ) {
		// 	if(x==31){
		// 		System.out.print("[");
		// 		for (int i=0;i<d ;i++ ) {
		// 			System.out.print("(");
		// 			for (int j=0;j<2 ;j++ ) {
		// 				System.out.print(gamemoves[x][i][j]+", ");
		// 			}
		// 			System.out.print("),");
		// 		}
		// 		System.out.print("], ");
		// 		System.out.println();					
		// 	}
		
		// }

		int[][] board = new int[5][5];

		replace(board);

		for (int i=0;i<board.length ;i++ ) {
			for (int j=0;j<board[0].length ;j++ ) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
}