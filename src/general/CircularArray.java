package general;

public class CircularArray {

	public static void main(String[] args) {
		int[] arr = { 1, -2 };
		System.out.println(circularArrayLoop(arr));

	}

	public static boolean circularArrayLoop(int[] nums) {
		int count = 0;
		int dir = 0;
		int size = nums.length;
		if (size == 0)
			return false;
		for (int i = 0; i < size; i++) {
			int temp = nums[count];
			if (nums[count] == 0 && dir % 2 == 0) {
				return true;
			}
			nums[count] = 0;
			if (temp == size || temp == -size)
				return false;
			count = (count + temp) % size;
			if (count < 0) {
				count = count + size;
				dir++;
			}
		}
		for (int i = 0; i < size; i++) {
			if (nums[i] != 0)
				return false;
		}
		return true;
	}
}
