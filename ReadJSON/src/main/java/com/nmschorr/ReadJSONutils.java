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


public class ReadJSONutils {

	public JSONArray toJsnArrOfObjs(ArrayList<HashMap<String,String>> arrOfHshMp)  throws Exception {
		JSONArray newJArry = new JSONArray();
		for (int count = 0; count < arrOfHshMp.size(); count++)  {
			HashMap<String,String> hMap = arrOfHshMp.get(count);
			JSONObject newJObj = new JSONObject(hMap);
			newJArry.put(newJObj);
		}
		return newJArry;
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



	public StringBuilder readUrlData(String locUrlString) throws Exception {
		String builderLine;
		StringBuilder myStrBuilder = new StringBuilder();
		Reader bufReader = new BufferedReader(
				new InputStreamReader(new URL(locUrlString).openStream()));	
		while((builderLine = ((BufferedReader)bufReader).readLine()) != null) {
			myStrBuilder.append(builderLine);
		}
		return myStrBuilder;	
	}

}


