package DynamicProgramming;

public class SubsetSum {

	public static void main(String[] args) {
		int[] set = { 3, 34, 4, 12, 2 };
		int len = set.length;
		int sum = 9;
		boolean[][] dp = new boolean[len][sum + 1];

		for (int i = 0; i < len; i++) {
			dp[i][0] = true;
		}

		for (int i = 0; i < len; i++) {
			for (int j = 1; j <= sum; j++) {
				dp[i][j] = false;
			}
		}

		for (int j = 1; j <= sum; j++) {
			if (j == set[0])
				dp[0][j] = true;
			else
				dp[0][j] = false;
		}

		for (int i = 1; i < len; i++) {
			for (int j = 0; j <= sum; j++) {
				dp[i][j] = dp[i - 1][j];

				if (set[i] <= j) {
					dp[i][j] = dp[i - 1][j - set[i]] || dp[i - 1][j];
				}
			}
		}

		if (dp[len - 1][sum]) {
			int i = len - 1;
			int j = sum;
			System.out.print("SubSet: { ");
			while (i > 0 && j >= 0) {
				if (dp[i - 1][j]) {
					if (i == 1)
						System.out.print(set[i - 1] + " ");
					i--;
				} else if (dp[i - 1][j - set[i]]) {
					System.out.print(set[i] + " ");
					j = j - set[i];
					i--;
				}
			}
			System.out.println("}");
		} else {
			System.out.println("Sum not possible with this subset!");
		}

	}

}
