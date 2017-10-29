package StringAlgorithms;

public class ShortestPalindrome {

	public static void main(String[] args) {
		String str = "aabc";
		String rev = "", newrev = "";
		for (int i = 0; i < str.length(); i++) {
			if (isPalindrome(str.substring(i, str.length()))) {
				break;
			} else {
				rev += str.charAt(i);
			}
		}

		for (int i = rev.length() - 1; i >= 0; i--) {
			newrev += rev.charAt(i);
		}

		System.out.println(str + newrev);
	}

	public static Boolean isPalindrome(String str) {
		for (int i = 0; i < str.length() / 2; i++) {
			if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
				return false;
			}
		}
		return true;
	}

}
