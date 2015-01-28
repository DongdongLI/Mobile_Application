package com.example.group4bhw02;

/*
 * Dongdong Li,Marcos Brenes
 * HW02
 * Task.java*/

import java.io.Serializable;

public class Task implements Serializable {
	private String title;
	private String date;
	private String time;
	private int priority;

	public Task(String title, String date, String time, int priority) {
		super();
		this.title = title;
		this.date = date;
		this.time = time;
		this.priority = priority;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "Task [title=" + title + ", date=" + date + ", time=" + time
				+ ", priority=" + priority + "]";
	}

}
