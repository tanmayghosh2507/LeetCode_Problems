package stringalgorithms;

public class ReverseWords {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "My name is Tanmay.";
		System.out.println(reverseWords(str));
	}

	public static String reverseWords(String s) {
		s = s.trim();
		boolean stop = false;
		if(s.charAt(s.length()-1)=='.')
			stop = true;
		int count=0;
		if (s.length() == 0 || !s.contains(" "))
			return s.trim();
		
		for (int i = 0; i < s.length() - 1; i++) {
			if (s.charAt(i) == ' ') {
				if (!s.substring(0, i + 1).equals(" "))
					if(stop && count ==0) {
						return reverseWords(s.substring(i + 1, s.length()-1)) + " " + s.substring(0, i)+".";
					} else {
						return reverseWords(s.substring(i + 1, s.length())) + " " + s.substring(0, i);
					}
				else
					return reverseWords(s.substring(i + 1, s.length()));
			}
		}
		return "";
	}

}
