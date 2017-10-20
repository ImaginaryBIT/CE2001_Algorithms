package ExampleClass2;

public class HashMap {
	private int tableSize;
	private int noOfComp = 0;
	// Test MOD 8, success case for last data entered is 765923 ns
	// Test MOD 7, success case for last data entered is 466129 ns
	private LinkedHashEntry[] table;

	public HashMap(int tableSize) {
		this.tableSize = tableSize;
		this.table = new LinkedHashEntry[tableSize];
		for (int i = 0; i < tableSize; i++)
			table[i] = null;
	}
	
	public int getNoOfComp()
	{
		return noOfComp;
	}
	public void setNoOfComp()
	{
		noOfComp = 0;
	}
	
	//currently the program needs to re-search and count the number of comparisons so that get(int key)'s running time is not affected by noOfCount++;
	public int getNCount(int key) {
		int hash = (key % tableSize);
		if (table[hash] == null) {
			noOfComp++;
			return -1;
		}

		else {
			LinkedHashEntry entry = table[hash];
			while (entry != null && entry.getKey() != key) {
				noOfComp = noOfComp + 2;
				entry = entry.getNext();
			}
			if (entry == null) {
				noOfComp++;
				return -1;
			} else {
				noOfComp++;
				return entry.getValue();
			}
		}
	}
	
	public int get(int key) {
		int hash = (key % tableSize);
		if (table[hash] == null) {
			//noOfComp++;
			return -1;
		}

		else {
			LinkedHashEntry entry = table[hash];
			while (entry != null && entry.getKey() != key) {
				//noOfComp = noOfComp + 2;
				entry = entry.getNext();
			}
			if (entry == null) {
				//noOfComp++;
				return -1;
			} else {
				//noOfComp++;
				return entry.getValue();
			}
		}
	}

	public void put(int key, int value) {
		int hash = (key % tableSize);
		if (table[hash] == null)
			table[hash] = new LinkedHashEntry(key, value);
		else {
			LinkedHashEntry entry = table[hash];
			while (entry.getNext() != null && entry.getKey() != key)
				entry = entry.getNext();
			if (entry.getKey() == key)
				entry.setValue(value);
			else
				entry.setNext(new LinkedHashEntry(key, value));
		}
	}

	/**
	 * @return the tableSize
	 */
	public int getTableSize() {
		return tableSize;
	}

	/**
	 * @param tableSize the tableSize to set
	 */
	public void setTableSize(int tableSize) {
		this.tableSize = tableSize;
	}

	/**
	 * @param noOfComp the noOfComp to set
	 */
	public void setNoOfComp(int noOfComp) {
		this.noOfComp = noOfComp;
	}
	
	
}