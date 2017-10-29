package DivideAndConquer;

public class KthLargest {

	public static void main(String[] args) {
		int[] nums = {3,2,1,5,4,6};
		KthLargest kthLargest = new KthLargest();
		System.out.println(kthLargest.findKthLargest(nums, 3));
	}
	
	public int findKthLargest(int[] nums, int k) {
		return kthLargest(nums, 0, nums.length-1, k);
    }
	
	private int kthLargest(int[] nums, int low, int high, int k) {
		if(low <= high) {
			int index = partition(nums,low, high);
			if(index == nums.length-k)
				return nums[index];
			else if(index > nums.length - k)
				return kthLargest(nums, low, index-1, k);
			else
				return kthLargest(nums, index+1, high, k);
		}
		return -1;
	}
	
	private int partition(int[] nums, int low, int high) {
		int pivot = nums[low];
		int i = low+1;
		for(int j=i; j<=high; j++) {
			if(nums[j] <= pivot) {
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
				i++;
			}
		}
		int temp = nums[i-1];
		nums[i-1] = nums[low];
		nums[low] = temp;
		return i-1;
	}
}
