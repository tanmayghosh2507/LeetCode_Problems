package general;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicates {
	public static void main(String[] args) {
		int[] nums = { 1, 1, 2 };
		System.out.println(removeDuplicatesUnSorted(nums));
	}

	public static int removeDuplicatesSorted(int[] nums) {
		if (nums.length == 0 || nums.length == 1)
			return nums.length;
		int count = 0;
		int prev = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != prev) {
				count++;
				nums[count] = nums[i];
				prev = nums[i];
			}
		}
		return count + 1;
	}

	public static int removeDuplicatesUnSorted(int[] nums) {
		if (nums.length == 0 || nums.length == 1)
			return nums.length;
		Set<Integer> intHash = new HashSet<>();
		for (Integer integer : nums) {
			intHash.add(integer);
		}
		for (Integer integer : intHash) {
			System.out.println(integer);
		}
		return intHash.size();
	}

}
