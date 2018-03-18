/* TestRestJson by Nancy Schorr, 2016 and March, 2018
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

public class ReadJSON extends ReadJSONutils{
	final String myJsonFileName =  "C:\\jsondata2.json";
	final static String locUrlString = "http://jsonplaceholder.typicode.com/albums";

	public ReadJSON() {
		System.setProperty("http.agent", "Chrome");
		System.out.println("Beginning...");
	}

	public static void main(String[] args)  throws Exception {
		ReadJSON myThis = new ReadJSON();     // constructor, set up strings
		ReadJSONutils rju = new ReadJSONutils();
		rju.readUrlData(locUrlString);  // reads json data from a url http page
		String FileAsString =  rju.readScanFile(myThis.myJsonFileName); 
		JSONArray filecontentsAsJSONArrayMain = makeJsonObjArrayOfHashmapsFrFileStringContent(FileAsString); 
		ArrayList<Object> myArrayList = new ArrayList<Object>(toList(filecontentsAsJSONArrayMain));
		Collection<Map<String,String>> theCol =  makeCollection( myArrayList) ;
		ArrayList <HashMap<String,String>> bigArrayofHashMap = chgStructToArrListHashMp(myArrayList);
		JSONArray newJaAr = rju.toJsnArrOfObjs(bigArrayofHashMap);

		System.out.println("\nAlmost Finished");
		System.out.println("\nProgram Finished");
	}

	public static ArrayList <HashMap<String,String>> chgStructToArrListHashMp(ArrayList<Object> inArrListObs) {
		ArrayList <HashMap<String,String>> newestArrayOfHashMap = new ArrayList <HashMap<String,String>>();
        String fieldToremove = "website";
        HashMap <String, String> locHM = null;
        for (int b = 0; b < inArrListObs.size(); b++) {
        						//	if (inArrListObs.get(b) instanceof HashMap <?, ?>) {
			locHM = (HashMap <String, String>)inArrListObs.get(b); //  }
			HashMap<String,String> localHM = new HashMap<String,String>();
			localHM = (HashMap<String,String>)locHM;
			localHM.remove(fieldToremove);
			out.println("\n after removing " + fieldToremove);
			out.println(localHM.toString());
			out.println( "is there a " + fieldToremove + " key now? : " + localHM.containsKey(fieldToremove) );
			newestArrayOfHashMap.add(localHM);
		}	            
		System.out.println("\n ");	
	  	return newestArrayOfHashMap; 
	}

	static Collection<Map<String,String>> makeCollection(ArrayList<Object> arlist) {
		Collection<Map<String,String>> mapsCol = new HashSet<Map<String,String>>();	
		for (int i=0; i < arlist.size(); i++) {
			mapsCol.add((HashMap<String, String>)arlist.get(i));
		}   
		return mapsCol;
	}		 

	// HashMap<String, String> map = new HashMap<String, String>();  //cast of Object to HashMap Map<String,String> mapsObj = new HashMap<String,String>();	
	static  JSONArray makeJsonObjArrayOfHashmapsFrFileStringContent(String fileContents) throws Exception {
		JSONArray filecontentsAsJSONArray = new JSONArray(fileContents); 
		System.out.println("\n inside makeJsonArrayFromFileStringContent. returning: " +filecontentsAsJSONArray.toString() );
		return filecontentsAsJSONArray;
	}	    

//
//	static Collection chgToHashMapSet(JSONObject locObj ) throws Exception {
//		HashMap<String, Object> locHashMap= new HashMap<String, Object>();
//		JSONArray myJsonArry2 = new JSONArray(); 
//		List<Object> objList = myReadJsonArrayToObList(myJsonArry2);
//		List<Object> myArrayList3= new ArrayList<Object>(objList);
//		Collection<Map<String,String>> mapsCol = new HashSet<Map<String,String>>();	
//		for (int i=0; i < myArrayList3.size(); i++) {
//			mapsCol.add((HashMap<String, String>)myArrayList3.get(i));
//		}
//		return mapsCol;
//	}

	public static List<Object> toList(JSONArray myJArray) throws JSONException {
		List<Object> myList = new ArrayList<Object>();
		for(int i = 0; i < myJArray.length(); i++) {
			Object myObject= myJArray.get(i);
			if (myObject instanceof JSONArray) {
				myObject = toList((JSONArray) myObject);
			}
			else if (myObject instanceof JSONObject) {
				myObject = toMap((JSONObject) myObject);
			}
			myList.add(myObject);
		}
		return myList;
	}
	// for this and toList below: thanks to: http://stackoverflow.com/questions/21720759/convert-a-json-string-to-a-hashmap

 
	//----------------------------------------------------------------------------------------------------

	public static List<Object> myReadJsonArrayToObList(JSONArray passedInJsonArray) throws JSONException {
		List<Object> myList = new ArrayList<Object>();
		for(int i = 0; i < passedInJsonArray.length(); i++) {
			Object myObject= passedInJsonArray.get(i);
			if (myObject instanceof JSONArray) {
				myObject = myReadJsonArrayToObList((JSONArray) myObject);
			}
			 else if (myObject instanceof JSONObject) {
			 myObject = toMap((JSONObject) myObject);
		 	}
			myList.add(myObject);
		}
		return myList;
	}

	public static Map<String, Object> toMap(JSONObject object) throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();

		Iterator<String> keysItr = object.keySet().iterator();
		while(keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = object.get(key);

			if(value instanceof JSONArray) {
				value = myReadJsonArrayToObList((JSONArray) value);
			}

			else if(value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			map.put(key, value);
		}
		return map;
	}

}	//class

