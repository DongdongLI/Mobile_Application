package com.example.group4bhw02;

/*
 * Dongdong Li,Marcos Brenes
 * HW02
 * CreateTaskActivity.java*/

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import android.view.View;

public class CreateTaskActivity extends Activity implements View.OnClickListener, View.OnTouchListener{

	Task task;
	EditText titleEditText, dateEditText, timeEditText;
	RadioGroup radioGroup;
	Button saveButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_task);
		
		titleEditText = (EditText) findViewById(R.id.create_task_title_edittext);
		dateEditText = (EditText) findViewById(R.id.create_task_date_edittext);
		timeEditText = (EditText) findViewById(R.id.create_task_time_edittext);
		radioGroup = (RadioGroup) findViewById(R.id.create_task_radiogroup);
		saveButton = (Button) findViewById(R.id.create_task_save_button);
		
		dateEditText.setKeyListener(null);
		timeEditText.setKeyListener(null);
		
		saveButton.setOnClickListener(this);
		
		dateEditText.setOnTouchListener(this);
		timeEditText.setOnTouchListener(this);
		
	}
	
	private int getSelectedRadioButtonIndex() {
		switch (radioGroup.getCheckedRadioButtonId()) {
		case R.id.radio_high: return 1; 	
		case R.id.radio_medium: return 2; 	
		default: return 3;
		}
	}

	@Override
	public void onClick(View v) {
			if (titleEditText.getText().length() > 0)
				if (dateEditText.getText().length() > 0)
					if (timeEditText.getText().length() > 0) {
						String title = titleEditText.getText().toString();
						String date = dateEditText.getText().toString();
						String time = timeEditText.getText().toString();
						int priority = getSelectedRadioButtonIndex();
						Task task = new Task(title, date, time, priority);
						
						Intent data = new Intent();
						data.putExtra(MainActivity.TASK_KEY, task);
						setResult(RESULT_OK, data);
						finish();
					}
					else Toast.makeText(this, "Please Select a Time", Toast.LENGTH_SHORT).show();
				else Toast.makeText(this, "Please Select a Date", Toast.LENGTH_SHORT).show();
			else Toast.makeText(this, "Please Select a Title", Toast.LENGTH_SHORT).show();
			
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN)
		switch (v.getId()) {
		case R.id.create_task_date_edittext:
			final Calendar calendar = Calendar.getInstance();
			int mYear = calendar.get(Calendar.YEAR);
			int mMonth = calendar.get(Calendar.MONTH);
			int mDay = calendar.get(Calendar.DAY_OF_MONTH);

			DatePickerDialog datePickerDialog = new DatePickerDialog(this,
					new DatePickerDialog.OnDateSetListener() {

						@Override
						public void onDateSet(DatePicker view, int year,
								int monthOfYear, int dayOfMonth) {
							dateEditText.setText((monthOfYear + 1) + "/"
									+ dayOfMonth + "/" + year);

						}

					}, mYear, mMonth, mDay);
			datePickerDialog.show();
			return true;
		case R.id.create_task_time_edittext:
			
			TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					int hour = 12;
					String AMPM = "PM";
					if (hourOfDay > 12) {
						hour = hourOfDay - 12;
						AMPM = "PM";
					} else if (hourOfDay == 0) {
						AMPM = "AM";
						
					} else if (hourOfDay < 12) {
						AMPM = "AM";
						hour = hourOfDay;		
					}
					timeEditText.setText(hour + ":" + (minute < 10 ? "0" + minute: minute) + " " + AMPM);
				}
			}, 12, 0, false);
			
			timePickerDialog.show();
			return true;
		}
		return false;
	}
}
