package exampleclass3;

public class MergeSort {
	private int[] data;
	private int compCounter;//count number of key comparisons

	MergeSort(int[] data) {
		this.data = data;
	}
	
	public int[] mergeSort(int start, int end) {

		int mid = (start + end) / 2;

		if (end - start <= 0) {
			int data2[] = new int[1];
			data2[0] = data[mid];
			return data2;
		} else {

			int[] firstList = mergeSort(start, mid);
			int[] secondList = mergeSort(mid + 1, end);
			return (merge(firstList, secondList));
		}

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
        public int getCompCounter(){
            return this.compCounter;
        }
}
