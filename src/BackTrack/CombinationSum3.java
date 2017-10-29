package BackTrack;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum3 {

	public static void main(String[] args) {
		CombinationSum3 combinationSum3 = new CombinationSum3();
		List<Integer> temp = new ArrayList<Integer>();
		List<List<Integer>> fin = new ArrayList<>();
		temp.add(2);
		temp.add(4);
		fin.add(temp);
		combinationSum3.combinationSum3(1, 2);
		// Driver function. Show the results.
	}

	public List<List<Integer>> combinationSum3(int k, int n) {
		List<Integer> temp = new ArrayList<Integer>();
		List<List<Integer>> fin = new ArrayList<>();
		combinationRec(fin, temp, k, 1, n);
		return fin;
	}

	private void combinationRec(List<List<Integer>> fin, List<Integer> temp, int k, int start, int n) {
		if (temp.size() == k && n == 0) {
			List<Integer> li = new ArrayList<>(temp);
			fin.add(li);
			return;
		}
		for (int i = start; i <= 9; i++) {
			temp.add(i);
			combinationRec(fin, temp, k, i + 1, n - i);
			temp.remove(temp.size() - 1);
		}
	}

}
