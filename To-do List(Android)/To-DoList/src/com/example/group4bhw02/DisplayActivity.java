package com.example.group4bhw02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/*
 * Dongdong Li,Marcos Brenes
 * HW02
 * DisplayActivity.java*/

public class DisplayActivity extends Activity {

	static int REQUEST_EDIT = 0X01;

	TextView name;
	TextView date;
	TextView time;
	TextView priority;
	ImageButton edit, delete;
	int index;
	Task task;

	Intent dataIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);

		name = (TextView) findViewById(R.id.displayActivity_name);
		date = (TextView) findViewById(R.id.displayActivity_date);
		time = (TextView) findViewById(R.id.displayActivity_time);
		priority = (TextView) findViewById(R.id.DisplayActivity_priority);
		edit = (ImageButton) findViewById(R.id.displayActivity_edit_btn);
		delete = (ImageButton) findViewById(R.id.displayActivity_delete_btn);

		dataIntent = new Intent();

		dataIntent.putExtra(MainActivity.DISPLAY_OPERATION, 0);

		index = getIntent().getExtras().getInt(MainActivity.INDEX_KEY);
		dataIntent.putExtra(MainActivity.INDEX_KEY, index);

		task = (Task) getIntent().getExtras().getSerializable(
				MainActivity.TASK_KEY);
		display(task);
		bindListener();
	}

	@Override
	public void finish() {
		setResult(RESULT_OK, dataIntent);
		super.finish();
	}

	void display(Task task) {
		name.setText("Name: " + task.getTitle());
		date.setText("Date: " + task.getDate());
		time.setText("Time " + task.getTime());

		int pri = task.getPriority();
		switch (pri) {
		case 1:
			priority.setText("Priority: " + "High");
			break;
		case 2:
			priority.setText("Priority: " + "Medium");
			break;
		case 3:
			priority.setText("Priority: " + "Low");
			break;
		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_EDIT)
			if (resultCode == RESULT_OK) {
				task = (Task) data.getExtras().getSerializable(
						MainActivity.TASK_KEY);
				dataIntent.putExtra(MainActivity.TASK_KEY, task);
				display(task);
			} else {
				dataIntent.putExtra(MainActivity.TASK_KEY, task);
				display(task);
			}

	}

	void bindListener() {
		edit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dataIntent.putExtra(MainActivity.DISPLAY_OPERATION, 1);
				Intent intent = new Intent(DisplayActivity.this,
						EditTaskActivity.class);
				intent.putExtra(MainActivity.TASK_KEY, task);
				startActivityForResult(intent, REQUEST_EDIT);
			}
		});

		delete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dataIntent.putExtra(MainActivity.DISPLAY_OPERATION, 2);

				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
