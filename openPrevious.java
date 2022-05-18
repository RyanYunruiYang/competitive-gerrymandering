import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.*;

public class openPrevious {
	public static void main(String[] args) throws FileNotFoundException, IOException{
        BufferedReader console = new BufferedReader(new InputStreamReader(new FileInputStream("output.txt")));
		StringTokenizer tokenizer = new StringTokenizer(console.readLine());        

		int dpsize = (int) Math.pow(2,25);
        int[] dp = new int[dpsize];
        for (int i=0;i<dpsize ;i++ ) {
        	dp[i] = Integer.parseInt(tokenizer.nextToken());

        	// if(dp[i]==4){
        	// 	System.out.println(i);
        	// }
        }
		dp worker = new dp();
		int d = 5;
		int[][][] gamemoves = worker.returnPieces(d);
		int o = dpsize-1;
		int a = 5;
		int b = 5;
		int[][] board = new int[a][b];
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

					// System.out.println(o + " " + valid);
					// System.out.println();
					if(valid) {
						states.add(dp[worker.BoardToInt(newboard,a,b)]);
						if(dp[worker.BoardToInt(newboard,a,b)]==1) {
							System.out.println(dp[worker.BoardToInt(newboard,a,b)]);						
							for (int x=0;x<a ;x++ ) {
								for (int y=0;y<b ;y++ ) {
									System.out.print(newboard[x][y]+" ");
								}
								System.out.println();
							}	
							System.out.println();	
						}
					}			

				}
			}
		}
		// states.add(100);
		// System.out.println(states);
		int[] appearances = new int[5];		
		int[] statesArray = new int[states.size()];
		for (int i=0;i<states.size() ;i++ ) {
			statesArray[i] = states.get(i);
			appearances[statesArray[i]]++;			
		}

		System.out.println(worker.mex(statesArray));
		for (int i=0;i<5 ;i++ ) {
			System.out.println(i + ": " + appearances[i]);
		}

		// System.out.println(o + " " + dpvalues[o]);		

        System.out.println(dp[(int) Math.pow(2,25)-1]);
	}
}