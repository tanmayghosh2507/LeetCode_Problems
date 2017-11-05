package stringalgorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RemoveComments {

	public static void main(String[] args) {
		String[] str = { "struct Node{", "    /*/ declare members;/**/", "    int size;", "    /**/int val;", "};" };
		String num = "    /*/ declare members;/**/";
		System.out.println(num.indexOf("/*"));
		System.out.println(num.indexOf("*/"));
		System.out.print(removeComments(str));
	}

	public static List<String> removeComments(String[] source) {
		List<String> str = new ArrayList<>();
		Stack<String> stack = new Stack<>();
		boolean opencomment = false;
		String cut2 = "";
		for (String string : source) {
			if (string.contains("//") && !opencomment) {
				String cut = string.substring(0, string.indexOf("//"));
				if (cut != null && cut != "")
					str.add(cut);
				if (str.contains("/*")) {
					stack.push("O");
					opencomment = true;
				}
			} else if (string.contains("/*") && string.contains("*/") && !opencomment
					&& string.indexOf("/*") < string.indexOf("*/")) {
				String cut = string.substring(0, string.indexOf("/*"))
							+ string.substring(string.substring(string.indexOf("/*")+2, string.length()).indexOf("*/") + 2, string.length());
				if (cut != null || cut != "")
					str.add(cut);
			} else if (string.contains("/*")) {
				if (!opencomment)
					cut2 = string.substring(0, string.indexOf("/*"));
				stack.push("O");
				opencomment = true;
			} else if (string.contains("*/")) {
				if (!stack.isEmpty())
					stack.pop();
				if (stack.isEmpty())
					opencomment = false;
				String cut = cut2 + string.substring(string.indexOf("*/") + 2, string.length());
				if (cut != null || cut != "")
					str.add(cut);
			} else if (!opencomment) {
				str.add(string);
			}
		}
		List<String> t = new ArrayList<>();
		t.add("");
		str.removeAll(t);
		return str;
	}
}
