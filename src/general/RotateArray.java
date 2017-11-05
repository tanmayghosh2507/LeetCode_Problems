package general;

public class RotateArray {

	public static void main(String[] args) {
		int[] nums = { 1 };
		rotate(nums, 10);
		for (int i : nums) {
			System.out.println(i);
		}
	}

	public static void rotate(int[] nums, int k) {
		if (nums.length == 0)
			return;
		k = k % (nums.length);
		rotateArray(nums, 0, nums.length - 1);
		rotateArray(nums, 0, k - 1);
		rotateArray(nums, k, nums.length - 1);
	}

	public static void rotateArray(int[] nums, int start, int end) {
		int mid = (int) Math.ceil((start + end) / 2);
		for (int i = start, j = end; i <= mid && j >= mid; i++, j--) {
			int temp = nums[i];
			nums[i] = nums[j];
			nums[j] = temp;
		}
	}

}
