package exampleclass3;

import java.util.Scanner;

public class SortingApp {

	public static void main(String[] args) {
		int[] array1k = new int[1000];
		int[] array10k = new int[10000];
		int[] array100k = new int[100000];
                Scanner sc = new Scanner(System.in);
		MergeSort mergeSort;
		HybridSort hybridSort;
		// generating random dataset with size = 1k, 10k, 100k
		for (int i = 0; i < 100000; i++) {
			if (i < 1000) {
				array1k[i] = (int) (Math.random() * 1000);
			}
			if (i < 10000) {
				array10k[i] = (int) (Math.random() * 10000);
			}

			array100k[i] = (int) (Math.random() * 100000);
		}
		
		int endIndex = 0;
		// sorting using normal MergeSort
		System.out.println("Sorting using original MergeSort");
		for (int j = 1000; j <= 100000; j *= 10) {
			if (j == 1000) {
				mergeSort = new MergeSort(array1k);
				endIndex = 999;
			} else if (j == 10000) {
				mergeSort = new MergeSort(array10k);
				endIndex = 9999;
			} else {
				mergeSort = new MergeSort(array100k);
				endIndex = 99999;
			}
			long start = 0;

			int runs = 100; // enough to run for 2-10 seconds. Avoid warm up time.

			for (int i = -100; i < runs; i++) {

				if (i == 0)
					start = System.nanoTime();

				// do test
				mergeSort.mergeSort(0, endIndex);

			}

			long time = System.nanoTime() - start;
			
			System.out.printf("With dataset size of %,d, MergeSort took %,d ns%n", j, time / runs);
			System.out.printf("Number of key comparisons: %,d%n%n", mergeSort.getCompCounter()/runs);
		}

		// sorting using modified MergeSort

		System.out.println("================================");
		System.out.println("Sorting using modified MergeSort with threshold S 10");
		for (int j = 1000; j <= 100000; j *= 10) {
			if (j == 1000) {
				hybridSort = new HybridSort(array1k);
				endIndex = 999;
			} else if (j == 10000) {
				hybridSort = new HybridSort(array10k);
				endIndex = 9999;
			} else {
				hybridSort = new HybridSort(array100k);
				endIndex = 99999;
			}
			
			long start = 0;
			
			int runs = 100; // enough to run for 2-10 seconds. Avoid warm up time.
			
			for (int i = -100; i < runs; i++) {

				if (i == 0)
					start = System.nanoTime();

				// do test
				hybridSort.modifiedMSort(0, endIndex, 10);

			}

			long time = System.nanoTime() - start;

			System.out.printf("With dataset size of %,d, ModifiedMergeSort took %,d ns%n", j, time / runs);
			System.out.printf("Number of key comparisons: %,d%n%n", hybridSort.getCompCounter()/runs);
		}
                while(true){
                    HybridSort new_sort;
                    System.out.println("================================");
                    System.out.print("Enter threshold no : ");
                    int S = sc.nextInt();
                    System.out.println("================================");
                    System.out.println("Sorting using modified MergeSort with threshold S "+S);
                    for (int j = 1000; j <= 100000; j *= 10) {
                            if (j == 1000) {
                                    new_sort = new HybridSort(array1k);
                                    endIndex = 999;
                            } else if (j == 10000) {
                                    new_sort = new HybridSort(array10k);
                                    endIndex = 9999;
                            } else {
                                    new_sort = new HybridSort(array100k);
                                    endIndex = 99999;
                            }
	                			long start = 0;
	                			
	                			int runs = 100; // enough to run for 2-10 seconds. Avoid warm up time.
	                			
	                			for (int i = -100; i < runs; i++) {
	
	                				if (i == 0)
	                					start = System.nanoTime();
	
	                				// do test
	                				new_sort.modifiedMSort(0, endIndex, S);
	
	                			}
	
	                			long time = System.nanoTime() - start;
                			
                            System.out.printf("With dataset size of %,d, ModifiedMergeSort took %,d ns%n", j, time / runs);
                            System.out.printf("Number of key comparisons: %,d%n%n", new_sort.getCompCounter()/runs);
                    }
                }
                
	}
}
