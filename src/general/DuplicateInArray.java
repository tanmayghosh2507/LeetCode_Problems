package general;

import java.util.ArrayList;
import java.util.List;

public class DuplicateInArray {

	public static void main(String[] args) {
		int[] arr = {5,5};
		if(arr.length == 0) {
			System.exit(0);
		}
		int max = arr[0], min = arr[0];
		
		for (int i=0; i<arr.length; i++) {
			if(arr[i]>max) {
				max = arr[i];
			}
			if(arr[i]<min) {
				min = arr[i];
			}
		}
		
		if(min < 0 ) {
			for(int i=0; i<arr.length; i++) {
				arr[i] = arr[i] - min;
			}
		} else
			min=0;
		
		int[] dup = new int[max-min+1];
		
		List<Integer> newlist = new ArrayList<Integer>(); 
		
		for(int i=0; i<dup.length; i++) {
			dup[i] = 0;
		}
		
		for(int i=0; i<arr.length; i++) {
			dup[arr[i]] = dup[arr[i]] +1;
		}
		
		for(int i=0; i<dup.length; i++) {
			if(dup[i] > 1) {
				newlist.add(i+min);
			}
		}
		
		for (Integer integer : newlist) {
			System.out.println(integer);
		}
	}

}
