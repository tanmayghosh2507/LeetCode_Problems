package StringAlgorithms;

public class LongestCommonSubStringSubSequence {

	public static void main(String[] args) {
		String s1="abcdaf";
		String s2 = "acbcf";
		
		System.out.println(longestCommomSubstring(s1, s2));
		System.out.println(longestCommonSubSequence(s1, s2));
	}
	
	public static int longestCommomSubstring(String s1, String s2) {
		int [][] dp = new int[s1.length()+1][s2.length()+1];
		for(int i=0; i<s1.length()+1; i++) {
			for(int j=0; j<s2.length()+1; j++) {
				if(i==0 || j==0) {
					dp[i][j] = 0;
				}
				else if(s1.charAt(i-1)==s2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] +1;
				} else
					dp[i][j] = 0;
			}
		}
		
		int max = 0;
		for(int i=0; i<s1.length()+1; i++) {
			for(int j=0; j<s2.length()+1; j++) {
				if(dp[i][j] > max)
					max = dp[i][j];
			}
		}
		return max;
	}

	public static int longestCommonSubSequence(String s1, String s2) {
		int [][] dp = new int[s1.length()+1][s2.length()+1];
		for(int i=0; i<s1.length()+1; i++) {
			for(int j=0; j<s2.length()+1; j++) {
				if(i==0 || j==0) {
					dp[i][j] = 0;
				}
				else if(s1.charAt(i-1)==s2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] +1;
				} else
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}
		return dp[s1.length()][s2.length()];
	}
}
