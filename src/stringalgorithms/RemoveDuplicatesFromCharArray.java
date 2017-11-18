package stringalgorithms;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicatesFromCharArray {

	public static void main(String[] args) {
		// String str = "cbacdcbc";
		String str = "aaaa";
		System.out.println(removeDuplicates2(str));
	}

	public static String removeDuplicateLetters(String s) {
		Set<Character> charSet = new HashSet<>();
		for (int i = 0; i < s.length(); i++)
			charSet.add(s.charAt(i));
		String str = "";
		for (Character character : charSet) {
			str = str.concat(character + "");
		}
		return str;
	}

	public static String removeDuplicates2(String str) {
		int len = str.length();
		boolean[] cArray = new boolean[256];

		for (int i = 0; i < len; i++) {
			if (cArray[str.charAt(i)]) {
				str = str.substring(0, i) + str.substring(i + 1, len);
				i--;
				len--;
			} else
				cArray[str.charAt(i)] = true;
		}
		return str;
	}
}
