package com.example.homework1;
import android.content.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
/*
 * team 4B
 * Dongdong Li, Marcos Brenes
 * Fruit.java*/
public class Fruit {
	String name;
	Bitmap image;
	Boolean selected;
	Boolean Clicked=false;
	int row;
	int column;
	public Fruit(String name, Bitmap image, Boolean selected,int row,int column) {
		this.name = name;
		this.image = image;
		this.selected = selected;
		this.row=row;
		this.column=column;
	}
	public void changeBrightness()
	{
		BitmapDrawable drawable = new BitmapDrawable();
		drawable.setAlpha(100);
		//Drawable d = new BitmapDrawable(this.image);
		//d.setColorFilter(new PorterDuffColorFilter(Color.argb(level, 0, 0, 0), Mode.SRC_ATOP));
		//this.image=BitmapFactory.decodeResource(.Context.getResources(), R.drawable.apple);
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public Bitmap getImage() {
		return image;
	}
	public void setImage(Bitmap image) {
		this.image = image;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	public Boolean getClicked() {
		return Clicked;
	}
	public void setClicked(Boolean clicked) {
		Clicked = clicked;
	}
	
}
