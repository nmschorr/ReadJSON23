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
		
		
		// make JSONArray into ArrayList<JSONObject> ----------------------------------------------		
	
		List<Object> myArrayList = new ArrayList<Object>(toList(filecontentsAsJSONArrayMain));
		Collection<Map<String,String>> mapsCol = new HashSet<Map<String,String>>();	
		for (int i=0; i < myArrayList.size(); i++) {
			mapsCol.add((HashMap<String, String>)myArrayList.get(i));
		}		 
		
	

		
	    System.out.println("\nProgram Finished");

		System.out.println("\nProgram Finished");
	}

							// HashMap<String, String> map = new HashMap<String, String>();  //cast of Object to HashMap Map<String,String> mapsObj = new HashMap<String,String>();	
	static  JSONArray makeJsonObjArrayOfHashmapsFrFileStringContent(String fileContents) throws Exception {
		JSONArray filecontentsAsJSONArray = new JSONArray(fileContents); 
		System.out.println("\n inside makeJsonArrayFromFileStringContent. returning: " +filecontentsAsJSONArray.toString() );
		return filecontentsAsJSONArray;
	}	    


	static Collection chgToHashMapSet(JSONObject locObj ) throws Exception {
		HashMap<String, Object> locHashMap= new HashMap<String, Object>();
		JSONArray myJsonArry2 = new JSONArray(); 
		List<Object> objList = myReadJsonArrayToObList(myJsonArry2);
		List<Object> myArrayList3= new ArrayList<Object>(objList);
		Collection<Map<String,String>> mapsCol = new HashSet<Map<String,String>>();	

		for (int i=0; i < myArrayList3.size(); i++) {
			mapsCol.add((HashMap<String, String>)myArrayList3.get(i));
		}
		return mapsCol;
	}

	JSONObject fetchOneJsonToedit(ArrayList<JSONObject> json2arr, int whichone ) {
		return   json2arr.get(whichone) ;      //     .optJSONObject(arrLocCtr);
	}

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
	public static  Map<String,Object> myJsonObjToMapCol(JSONObject  jobject) throws JSONException {
		Map<String,Object> maps = new  HashMap<String,Object>();	
		  Iterator<String> keysItr = jobject.keys();
		    while(keysItr.hasNext()) {
		    	 String key = keysItr.next();
		         Object value = jobject.get(key);

		         if(value instanceof JSONArray) {
		             value = myReadJsonArrayToObList((JSONArray) value);
		         }

		         else if(value instanceof JSONObject) {
		              value =  toMap((JSONObject) value);
		         }
		         maps.put(key, value);
		     }
		     return maps;
		 }

		//----------------------------------------------------------------------------------------------------
//	JSONArray myJsonArry = new JSONArray(longJsonString); 
//	List<Object> myArrayList = new ArrayList<Object>(toList(myJsonArry));
//	Collection<Map<String,String>> mapsCol = new HashSet<Map<String,String>>();	
//	for (int i=0; i < myArrayList.size(); i++) {
//		mapsCol.add((HashMap<String, String>)myArrayList.get(i));

		
		
		public static Map<String, Object>  newThing() {
	  Map<String, Object> myMap = new HashMap<String, Object>();
//		//  Iterator<String> keysItr = myMap.
//		//  while (keysItr.hasNext()) {
//		//      String myKey = keysItr.next();
//		//      Object myVal = object.get(myKey);
//
//		      if (myVal instanceof JSONArray) {
//		          myVal = myReadJsonArrayToObList((JSONArray) myVal);
//		      }
//		      else if (myVal instanceof JSONObject) {
//		          myVal = myJsonObjToMap((JSONObject) myVal);
//		      }
//		      myMap.put(myKey, myVal);
		  return myMap;
		  }
 
	 
 
	 

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


//
//
//
////for this and toList below: thanks to: http://stackoverflow.com/questions/21720759/convert-a-json-string-to-a-hashmap
//public static Map<String, Object> toMap(JSONObject object) throws JSONException {
//    Map<String, Object> myMap = new HashMap<String, Object>();
//    Iterator<String> keysItr = object.keys();
//    while (keysItr.hasNext()) {
//        String myKey = keysItr.next();
//        Object myVal = object.get(myKey);
//
//        if (myVal instanceof JSONArray) {
//            myVal = toList((JSONArray) myVal);
//        }
//        else if (myVal instanceof JSONObject) {
//            myVal = toMap((JSONObject) myVal);
//        }
//        myMap.put(myKey, myVal);
//    }
//    return myMap;
//}
//
//public static List<Object> toList(JSONArray myJArray) throws JSONException {
//    List<Object> myList = new ArrayList<Object>();
//    for(int i = 0; i < myJArray.length(); i++) {
//        Object myObject= myJArray.get(i);
//        if (myObject instanceof JSONArray) {
//            myObject = toList((JSONArray) myObject);
//        }
//        else if (myObject instanceof JSONObject) {
//            myObject = toMap((JSONObject) myObject);
//        }
//        myList.add(myObject);
//    }
//    return myList;
//}
//}   //class
//
//
//
//
