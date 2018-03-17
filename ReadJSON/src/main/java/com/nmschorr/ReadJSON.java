/* TestRestJson by Nancy Schorr, 2016
 Demonstrates use of apis to read a json formatted text file and store it as a JSON object for further manipulation.
 Also goes to url http://jsonplaceholder.typicode.com/albums and reads the data and store it in various objects.
 */

package com.nmschorr;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class ReadJSON {
	final static String myJsonFileName = "C:\\jsondata2.json";
	final static String locUrlString = "http://jsonplaceholder.typicode.com/albums";

	public static void main(String[] args) throws Exception {
		System.setProperty("http.agent", "Chrome");
		System.out.println("Beginning...");
		//readUrlData();  // reads json data from a url http page
		try {    	readJson(ReadJSON.readScanFile(myJsonFileName));  } catch(  Exception e) { e.printStackTrace(); }
		System.out.println("\nProgram Finished");
	}


	// This method plays with converting JSON objects: use debugger to see them List<Object> myArrayList = new ArrayList<Object>();
	// HashMap<String, String> map = new HashMap<String, String>();  // shows casting of Object to HashMap Map<String,String> mapsObj = new HashMap<String,String>();	

	static void readJson(String long1string) throws Exception {
		JSONArray myJsonArry = new JSONArray(long1string); 
		List<Object> mArrayofObjects = new ArrayList<Object>(myReadJsonArrayToObList(myJsonArry));
		Collection<Map<String,String>> myMapsHashSet = new HashSet<Map<String,String>>();	
		
		final JSONArray json2arr = new JSONArray(long1string); 
	    List<Object> arryOf4Objects= new ArrayList<Object>();
	    arryOf4Objects=myReadJsonArrayToObList(json2arr);
		for (int i=0; i < arryOf4Objects.size(); i++) {
			Object myLocObject = arryOf4Objects.get(i);
			HashMap<String, String> locHashMap = ((HashMap<String, String>)myLocObject);
			myMapsHashSet.add(locHashMap);
		}


		for ( int arrLocCtr = 0; arrLocCtr < json2arr.length(); arrLocCtr++) { 
		//	 JSONObject myJsonObj = json2arr.optJSONObject(arrLocCtr);
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

	// for this and toList below: thanks to: http://stackoverflow.com/questions/21720759/convert-a-json-string-to-a-hashmap

	//----------------------------------------------------------------------------------------------------
	public static Map<String, Object> myJsonObjToMap(JSONObject locPassInObj) throws JSONException {
		Map<String, Object> myMap = new HashMap<String, Object>();
		Iterator<String> keysItr = locPassInObj.keys();
		while (keysItr.hasNext()) {
			String myKey = keysItr.next();
			Object myVal = locPassInObj.get( keysItr.next() );

			if (myVal instanceof JSONArray) {
				myVal = myReadJsonArrayToObList((JSONArray) myVal);
			}
			else if (myVal instanceof JSONObject) {
				myVal = myJsonObjToMap((JSONObject) myVal);
			}
			myMap.put(myKey, myVal);
		}
		return myMap;
	}

 
	public static List<Object> myReadJsonArrayToObList(JSONArray passedInJsonArray) throws JSONException {
		List<Object> myObjectList = new ArrayList<Object>();
		for(int i = 0; i < passedInJsonArray.length(); i++) {
			Object locObject= passedInJsonArray.get(i);
			if (locObject instanceof JSONArray) {
				locObject = myReadJsonArrayToObList((JSONArray) locObject);
			} 	
			myObjectList.add(locObject);
		} 	return myObjectList;
	}
	
	
	
	
	
	
	
	
	
	
	public static String readScanFile(String filename) throws FileNotFoundException {
	    String myDelim = "\\Z";
		String locString = new String("");  // new empty string
		File myFile = new File(filename);
		Scanner myScanner = new Scanner(myFile);
		locString = myScanner.useDelimiter(myDelim).next();
		myScanner.close();			
		return locString;
	}  //myScanner()

	void readUrlData2() throws Exception {
		String builderLine;
		StringBuilder myStrBuilder = new StringBuilder();
	    Reader bufReader = new BufferedReader(
		    new InputStreamReader(new URL(locUrlString).openStream()));	
		    while((builderLine = ((BufferedReader)bufReader).readLine()) != null) {
			    myStrBuilder.append(builderLine);
		}}

	}	//class










