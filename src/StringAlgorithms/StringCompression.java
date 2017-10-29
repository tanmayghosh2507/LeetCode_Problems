package StringAlgorithms;

public class StringCompression {

	public static void main(String[] args) {
		StringCompression compression = new StringCompression();
		char[] chars = { 'b', '0', 'l', 'A', ']', '+', 'O', '5', 'j', '4' };
		// char[] chars = {'a','a','b','b','c','c','c'};
		System.out.println(compression.compress(chars));
		for (int i = 0; i < chars.length; i++)
			System.out.print(chars[i] + " ");
	}

	public int compress(char[] chars) {
		int[] bitarr = new int[147];

		for (int i : bitarr)
			bitarr[i] = 0;

		int length = chars.length;

		for (int i = 0; i < chars.length; i++) {
			char ch = chars[i];
			bitarr[ch]++;
			if (bitarr[ch] > 1)
				chars[i] = 0;
		}

		int count = 0;
		for (char ch : chars) {
			if (bitarr[ch] > 1) {
				chars[count] = ch;
				count++; // for the character itself
				int num = bitarr[ch];
				int rev = 0;
				while (num != 0) {
					rev = rev * 10 + num % 10;
					num = num / 10;
				}
				while (rev != 0) {
					chars[count] = (char) (rev % 10 + 48);
					rev = rev / 10;
					count++;
				}
			} else if (bitarr[ch] == 1) {
				chars[count] = (char) (ch);
				count++;
			}
		}
		for (int i = count; i < length; i++) {
			chars[count] = 0;
		}
		return count;
	}
}
