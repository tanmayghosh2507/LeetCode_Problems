package stringalgorithms;

public class LongestPalindromeSubStringSubSequence {

	public static void main(String[] args) {
		String str = "bbbab";
		LongestPalindromeSubStringSubSequence longestPalindromeSubString = new LongestPalindromeSubStringSubSequence();
		System.out.println(longestPalindromeSubString.longestPalindromeSubString(str));

	}

	public String longestPalindromeSubString(String str) {
		Boolean dp[][] = new Boolean[str.length()][str.length()];

		for (int i = 0; i < str.length(); i++) {
			for (int j = 0; j < str.length(); j++) {
				dp[i][j] = false;
			}
		}

		int maxLength = 1;
		int startIndex = 0;

		// All single characters are palindrome
		for (int i = 0; i < str.length(); i++) {
			dp[i][i] = true;
		}

		// All length 2 substrings
		for (int i = 0; i < str.length() - 1; i++) {
			if (str.charAt(i) == str.charAt(i + 1)) {
				dp[i][i + 1] = true;
				startIndex = i;
				maxLength = 2;
			}
		}

		for (int len = 3; len <= str.length(); len++) { // len: length of substring
			for (int start = 0; start < str.length() - len + 1; start++) {
				int end = start + len - 1; // j:end index
				if (dp[start + 1][end - 1] && str.charAt(start) == str.charAt(end)) {
					dp[start][end] = true;
					if (len > maxLength) {
						startIndex = start;
						maxLength = len;
					}
				}
			}
		}
		return str.substring(startIndex, startIndex + maxLength);
	}

	public int longestPalindromeSubSequence(String str) {
		int dp[][] = new int[str.length()][str.length()];

		for (int i = 0; i < str.length(); i++) {
			for (int j = 0; j < str.length(); j++) {
				dp[i][j] = 0;
			}
		}

		// All single characters are palindrome
		for (int i = 0; i < str.length(); i++) {
			dp[i][i] = 1;
		}

		for (int len = 2; len <= str.length(); len++) { // len: length of substring
			for (int start = 0; start < str.length() - len + 1; start++) {
				int end = start + len - 1;
				if (str.charAt(start) == str.charAt(end) && len == 2)
					dp[start][end] = 2;
				else if (str.charAt(start) == str.charAt(end))
					dp[start][end] = dp[start + 1][end - 1] + 2;
				else
					dp[start][end] = Math.max(dp[start + 1][end], dp[start][end - 1]);
			}
		}
		return dp[0][str.length() - 1];
	}
}
