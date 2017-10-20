package ExampleClass2;

import java.io.File;
import java.util.Scanner;

public class UniFormlyDistributedData {
	public static void main(String[] args) {
		int tableSize, userKey = 0, userData = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Using uniformly distributed dataset");
		System.out.println("Please input table size:");
		tableSize = sc.nextInt();
		
		//reading key from excel sheet and map to hash table
		CSVDataReader csvreader = new CSVDataReader();
		System.out.println("Reading data for No size " + tableSize + "...please wait 1 mins.");
		String path = new File("src/com/algorithm/exampleclass2/uniformdata.csv").getAbsolutePath();
    	HashMap hmapforPrimeNo = csvreader.readCSVFile(tableSize,path);
						
		System.out.println("Data adding finished.");
		System.out.println("=================================");
		while (true) {
			System.out.println("Please key in a NRIC you want to looking for :");
			
			userKey = sc.nextInt();
			if (userKey == -1)//input -1 to terminate the program
				break;

			// calculate CPU time
			long start = 0;

			int runs = 10000; // enough to run for 2-10 seconds. Avoid warm up time.

			for (int i = -10000; i < runs; i++) {

				if (i == 0)
					start = System.nanoTime();

				// do test
				userData = hmapforPrimeNo.get(userKey);

			}

			long time = System.nanoTime() - start;
			
			//get number of comparisions
			userData = hmapforPrimeNo.getNCount(userKey);
	
			if (userData != -1){
				System.out.println("The car plate No. founded, it is " + userData);
			}
			else{
				System.out.println("No record of this NRIC No.");
			}
			
			System.out.println("For Number table size " + tableSize );
			System.out.printf("The searching took an average of %,d ns%n", time / runs);
			System.out.printf("The searching took an average of %,d comparisions%n%n", hmapforPrimeNo.getNoOfComp());
			
			hmapforPrimeNo.setNoOfComp(0);//reset the count of comparisons
	
		}

	}
}
