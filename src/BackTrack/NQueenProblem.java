package BackTrack;

public class NQueenProblem {
	
	static int N = 8;

	public static void main(String[] args) {
		NQueenProblem nQueenProblem = new NQueenProblem();
		int[][] board = new int[N][N];
		
		//Initialization
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				board[i][j]=0;
			}
		}
		
		if(solveNQueen(board, 0) == false)
			System.out.println("Solution doesn't exist!");
		else
			nQueenProblem.print(board);
	}

	void print(int[][] board) {
		for(int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
	}

	private static boolean solveNQueen(int[][] board, int currentCol) {
		
		//Base Case
		if(currentCol >= N) {
			return true;
		}
		
		//Recursion for every column
		for(int currentRow=0; currentRow<N; currentRow++) {
			if(isSafe(board, currentRow, currentCol)) {
				board[currentRow][currentCol] = 1;
				
				if(solveNQueen(board, currentCol + 1))
					return true;
				
				board[currentRow][currentCol] = 0;	//BackTrack
			}
		}
		return false;
	}

	private static boolean isSafe(int[][] board, int currentRow, int currentCol) {
		
		//Check left columns
		for(int i=0; i<currentCol; i++) {
			if(board[currentRow][i]==1)
				return false;
		}
		
		//Check left upper diagonal
		for(int i=currentRow, j=currentCol; i>=0 && j>=0; i--, j--) {
			if(board[i][j]==1)
				return false;
		}
		
		//Check left lower diagonal
		for(int i=currentRow, j=currentCol; i<N && j>=0; i++, j--) {
			if(board[i][j]==1)
				return false;
		}
		
		return true;
	}

}
