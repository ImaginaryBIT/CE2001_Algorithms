package ExampleClass2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVDataReader {

    public HashMap readCSVFile(int tableSize,String path) {
    	BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
		HashMap hmap = new HashMap(tableSize);
        try {
        	
            br = new BufferedReader(new FileReader(path));
            int lineNo = 0;
            while ((line = br.readLine()) != null) {
            	lineNo = lineNo + 1;
                // use comma as separator
            	if(lineNo >=2){
            		String[] data = line.split(cvsSplitBy);
            		//insert into HashTable
            		hmap.put(Integer.valueOf(data[0]), Integer.valueOf(data[1]));
            	//	System.out.println("NRIC: "+ data[0]+ ","+data[1]);
            	}
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return hmap;

    }

}
