package divideandconquer;

public class Medianof2Sorted {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		if (nums1.length > nums2.length)
			return getMedian(nums1, nums1.length, nums2, nums2.length);
		return getMedian(nums2, nums2.length, nums1, nums1.length);
	}

	public double getMedian(int[] big, int bSize, int[] small, int sSize) {
		if (sSize == 0 && bSize == 0)
			return -1;
		if (sSize == 0 && bSize % 2 == 0)
			return (big[bSize / 2] + big[(bSize - 1) / 2]) / 2;
		if (sSize == 0 && bSize % 2 == 1)
			return big[(bSize - 1) / 2];
		if (sSize == 1 && bSize == 1)
			return (big[0] + small[0]) / 2;
		if (sSize == 1 && bSize % 2 == 1) {
			int mid = (bSize - 1) / 2;
			if ((big[mid] >= small[0] && big[mid - 1] < small[0]) || (big[mid] <= small[0] && big[mid + 1] > small[0]))
				return (big[mid] + small[0]) / 2;
			else if (big[mid - 1] > small[0])
				return (big[mid - 1] + big[mid]) / 2;
			else
				return (big[mid + 1] + big[mid]) / 2;

		}
		if (sSize == 1 && bSize % 2 == 0) {
			int mid = bSize / 2;
			if (big[mid - 1] >= small[0])
				return big[mid - 1];
			else if (big[mid] < small[0])
				return big[mid];
			else
				return small[0];

		}
		if (sSize == 2 && bSize == 2) {
			return medianof4elements(big[0], big[1], small[0], small[1]);
		}
		if (sSize == 2 && bSize % 2 == 0) {
			return medianof3elements(big[bSize / 2], Math.max(small[0], big[bSize / 2 - 1]),
					Math.min(small[1], big[bSize / 2 + 1]));
		}
		if (sSize == 2 && bSize % 2 == 1) {
			return medianof4elements(big[bSize / 2], big[bSize / 2 - 1], Math.max(small[0], big[bSize / 2 - 2]),
					Math.min(small[1], big[bSize / 2 + 1]));
		}
		int smallIndex = (sSize - 1) / 2;
		int bigIndex = (bSize - 1) / 2;
		if (big[bigIndex] >= small[smallIndex]) {
			int[] temp = new int[sSize - smallIndex];
			for (int i = 0; i < sSize - smallIndex - 1; i++) {
				temp[i] = small[smallIndex];
				smallIndex++;
			}
			return getMedian(big, bigIndex, temp, sSize - smallIndex);
		} else {
			int[] temp = new int[bSize - bigIndex];
			for (int i = 0; i < bSize - bigIndex - 1; i++) {
				temp[i] = big[bigIndex];
				bigIndex++;
			}
			return getMedian(temp, bSize - bigIndex, small, smallIndex);
		}

	}

	public double medianof4elements(int a, int b, int c, int d) {
		int max = Math.max(a, Math.max(b, Math.max(c, d)));
		int min = Math.min(a, Math.min(b, Math.min(c, d)));
		return (a + b + c + d - max - min) / 2;
	}

	public double medianof3elements(int a, int b, int c) {
		int max = Math.max(a, Math.max(b, c));
		int min = Math.min(a, Math.min(b, c));
		return a + b + c - max - min;
	}

}
