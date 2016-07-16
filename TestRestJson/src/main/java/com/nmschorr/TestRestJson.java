/* TestRestsJson by Nancy Schorr, 2016
 Demonstrates use of apis to read text file and store it as a JSON object for further manipulation
 Also goes to url http://jsonplaceholder.typicode.com/albums and reads the data
 */

package com.nmschorr;

import java.io.*;
import org.json.*;
import java.util.*;
import java.net.*;
import org.apache.commons.io.*;   // for IOUtils
import org.apache.commons.io.input.*;   // for IOUtils
import java.nio.charset.StandardCharsets;
import static java.lang.System.out;

public class TestRestJson {
	public static String scanFileContentsString = null;
	public static String myDelim = "\\r";
	public static String myDelim2 = "\\Z";
	public static String myUrlString = "http://jsonplaceholder.typicode.com/albums";
	//public static Map<Integer, String> myMap = new HashMap<>();


	public static void main(String[] args) {
		System.setProperty("http.agent", "Chrome");
		System.out.println("Here's new stuff");
		
		try {
							//scanFileContentsString = readScanFile();  // read from file
							//readJson(scanFileContentsString); 
			URL myUrl = new URL(myUrlString);
			HttpURLConnection urlConnection = (HttpURLConnection) myUrl.openConnection(); 
			
						//InputStream inputStream = new InputStream(myUrl);
			InputStream newInputStream = new BufferedInputStream(urlConnection.getInputStream());
			
			//Reader      reader      = new InputStreamReader(newInputStream);
											//			int dataCounter = reader.read();
											//			
											//			out.println ("value of dataCounter: " + dataCounter);
											//			while(dataCounter != -1){
											//			    char theChar = (char) dataCounter;
											//			    //out.print(theChar);
											//			    int x = 1;
											//			    out.print("" + x);
											//			    dataCounter = reader.read();
											//			}
										//reader.close();  		
			
			BufferedReader breader = new BufferedReader(new InputStreamReader(newInputStream));
			StringBuilder bresult = new StringBuilder();
			String bline;
			while((bline = breader.readLine()) != null) {
			    bresult.append(bline);
			}
			System.out.println(bresult.toString());		
			
			out.println("\nalmost done ");
			
			

		   System.out.println( "" );
		    
				
		} catch(Exception e) {
			e.printStackTrace();
		}
    	finally {
	      //IOUtils.closeQuietly(in);
	    }			 
		System.out.println("Program Finished");
	}

	static void readJson(String myStr) throws Exception {
		// this method just plays around with converting JSON objects: use debugger to see them
		List<Object> myArrayList = new ArrayList<Object>();

		JSONArray myJsonArry = new JSONArray(myStr); 
		myArrayList = toList(myJsonArry);
		Object arryListObject = myArrayList.get(0);
		HashMap<String,String> arryListObject2 = (HashMap<String, String>) myArrayList.get(0); // shows casting of Object to HashMap
		 
		HashMap<String, String> map = (HashMap<String, String>) arryListObject;  // shows casting of Object to HashMap
		
		JSONObject myJsonObj = myJsonArry.optJSONObject(0);
		Iterator<Object> myJsonItr = myJsonArry.iterator();

		while(myJsonItr.hasNext()) {
			Object myObj = myJsonItr.next();
			System.out.println("myJsonArry element obj" + myObj + " ");
			System.out.println("--------------------" + myJsonObj.toString());
			//ArrayList<String> a = new ArrayList<String>();
			for(int i=0; i<myJsonArry.length(); i++){
				JSONObject jsonObj  = myJsonArry.getJSONObject(i);
			} //for

		}//while
	}
	 
	
	public static String readScanFile() throws FileNotFoundException {
		final String myJsonFileName = "C:\\jasondata2.json";
		final Scanner myScanner;
		String locString = new String("");  // new empty string
		File myFile = new File(myJsonFileName);
		myScanner = new Scanner(myFile);
		locString = myScanner.useDelimiter(myDelim2).next();
		myScanner.close();			
		return locString;
	}  //myScanner()
	

	// from: http://stackoverflow.com/questions/21720759/convert-a-json-string-to-a-hashmap
	public static Map<String, Object> toMap(JSONObject object) throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();

		Iterator<String> keysItr = object.keys();
		while(keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = object.get(key);

			if(value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if(value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			map.put(key, value);
		}
		return map;
	}

	// from: http://stackoverflow.com/questions/21720759/convert-a-json-string-to-a-hashmap
	public static List<Object> toList(JSONArray array) throws JSONException {
		List<Object> list = new ArrayList<Object>();
		for(int i = 0; i < array.length(); i++) {
			Object value = array.get(i);
			if(value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}
			else if(value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			list.add(value);
		}
		return list;
	}

}	//class










