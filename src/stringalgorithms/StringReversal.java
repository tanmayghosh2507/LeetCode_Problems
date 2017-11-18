package stringalgorithms;

public class StringReversal {

	public static void main(String[] args) {
		String str = "Tanmay";
		System.out.println(reverse2(str));
	}

	public static void reverse(String str) {
		int size = str.length();
		if (size <= 1 || str.isEmpty()) {
			System.out.println(str);
		} else {
			System.out.print(str.charAt(size - 1));
			reverse(str.substring(0, size - 1));
		}
	}

	public static String reverse2(String str) {
		int size = str.length();
		if (size <= 1 || str.isEmpty())
			return str;

		return str.charAt(size - 1) + reverse2(str.substring(0, size - 1));
	}

}
