package divideandconquer;

// 2D Matrix Search II LeetCode.

public class MatrixSearch2D {

	public static void main(String[] args) {
		int[][] matrix = { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 50 } };
		MatrixSearch2D matrixSearch2D = new MatrixSearch2D();
		System.out.println(matrixSearch2D.searchMatrix1(matrix, 7));
	}

	public boolean searchMatrix2(int[][] matrix, int target) {
		if (matrix == null || matrix.length < 1 || matrix[0].length < 1)
			return false;

		int col = matrix[0].length - 1;
		int row = 0;

		while (row >= 0 && col >= 0 && row < matrix.length && col < matrix[0].length) {
			if (target == matrix[row][col])
				return true;
			else if (target > matrix[row][col])
				row++;
			else
				col--;
		}
		return false;
	}

	public boolean searchMatrix1(int[][] matrix, int target) {
		if (matrix == null || matrix.length < 1 || matrix[0].length < 1)
			return false;

		int row = BSearchRec(matrix, target, 0, matrix.length - 1);
		if (row == -1)
			return false;
		else
			return BLinearSearchRec(matrix[row], target, 0, matrix[0].length - 1);
	}

	private int BSearchRec(int[][] mat, int target, int low, int high) {
		if (low <= high) {
			int mid = (low + high) / 2;
			if (mat[mid][mat[0].length - 1] >= target) {
				if (mid == 0 || mat[mid - 1][mat[0].length - 1] < target)
					return mid;
				else
					return BSearchRec(mat, target, low, mid - 1);
			} else
				return BSearchRec(mat, target, mid + 1, high);
		}
		return -1;
	}

	private boolean BLinearSearchRec(int[] row, int target, int low, int high) {
		if (low <= high) {
			int mid = (low + high) / 2;
			if (row[mid] == target)
				return true;
			else if (row[mid] >= target)
				return BLinearSearchRec(row, target, low, mid - 1);
			else
				return BLinearSearchRec(row, target, mid + 1, high);
		}
		return false;
	}
}
