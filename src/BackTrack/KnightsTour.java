package backtrack;

public class KnightsTour {

	static int N = 8;

	public static void main(String[] args) {
		NQueenProblem nQueenProblem = new NQueenProblem();

		int[][] board = new int[N][N];

		// Initialization
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = -1;
			}
		}

		// Moves of a Knight
		int movex[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
		int movey[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
		board[0][0] = 0;

		if (!solveKnightsTour(board, movex, movey, 1, 0, 0))
			System.out.println("Solution doesn't exist!");
		else
			nQueenProblem.print(board);
	}

	private static boolean solveKnightsTour(int[][] board, int[] movex, int[] movey, int moveCount, int x, int y) {

		// Base Case
		if (moveCount == N * N)
			return true;

		// Recursion for each move
		for (int i = 0; i < N; i++) {
			int nextX = x + movex[i];
			int nextY = y + movey[i];
			if (isSafe(board, nextX, nextY)) {
				board[nextX][nextY] = moveCount;
				if (solveKnightsTour(board, movex, movey, moveCount + 1, nextX, nextY))
					return true;
				else
					board[nextX][nextY] = -1; // BackTrack
			}
		}
		return false;
	}

	private static boolean isSafe(int[][] board, int nextX, int nextY) {
		return (nextX < N) && (nextX >= 0) && (nextY >= 0) && (nextY < N) && (board[nextX][nextY] == -1);
	}

}
