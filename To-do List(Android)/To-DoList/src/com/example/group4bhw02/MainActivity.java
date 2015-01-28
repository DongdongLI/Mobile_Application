package com.example.group4bhw02;
/*
 * Dongdong Li,Marcos Brenes
 * HW02
 * MainActivity.java*/

import java.util.ArrayList;

import android.app.Activity;
import android.app.DownloadManager.Request;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	static final String TASK_DISPLAY	= "DISPLAY";
	static final String TASK_KEY 		= "TASK";
	static final String INDEX_KEY 		= "INDEX";
	static String DISPLAY_OPERATION		= "DISPLAY_OP";

	static final int RESULT_EDIT 		= 0X01;
	static final int REQUEST_CREATE 	= 0X03;
	static final int REQUEST_DISPLAY 	= 0X04;
	static ArrayList<Task> allTask;

	ScrollView scroll;
    LinearLayout container;
    ImageButton btn;
    TextView title;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        allTask = new ArrayList<Task>();
        
		title = (TextView) findViewById(R.id.main_activity_numOfItems_textView);
		scroll = (ScrollView) findViewById(R.id.min_activity_showArea_scrollView);
		container = (LinearLayout) findViewById(R.id.mainActivity_container);
		
		btn = (ImageButton) findViewById(R.id.activity_main_newTask_Button);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, CreateTaskActivity.class);
				startActivityForResult(intent, REQUEST_CREATE);
			}
		});
		
        display();
	}
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
    	if (resultCode == RESULT_OK) {
	    	if (requestCode == REQUEST_CREATE)
				allTask.add(0,(Task) data.getExtras().getSerializable(TASK_KEY));
	    	
	    	else if (requestCode == REQUEST_DISPLAY) {
				if (data.getExtras().getInt(DISPLAY_OPERATION) == 1) {
	    			// Edit the list
					int index = data.getExtras().getInt(INDEX_KEY);
					Task task = (Task)data.getExtras().getSerializable(TASK_KEY);
					allTask.remove(index);
	    			allTask.add(index, task);
	    			
	    		} else if (data.getExtras().getInt(DISPLAY_OPERATION) == 2) {
	    			// Delete Element from List
	    			int index= data.getExtras().getInt(INDEX_KEY);
	    			allTask.remove(index);
	    		}
			}
    	}
    	display();
    }
    
    private void display() {
		container.removeAllViews();
		int size = allTask.size();
		title.setText(size + " " + "Tasks");
		for (Task t : allTask) {
			newTaskItem(t);
		}
    }
    
    private void newTaskItem(Task task) {
		LinearLayout newItem = new LinearLayout(this);
		newItem.setOrientation(LinearLayout.VERTICAL);

		TextView date = new TextView(this);
		TextView time = new TextView(this);
		TextView name = new TextView(this);
    	
    	name.setTextColor(Color.BLACK);
    	date.setTextColor(Color.BLACK);
    	time.setTextColor(Color.BLACK);
    	
    	name.setText(task.getTitle());
    	date.setText(task.getDate());
    	time.setText(task.getTime());
    	
    	newItem.addView(name);
    	newItem.addView(date);
    	newItem.addView(time);
    	
    	LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    	int bottom = (int) getResources().getDimension(R.dimen.margin_bottom);
    	int left = (int) getResources().getDimension(R.dimen.margin_left);
    	params.setMargins(left, 0, 0, bottom);
    	newItem.setLayoutParams(params);
    	
    	params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    	params.setMargins(left, 0, 0, 0);
    	date.setLayoutParams(params);
    	time.setLayoutParams(params);
    	
    	name.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title_text_size));
    	name.setTypeface(name.getTypeface(), Typeface.BOLD);
    	container.addView(newItem);
    	
    	final int index = ((ViewGroup) newItem.getParent()).indexOfChild(newItem);
    	
    	//Toast.makeText(this, index + "", Toast.LENGTH_SHORT).show();
    	
    	newItem.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this, DisplayActivity.class);
				intent.putExtra(TASK_KEY, allTask.get(index));
				intent.putExtra(INDEX_KEY, index);
				startActivityForResult(intent, REQUEST_DISPLAY);
			}
		});
    }
}
