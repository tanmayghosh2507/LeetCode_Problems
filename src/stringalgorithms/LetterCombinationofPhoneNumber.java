package stringalgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationofPhoneNumber {

	public static void main(String[] args) {
		String digit = "";
		System.out.println(letterCombinations(digit));
	}

	public static List<String> letterCombinations(String digits) {
		List<String> current = new ArrayList<>();
		if (digits.length() == 0)
			return current;
		Map<String, List<String>> map = new HashMap<>();
		List<String> list2 = new ArrayList<>();
		list2.add("a");
		list2.add("b");
		list2.add("c");
		map.put("2", list2);
		List<String> list3 = new ArrayList<>();
		list3.add("d");
		list3.add("e");
		list3.add("f");
		map.put("3", list3);
		List<String> list4 = new ArrayList<>();
		list4.add("g");
		list4.add("h");
		list4.add("i");
		map.put("4", list4);
		List<String> list5 = new ArrayList<>();
		list5.add("j");
		list5.add("k");
		list5.add("l");
		map.put("5", list5);
		List<String> list6 = new ArrayList<>();
		list6.add("m");
		list6.add("n");
		list6.add("o");
		map.put("6", list6);
		List<String> list7 = new ArrayList<>();
		list7.add("p");
		list7.add("q");
		list7.add("r");
		list7.add("s");
		map.put("7", list7);
		List<String> list8 = new ArrayList<>();
		list8.add("t");
		list8.add("u");
		list8.add("v");
		map.put("8", list8);
		List<String> list9 = new ArrayList<>();
		list9.add("w");
		list9.add("x");
		list9.add("y");
		list9.add("z");
		map.put("9", list9);

		for (String alphabet : map.get(digits.charAt(0) + "")) {
			current.add(alphabet);
		}

		for (int i = 1; i < digits.length(); i++) {
			String digit = digits.charAt(i) + "";
			for (String string : new ArrayList<String>(current)) {
				for (String alphabet : map.get(digit)) {
					current.add(string + alphabet);
				}
				current.remove(string);
			}
		}

		return current;
	}
}
