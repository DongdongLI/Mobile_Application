/*
 * InClass 6
 * DetailsActivity.java
 * Marcos Brenes, Dongdong Li
 */

package com.example.inclass6;

import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends Activity {
	ImageView image;
	TextView title;
	TextView owner;
	Photo p;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		image=(ImageView) findViewById(R.id.imageView1);
		title=(TextView) findViewById(R.id.textView1);
		owner=(TextView) findViewById(R.id.textView2);
		
		p= (Photo) getIntent().getExtras().getSerializable(GalleryActivity.PHOTO_KEY);
		
		
		new GetImage().execute(p.url);
	}
	
	


	class GetImage extends AsyncTask<String, Void, Bitmap>
    {
    	Bitmap bitmap;
		@Override
		protected Bitmap doInBackground(String... params) {
			try {
				URL url=new URL(params[0]);
				HttpURLConnection con=(HttpURLConnection) url.openConnection();
				
				bitmap=BitmapFactory.decodeStream(con.getInputStream());
				
				return bitmap;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			
			if(result!=null)
			{
			    image=	(ImageView) findViewById(R.id.imageView1);
				image.setImageBitmap(result);
				title.setText(p.title);
				owner.setText("By: " + p.owner);
			}
		}
		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
    	
    }
}
