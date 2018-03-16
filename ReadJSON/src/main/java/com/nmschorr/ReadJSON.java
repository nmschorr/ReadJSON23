/* TestRestJson by Nancy Schorr, 2016
 Demonstrates use of apis to read a json formatted text file and store it as a JSON object for further manipulation.
 Also goes to url http://jsonplaceholder.typicode.com/albums and reads the data and store it in various objects.
 */

package com.nmschorr;

import static java.lang.System.out;
import java.io.*;
import java.net.*;
import java.util.*;
import org.json.*;

public class ReadJSON {
	public static String scanFileContentsString = null;
	public static String myDelim = "\\Z";
	public static String myJsonFileName = "C:\\jsondata2.json";
	protected	 static String  builderLine;
	static Exception e;
	final protected static String locUrlString = "http://jsonplaceholder.typicode.com/albums";
	final static StringBuilder myStrBuilder = new StringBuilder();
	final public static List<Object> myList = new ArrayList<Object>();

	static String  locString = new String("");  // new empty string

	public static void main(String[] args) throws Exception {
		System.setProperty("http.agent", "Chrome");
		System.out.println("Beginning...");
		//readUrlData();  // reads json data from a url http page
		try {    	ReadJSON.readJson(ReadJSON.readScanFile(myJsonFileName));  } catch(final Exception e) { e.printStackTrace(); }
		System.out.println("\nProgram Finished");
	}

	static void readUrlData () throws Exception {
		final Reader bufReader = new BufferedReader(
		    new InputStreamReader(new URL(locUrlString).openStream()));	
		while((builderLine = ((BufferedReader)bufReader).readLine()) != null) {
			myStrBuilder.append(builderLine);
		}
	}

	// This method plays with converting JSON objects: use debugger to see them List<Object> myArrayList = new ArrayList<Object>();
	// HashMap<String, String> map = new HashMap<String, String>();  // shows casting of Object to HashMap Map<String,String> mapsObj = new HashMap<String,String>();	

	static void readJson(String long1string) throws Exception {

		final JSONArray json2arr = new JSONArray(long1string); 
		final List<Object> obj3list =    ReadJSON.myToList(json2arr);

		final List<Object> obj4array= new ArrayList<Object>(obj3list);
		final Collection<Map<String,String>> mapsCol2 = new HashSet<Map<String,String>>();	
		final Collection<Map<String,String>> mapsCol = new HashSet<Map<String,String>>();	

		int iv = obj3list.size();
		int vv = obj4array.size();
		for (int i=0; i < obj3list.size(); i++) {
			mapsCol2.add((HashMap<String, String>)obj3list.get(i));
		}
		for (int i=0; i < obj4array.size(); i++) {
			mapsCol.add((HashMap<String, String>)obj4array.get(i));
		}


		for ( int xx = 0; xx < json2arr.length(); xx++) { 
			 JSONObject myJsonObj = json2arr.optJSONObject(xx);
			 Iterator<Object> myJsonItr = json2arr.iterator();

			while(myJsonItr.hasNext()) {
			    Object myObj = myJsonItr.next();
				System.out.println("myJsonArry element obj" + myObj + " \n\n");

				for(int i=0; i<json2arr.length(); i++){
					json2arr.getJSONObject(i);
				} //for
			} //while
		}  //for
	}

	public static String readScanFile(String filename) throws FileNotFoundException {
		//  File myFile=new File(builderLine);
		   Scanner 	myScanner = new Scanner(new File(builderLine));
		locString = myScanner.useDelimiter(ReadJSON.myDelim).next();
		myScanner.close();			
		return locString;
	}  //myScanner()

	// for this and toList below: thanks to: http://stackoverflow.com/questions/21720759/convert-a-json-string-to-a-hashmap
	public static Map<String, Object> toMap(JSONObject object) throws JSONException {
		 Map<String, Object> myMap = new HashMap<String, Object>();
		 Iterator<String> keysItr = object.keys();
		while (keysItr.hasNext()) {
			 String myKey = keysItr.next();
			Object myVal = object.get(myKey);

			if (myVal instanceof JSONArray) {
				myVal = ReadJSON.myToList((JSONArray) myVal);
			}
			else if (myVal instanceof JSONObject) {
				myVal = ReadJSON.toMap((JSONObject) myVal);
			}
			myMap.put(myKey, myVal);
		}
		return myMap;
	}

	public static List<Object> myToList(JSONArray myJArray) throws JSONException {
		Object myObject= null;
		for(int i = 0; i < myJArray.length(); i++) {
			 myObject= myJArray.get(i);
			if (myObject instanceof JSONArray) {
				myObject = ReadJSON.myToList((JSONArray) myObject);
			}
			else if (myObject instanceof JSONObject) {
				myObject = ReadJSON.toMap((JSONObject) myObject);
			} myList.add(myObject);
		} 	return myList;
	}
}	//class










