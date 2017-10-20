package exampleclass3;

public class HybridSort {

	private int[] data;
	private long compCounter;// count number of key comparisons

	HybridSort(int[] data) {
		this.data = data;
	}
        

	public int[] modifiedMSort(int start, int end, int S) {

		int mid = (start + end) / 2;

		if (end - start <= S) {
			return insertionSort(start, end);
		} else {

			int[] firstList = modifiedMSort(start, mid, S);
			int[] secondList = modifiedMSort(mid + 1, end, S);
			return (merge(firstList, secondList));
		}

	}

	private int[] insertionSort(int first, int last) {
		int temp;
		int[] sorted = new int[last - first + 1];

		for (int i = 0; i < sorted.length; i++) {
			sorted[i] = data[first];
			first++;
		}

		for (int i = 0; i < sorted.length; i++) {
			for (int j = i; j > 0; j--) {
				if (sorted[j - 1] > sorted[j]) {
					temp = sorted[j - 1];
					sorted[j - 1] = sorted[j];
					sorted[j] = temp;
					compCounter++;
				} else
					break;
			}
		}
		return sorted;
	}

	private int[] merge(int[] firstList, int[] secondList) {
		int totalElement = firstList.length + secondList.length; // totalElement=length of array

		int sorted[] = new int[totalElement]; // create new array to store the sorted list of numbers
		int index1 = 0; // check from the first element in firstList
		int index2 = 0; // check from the first element in secondList
		int sortedindex = 0;

		while (sortedindex < totalElement) { // checking all elements
			// still checking elements in firstList and secondList
			if ((index1 < firstList.length) && (index2 < secondList.length)) {
				if (firstList[index1] < secondList[index2]) {
					sorted[sortedindex] = firstList[index1];
					sortedindex++;
					index1++;
				} else {
					sorted[sortedindex] = secondList[index2];
					sortedindex++;
					index2++;
				}
				compCounter++;
			}
			// finish checking either firstList or secondList
			else {
				// if finish checking firsthalf
				if (index1 >= firstList.length) {
					while (index2 < secondList.length) {
						sorted[sortedindex] = secondList[index2];
						sortedindex++;
						index2++;
					}
				}
				// if finish checking secondhalf
				else if (index2 >= secondList.length) {
					// put remaining firstList into tmparray
					while (index1 < firstList.length) {
						sorted[sortedindex] = firstList[index1];
						sortedindex++;
						index1++;
					}
				}
				compCounter++;
			}
		}
		return sorted;
	}
        public long getCompCounter(){
            return this.compCounter;
        }
}
