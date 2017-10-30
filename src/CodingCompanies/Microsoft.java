package codingcompanies;


import java.io.BufferedReader;
import java.io.FileReader;

public class Microsoft {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] csvValues = null;
		try{
		    BufferedReader in = new BufferedReader(new FileReader("C:\\\\Users\\\\tanma\\\\Desktop\\Data-consolidation_InputForSubmission_1.txt"));
		    String s;

		    while((s = in.readLine()) != null){
		        csvValues = s.split(",");
		    }

		}catch(Exception e){
		    e.printStackTrace();
		}
		
		System.out.println(calculateAverage(csvValues));
	}
	
	public static int calculateAverage(String[] listA) {
		int sum = 0;
		for(int i=0; i<listA.length; i++) {
			sum += Integer.parseInt(listA[i]);
		}
		
		return (int)Math.round(sum/listA.length);
	}

}
