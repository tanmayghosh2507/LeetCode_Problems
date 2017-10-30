package codingcompanies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Google {

	public static List<String> permute = new ArrayList<>();
	
	public static void main(String args[]) throws Exception {
		String numberPlate = "RT 123SO";
		
		if(!numberPlate.isEmpty()) {
			String[] words = numberPlate.split(" ");
			String letters = words[0];
			
			for (int i=0; i<words[1].length(); i++) {
				if(Character.isLetter(words[1].charAt(i))) {
					letters +=words[1].charAt(i);
				}
			}
			
			System.out.println(letters);
			while(!isFound("", letters)) {
				
				//TODO for non exact match
			}
		}
	}

	public static Boolean isFound(String prefix, String str) {
			
		HashMap<String, String> map = new HashMap<>();
		map.put("car", "car");
		map.put("sort", "sort");
		
		if (str.length() == 0) {
			if(map.containsKey(prefix.toLowerCase())) {
				System.out.println("Found the word in Dict: " + prefix.toLowerCase());
				return true;
			}
		}
		else {
			for(int i=0; i<str.length(); i++) {
				isFound(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, str.length()));
			}
		}
		return false;
	}

}
