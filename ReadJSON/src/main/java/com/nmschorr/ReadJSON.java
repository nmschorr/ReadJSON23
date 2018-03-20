/* TestRestJson by Nancy Schorr, 2016 and March, 2018
 Demonstrates use of apis to read a json formatted text file and store it as a JSON object for further manipulation.
 Also goes to url http://jsonplaceholder.typicode.com/albums and reads the data and store it in various objects.
 */

package com.nmschorr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class ReadJSON extends ReadJSONutils{

	public static void print(Object o) { System.out.println(o); }
	
	
	//final static String myJsonFileName =  "C:\\jsondata2.json";
	final static String myJsonFileName =  "C:\\jsondata3.json";
	final static String locUrlString = "http://jsonplaceholder.typicode.com/albums";

	public ReadJSON() {
		System.setProperty("http.agent", "Chrome");
		System.out.println("Beginning...");
	}

	
	
	public static void main(String[] args)  throws Exception {
		//final StringBuilder sb = readUrlData(locUrlString);  // reads json data from a url http page
		
		final String FileAsString =  readScanFile(ReadJSON.myJsonFileName); 
		
	    JSONArray myJSONarrayMAIN = jsnArrOfHMapsFstr(FileAsString); 
	    Map<String, Object> oneHashObj = new HashMap<String, Object>();
	    
	    oneHashObj = (HashMap)myJSONarrayMAIN.get(0);	    
	    
	    
		//myArrayList = jsonArryToMap(myJSONarrayMAIN);
	//	Collection<?> col = ReadJSONutils.makeCollection( myArrayList);
	//	ArrayList <HashMap<String,String>> bigArrayofHashMap = remLinesFromMap(myArrayList);
	//	toJsnArrOfObjs(bigArrayofHashMap);
		
		
		Object arry = myJSONarrayMAIN.get(0);
	//	Map<?,? > mymap = arry.js
		//		readJsnArr_toMap
	//	List<String, Object> so  = myJSONarrayMAIN.toJSONObject();
		
    //    for (JSONObject ar: col) {

        	System.out.println(  );
        	//System.out.println(json.toString(4));
   //     }
        	
        	
		print("\nProgram Finished");

	}

	//----------------------------------------------------------------------------------------------------
	// for this and toList below: thanks to: http://stackoverflow.com/questions/21720759/convert-a-json-string-to-a-hashmap

		
	
		public static Map<String, Object> jsonArryToMap(JSONObject object) throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();
		Iterator<String> keysItr = object.keySet().iterator();
		while(keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = object.get(key);
			if( value instanceof JSONArray) {
				value = ReadJSON.readJsnArr_toList((JSONArray) value);
			} 	else if(value instanceof JSONObject) {
				value = ReadJSON.jsonArryToMap((JSONObject) value);
			} 	map.put(key, value);
		} 	return map;
	}
	
		public static List<Object> readJsnArr_toList(JSONArray passedInJsonArray) throws JSONException {
			 List<Object> myList = new ArrayList<Object>();
				for(int i = 0; i < passedInJsonArray.length(); i++) {
					Object myObject= passedInJsonArray.get(i);
					if (myObject instanceof JSONArray) {
						myObject = ReadJSON.readJsnArr_toList((JSONArray) myObject);
					} 	else if (myObject instanceof JSONObject) {
					    myObject = ReadJSON.jsonArryToMap((JSONObject) myObject);
					} 	myList.add(myObject);
				} 	return myList;
			}

	public static void babyTest() {                                    // for stackoverflow question
		  JSONObject firstJson = new JSONObject();
		    JSONObject secondJson = new JSONObject();
		    firstJson.put("test1", "value1");
		    secondJson.put("test1", "value2");
		    boolean result = firstJson.similar(secondJson);
		    System.out.println("result: " + result);
	}
	
	
}	//class

