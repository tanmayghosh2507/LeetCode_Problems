package StringAlgorithms;

public class StringReversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "Tanmay";
		reverse(str);
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

}
