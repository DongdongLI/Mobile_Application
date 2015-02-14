/*
 * InClass 6
 * GalleryActivity.java
 * Marcos Brenes, Dongdong Li
 */

package com.example.inclass6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GalleryActivity extends Activity {
	
	String searchTerm;
	String urlEndpoint = "https://api.500px.com/v1/photos/search?";
	ListView listView;
	ArrayList<Photo> photos;
	
	public static final String PHOTO_KEY = "PHOTO";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery);
		
		listView = (ListView) findViewById(R.id.listView1);
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Photo photo = photos.get(position);
				Intent intent = new Intent(GalleryActivity.this, DetailsActivity.class);
				intent.putExtra(PHOTO_KEY, photo);
				
				startActivity(intent);
			}
		});
		
		
		
		searchTerm = getIntent().getExtras().getString(MainActivity.SEARCH_KEY);
		
		RequestParams params = new RequestParams("GET", urlEndpoint);
		params.addParams("consumer_key", MainActivity.CONSUMER_KEY);
		params.addParams("term", searchTerm);
		params.addParams("image_size", "4");
		params.addParams("rpp", "50");
		
		
		
		new GetJSONAsyncTask().execute(params);
	}
	
	class GetJSONAsyncTask extends AsyncTask<RequestParams, Void, ArrayList<Photo>> {

		@Override
		protected ArrayList<Photo> doInBackground(RequestParams... params) {
			try {
				
				HttpURLConnection connection = params[0].setupConnection();
		
				int statuscode = connection.getResponseCode();
				if (statuscode == HttpURLConnection.HTTP_OK) {
					BufferedReader reader = 
							new BufferedReader(
									new InputStreamReader(connection.getInputStream()));
					StringBuilder sb = new StringBuilder();
					String line = reader.readLine();
					
					while (line != null) {
						sb.append(line);
						line = reader.readLine();
					}
					
					//Log.i("demo", sb.toString());
					return JsonParser.PhotoJSONParser.parsePersons(sb.toString());
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(ArrayList<Photo> result) {
			super.onPostExecute(result);
			
			photos = result;
			ArrayAdapter<Photo> adapter = new ArrayAdapter<Photo>(GalleryActivity.this, android.R.layout.simple_list_item_1, result);
			listView.setAdapter(adapter);
			adapter.setNotifyOnChange(true);
		}

	}
}
