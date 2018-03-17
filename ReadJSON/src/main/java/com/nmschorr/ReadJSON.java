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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class ReadJSON {
	final String myJsonFileName, locUrlString;

	public ReadJSON() {
		System.setProperty("http.agent", "Chrome");
		myJsonFileName =  "C:\\jsondata2.json";
		locUrlString = "http://jsonplaceholder.typicode.com/albums";
		System.out.println("Beginning...");
	}

	public static void main(String[] args) throws Exception {
		ReadJSON myThis = new ReadJSON();     // constructor, set up strings
		//readUrlData();  // reads json data from a url http page
		try {    	
			String FileAsString =  readScanFile(myThis.myJsonFileName); 

			JSONArray filecontentsAsJSONArrayMain = makeJsonArrayReallyHashmapsFrFileStringContent(FileAsString); 
			// turn array into objects, hashmap set


		} catch(  Exception e) { e.printStackTrace(); }
		System.out.println("\nProgram Finished");
	}

	// This method plays with converting JSON objects: use debugger to see them List<Object> myArrayList = new ArrayList<Object>();
	// HashMap<String, String> map = new HashMap<String, String>();  // shows casting of Object to HashMap Map<String,String> mapsObj = new HashMap<String,String>();	

	static  JSONArray makeJsonArrayReallyHashmapsFrFileStringContent(String fileContents) throws Exception {
		JSONArray filecontentsAsJSONArray = new JSONArray(fileContents); 
		System.out.println("\n inside makeJsonArrayFromFileStringContent. returning: " +filecontentsAsJSONArray.toString() );
	    return filecontentsAsJSONArray;
	}	    

	static void chgToHashMapSET(ArrayList<Object> locArryList ) throws Exception {
		Collection<Map<String,String>> myMapsHashSet = new HashSet<Map<String,String>>();	
		for (int i=0; i < locArryList.size(); i++) {
			Object myLocObject = locArryList.get(i);
			HashMap<String,Object> hm = chgToHashMap(myLocObject);
			HashMap<Object,Object> hm2;
			//myMapsHashSet.add((HashMap<Object,Object>)hm);
		}
		System.out.println("\n inside chgToHashMapSET. returning: " + "nothing" );
	}

	static HashMap chgToHashMap(Object locObj ) throws Exception {
		HashMap<String, String> locHashMap= ((HashMap<String, String>)locObj);
		return locHashMap;
	}



void fetchOneJsonToedit(ArrayList<JSONObject> json2arr ) {
     for ( int arrLocCtr = 0; arrLocCtr < json2arr.size()    ; arrLocCtr++) { 
	         JSONObject myJsonObj = json2arr.get(arrLocCtr) ;      //     .optJSONObject(arrLocCtr);
	 		 Iterator<JSONObject> myJsonItr = json2arr.iterator();
     
	 while(myJsonItr.hasNext()) {
	      Object myObj = myJsonItr.next();
	      System.out.println("myJsonArry element obj" + myObj + " \n\n");
	 }
     }
     
     for(int i=0; i<json2arr.size(); i++){
           json2arr.get(i) ;         
     }
     }

	//void newFunction() {
	//		for ( int arrLocCtr = 0; arrLocCtr < json2arr.length(); arrLocCtr++) { 
	//		//	 JSONObject myJsonObj = json2arr.optJSONObject(arrLocCtr);
	//			 Iterator<Object> myJsonItr = json2arr.iterator();
	//
	//			while(myJsonItr.hasNext()) {
	//			    Object myObj = myJsonItr.next();
	//				System.out.println("myJsonArry element obj" + myObj + " \n\n");
	//
	//				for(int i=0; i<json2arr.length(); i++){
	//					json2arr.getJSONObject(i);
	//				}}}
	//}


	// for this and toList below: thanks to: http://stackoverflow.com/questions/21720759/convert-a-json-string-to-a-hashmap

	//----------------------------------------------------------------------------------------------------
	public static Map<String, Object> myJsonObjToMap(JSONObject locPassInObj) throws JSONException {
		Map<String, Object> myMap = new HashMap<String, Object>();
		Iterator<String> keysItr = locPassInObj.keys();
		while (keysItr.hasNext()) {
			String myKey = keysItr.next();
			Object myVal = locPassInObj.get( keysItr.next() );
			myVal = myJsonObjToMap((JSONObject) myVal);
			myMap.put(myKey, myVal);
		}
		return myMap;
	}


	public static List<Object> myReadJsonArrayToObList(JSONArray passedInJsonArray) throws JSONException {
		Object locObject2= null;
		List<Object> myObjectList = new ArrayList<Object>();
		for(int i = 0; i < passedInJsonArray.length(); i++) {
			Object locJson= passedInJsonArray.get(i);
			locObject2 = myReadJsonArrayToObList((JSONArray) locJson);
			myObjectList.add(locObject2);
		} 	
		return myObjectList;
	}










	public static String readScanFile(String filename) throws FileNotFoundException {
		out.println ("in readScanFile");
		String myDelim = "\\Z";
		String locString = new String("");  // new empty string
		File myFile = new File(filename);
		Scanner myScanner = new Scanner(myFile);
		locString = myScanner.useDelimiter(myDelim).next();
		myScanner.close();			
		out.println ("in readScanFile. returning string: " + locString);
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










