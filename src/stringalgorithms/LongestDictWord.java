package stringalgorithms;

import java.util.ArrayList;
import java.util.List;

public class LongestDictWord {

	public static void main(String[] args) {
		String[] str = {"m","mo","moc","moch","mocha","l","la","lat","latt","latte","c","ca","cat"};
		System.out.println(longestWord(str));
	}
	public static String longestWord(String[] words) {
		String min = "";
		
		int i=0;
		List<String> temp = new ArrayList<>();
		temp.add("");
		boolean count = true;
		while(count) {
			count = false;
			for (int j=0; j <words.length; j++) {
				if(words[j].length()==i+1 && temp.contains(words[j].substring(0, words[j].length()-1))) {
					temp.add(words[j]);
					count = true;
				}
			}
			if(count)
				i++;
		}
		
		for (String string : temp) {
			if(string.length()==i) {
				min = string;
			}
		}
		
		for (String string : temp) {
			if(string.length()==i && string.compareTo(min)<0) {
				min = string;
			}
		}
		return min;
		
    }
}
