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
import java.util.Map;
import java.util.Scanner;
import java.util.Collection;
import org.json.JSONObject;
import org.json.JSONArray;

public class ReadJSONutils {

	public static JSONArray arrList_fHash(ArrayList<HashMap<String,String>> arrOfHshMp)  throws Exception {
		JSONArray newJArry = new JSONArray();
		for (int count = 0; count < arrOfHshMp.size(); count++)  {
			HashMap<String,String> hMap = arrOfHshMp.get(count);
			JSONObject newJObj = new JSONObject(hMap);
			JSONObject newJObj2 = new JSONObject(hMap);
			newJObj.similar(newJObj2);
			newJArry.put(newJObj);
		} 	return newJArry;
	}

	public static String readScanFile(String filename) throws FileNotFoundException {
		out.println ("Beginning readScanFile.");
		String myDelim = "\\Z";
		Scanner myScanner = new Scanner(new File(filename));
		String locString =new String( myScanner.useDelimiter(myDelim).next() );
		myScanner.close();			
		out.println ("In readScanFile: \nReturning string: " + locString);
		return locString;
	}  //myScanner()

	public static StringBuilder readUrlData(String locUrlString) throws Exception {
		String builderLine;
		StringBuilder myStrBuilder = new StringBuilder();
		Reader bufReader = new BufferedReader(
				new InputStreamReader(new URL(locUrlString).openStream()));	
		while((builderLine = ((BufferedReader)bufReader).readLine()) != null) {
			myStrBuilder.append(builderLine);
		}  return myStrBuilder;	
	}

	public static HashMap<String,String> convertObjToHM (Object inObject) {
		return   (HashMap<String,String>)inObject;  
	}

	public static HashMap<String,String> remField (HashMap<String,String> incomingHM, String fieldToRem) {
		incomingHM.remove(fieldToRem);
		out.println("\n after removing " + fieldToRem + " from " + incomingHM.toString());
		out.println( "is there a " + fieldToRem + " key now? : " + incomingHM.containsKey(fieldToRem) );
		return incomingHM;
	}
	public static ArrayList <HashMap<String,String>> chgStructToArrListHashMp(ArrayList<Object> inArrListObs) {
		ArrayList <HashMap<String,String>> newestArrayOfHashMap = new ArrayList <HashMap<String,String>>();
		String fieldToRemove = "website";
		HashMap <String, String> convertedHM = null;
		for (int b = 0; b < inArrListObs.size(); b++) {
			Object theObject =  inArrListObs.get(b);   // get one Object from ArrayList
			convertedHM = convertObjToHM (theObject);   // convert it to a HashMap
			HashMap<String,String> fixedHM = remField(convertedHM, fieldToRemove);
			newestArrayOfHashMap.add(fixedHM);
		}	            
		System.out.println("\n ");	
		return newestArrayOfHashMap; 

	}
	static Collection<Map<String,String>> makeCollection(ArrayList<Object> arryList) {
		Collection<Map<String,String>> mapsCol = new HashSet<Map<String,String>>();	
		for (int i=0; i < arryList.size(); i++) {
			mapsCol.add((HashMap<String, String>)arryList.get(i));
		}   
		return mapsCol;
	}		 

	// HashMap<String, String> map = new HashMap<String, String>();  //cast of Object to HashMap Map<String,String> mapsObj = new HashMap<String,String>();	
	static  JSONArray jsonArrOfHash_FFileStr(String fileContents) throws Exception {
		JSONArray filecontentsAsJSONArray = new JSONArray(fileContents); 
		System.out.println("\n inside jsnArrOfHMapsFstr.  returning: " +filecontentsAsJSONArray.toString() );
		return filecontentsAsJSONArray;
	}
}



