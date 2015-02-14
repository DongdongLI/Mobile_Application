/*
 * InClass 6
 * MainActivity.java
 * Marcos Brenes, Dongdong Li
 */

package com.example.inclass6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	public static final String SEARCH_KEY = "SEARCH";
	public static final String CONSUMER_KEY = "ZouTCtJqoO1c18A9f3cADYObA3OtzcnknYlWvico";
	EditText editText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editText = (EditText) findViewById(R.id.main_activity_edit_text);
		((Button)findViewById(R.id.main_activity_button_submit)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (editText.getText().length() > 0) {
					Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
					intent.putExtra(SEARCH_KEY, editText.getText().toString());
					startActivity(intent);
				} else {
					Toast.makeText(MainActivity.this, "Text field empty!", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
	}
}
