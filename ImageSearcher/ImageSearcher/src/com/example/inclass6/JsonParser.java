/*
 * InClass 6
 * JsonParser.java
 * Marcos Brenes, Dongdong Li
 */

package com.example.inclass6;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {
	static public class PhotoJSONParser
	{
		static ArrayList<Photo> parsePersons(String in) throws JSONException
		{
			ArrayList<Photo> photolist= new ArrayList<Photo>();
			JSONObject root=new JSONObject(in);
			JSONArray personsJSONArray=root.getJSONArray("photos");
			
			for(int i=0;i<personsJSONArray.length();i++)
			{
				JSONObject photoJsonObject=personsJSONArray.getJSONObject(i);
				photolist.add(new Photo(photoJsonObject));

			}
			
			return photolist;
		}
	}
}
