package general;

public class MaxSubArraySum {

	public static void main(String[] args) {
		//int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
		int[] arr = {-2, -1, 1};
		MaxSubArraySum maxSubArraySum = new MaxSubArraySum();
		System.out.println(maxSubArraySum.maxSubArray(arr));
	}
	
	public int maxSubArray(int[] nums) {
		int max = 0;
		int negMax = nums[0];
		int current = 0;
		for(int i=0; i<nums.length; i++) {
			current = current + nums[i];
			if(current<=0) {
				current = 0;
				if(negMax<nums[i]) {
					negMax = nums[i];
				}
			}
			else if (current>max)
				max = current;
		}
		if(max==0)
			return negMax;
		return max;
    }
}
