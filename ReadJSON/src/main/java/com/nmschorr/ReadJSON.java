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

public class ReadJSON {
	final String  locUrlString;
	final String myJsonFileName =  "C:\\jsondata2.json";

	public ReadJSON() {
		System.setProperty("http.agent", "Chrome");
		locUrlString = "http://jsonplaceholder.typicode.com/albums";
		System.out.println("Beginning...");
	}

	public static void main(String[] args) throws Exception {
		ReadJSON myThis = new ReadJSON();     // constructor, set up strings
		//readUrlData();  // reads json data from a url http page
		String FileAsString =  readScanFile(myThis.myJsonFileName); 
		JSONArray filecontentsAsJSONArrayMain = makeJsonObjArrayOfHashmapsFrFileStringContent(FileAsString); 
		ArrayList<Object> myArrayList = new ArrayList<Object>(toList(filecontentsAsJSONArrayMain));
		Collection<Map<String,String>> theCol =  makeCollection( myArrayList) ;
		ArrayList <HashMap<String,String>> bigArrayofHashMap = chgStructToArrListHashMp(myArrayList);
		JSONArray bigArrayofChangedJSON = 	toJsonArrayofObjects(bigArrayofHashMap);
		System.out.println("\nAlmost Finished");
		System.out.println("\nProgram Finished");
	}

	public static ArrayList <HashMap<String,String>> chgStructToArrListHashMp(ArrayList<Object> newvar) {
		ArrayList <HashMap<String,String>> newestArrayOfHashMap = new ArrayList <HashMap<String,String>>();
        String fieldToremove = "website";
        for (int b = 0; b < newvar.size(); b++) {
			HashMap <String, String> myObject2 = (HashMap <String, String>)newvar.get(b);
			HashMap<String,String> hashmapy = new HashMap<String,String>();
			hashmapy = (HashMap<String,String>)myObject2;
			hashmapy.remove(fieldToremove);
			out.println("\n after removing " + fieldToremove);
			out.println(hashmapy.toString());
			out.println( "is there a " + fieldToremove + " key now? : " + hashmapy.containsKey(fieldToremove) );
			newestArrayOfHashMap.add(hashmapy);
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

//-----------------working on now:
	public static JSONArray toJsonArrayofObjects(ArrayList<HashMap<String,String>> arrayListOfHashMap)  throws Exception {
		JSONArray newjarr = new JSONArray();
		for (int xy = 0; xy < arrayListOfHashMap.size(); xy++)  {
			HashMap<String,String> mmap = arrayListOfHashMap.get(xy);
			 JSONObject jjoo = new JSONObject(mmap);
            newjarr.put(jjoo);
		}
		return newjarr;
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

