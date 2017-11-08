package general;

public class SortColors {

	public static void main(String[] args) {

	}

	public static void sortColors(int[] nums) {
		int index0 = 0;
		int index2 = nums.length - 1;
		int index = 0;
		while (index <= index2) {
			if (nums[index] == 0) {
				nums[index] = nums[index0];
				nums[index0] = 0;
				index0++;
			} else if (nums[index] == 2) {
				nums[index] = nums[index2];
				nums[index2] = 2;
				index2--;
				index--;
			}
			index++;
		}
	}

}
