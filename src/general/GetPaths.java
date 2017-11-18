package general;

/*
 * Given a n X n matrix. Starting at (0,0), number of ways to reach (n,n)
 */
public class GetPaths {

	public static void main(String[] args) {
		System.out.println(getPaths(4, 0, 0));
	}

	public static int getPaths(int n, int x, int y) {
		if (x == n - 1 && y == n - 1)
			return 1;
		if (x == n - 1)
			return getPaths(n, x, y + 1);
		if (y == n - 1)
			return getPaths(n, x + 1, y);
		return getPaths(n, x + 1, y) + getPaths(n, x, y + 1);
	}
}
