/*
 * InClass 6
 * Photo.java
 * Marcos Brenes, Dongdong Li
 */

package com.example.inclass6;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class Photo implements Serializable {
	String title;
	String url;
	String owner;
	
	Photo(JSONObject photo) throws JSONException
	{
		setTitle(photo.getString("name"));
		setUrl(photo.getString("image_url"));
		setOwner(photo.getJSONObject("user").getString("firstname")+" "+photo.getJSONObject("user").getString("lastname"));
		
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	@Override
	public String toString() {
		
		return title;
	}
}
