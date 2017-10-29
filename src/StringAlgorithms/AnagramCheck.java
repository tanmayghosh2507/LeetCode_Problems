package StringAlgorithms;

import java.util.Arrays;

public class AnagramCheck {

	public static void main(String[] args) {
		String str1 = "Tanmay";
		String str2 = "yTanma";

		if (str1.length() == str2.length()) {
			int count[] = new int[256];
			Arrays.fill(count, 0);

			for (int i = 0; i < str1.length(); i++) {
				count[str1.charAt(i)] = 1;
			}
			for (int i = 0; i < str2.length(); i++) {
				count[str2.charAt(i)] = 0;
			}
			for (int i = 0; i < count.length; i++) {
				if (count[i] == 1) {
					System.out.println("Strings are not anagrams!");
					System.exit(0);
				}
			}
		} else {
			System.out.println("Strings are not anagrams!");
			System.exit(0);
		}
		System.out.println("Strings are Anagrams!");
	}

}
