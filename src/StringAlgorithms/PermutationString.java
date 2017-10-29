package StringAlgorithms;

public class PermutationString {

	public static void main(String[] args) {
		String str = "abc";
		printPermutations("", str);
	}

	public static void printPermutations(String prefix, String str) {

		if (str.length() == 0)
			System.out.println(prefix);
		else {
			for (int i = 0; i < str.length(); i++) {
				printPermutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, str.length()));
			}
		}
	}

}
