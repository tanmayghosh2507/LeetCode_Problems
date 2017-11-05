package stringalgorithms;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicatesFromCharArray {

	public static void main(String[] args) {
		String str = "cbacdcbc";
		System.out.println(removeDuplicateLetters(str));
	}
	
	public static String removeDuplicateLetters(String s) {
        Set<Character> charSet = new HashSet<>();
        for(int i=0; i<s.length(); i++)
        	charSet.add(s.charAt(i));
        String str = "";
        for (Character character : charSet) {
			str = str.concat(character+"");
		}
        return str;
    }
}
