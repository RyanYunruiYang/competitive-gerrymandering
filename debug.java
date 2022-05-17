public class debug {
	public static void main(String[] args) {
		dp test = new dp();
		int[][][] states = {{{0,1,1,1,3,4,5}, {0}}, {{0}}};
		System.out.println(test.mex(states[0][0])); //mex works.

		int d = 2;
		int[][][] gamemoves = test.returnPieces(d);
		
		for (int x=0;x<gamemoves.length; x++ ) {
			System.out.print("[");
			for (int i=0;i<d ;i++ ) {
				System.out.print("(");
				for (int j=0;j<d ;j++ ) {
					System.out.print(gamemoves[x][i][j]+", ");
				}
				System.out.print("),");
			}
			System.out.print("], ");
		}
		System.out.println();

	}
}