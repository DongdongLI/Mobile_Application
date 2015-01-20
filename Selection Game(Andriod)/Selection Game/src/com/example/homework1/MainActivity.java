package com.example.homework1;
/*
 * team 4B
 * Dongdong Li, Marcos Brenes
 * MainActivity.java*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	int selected;
	int num_focus;
	int[] fruitNum=new int[6];
	final int total=25;
	Random r=new Random();
    Bitmap pic[]=new Bitmap[6];
    Bitmap pic2[]=new Bitmap[6];
    boolean occupied[][]=new boolean[6][6];
    ImageView img[]=new ImageView[total];
    HashMap<Integer, String> NumToString=new HashMap<Integer, String>();
    HashMap<String,Integer> StringToNum=new HashMap<String,Integer>();
    
    View lastClicked;
    Button btn1;
    Button btn2;
    TextView text;
    ArrayList<Fruit> allFruit=new ArrayList<Fruit>();
    ArrayList<Fruit> backup;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NumToString.put(0, "apple");
        NumToString.put(1, "lemon");
        NumToString.put(2, "mango");
        NumToString.put(3, "peach");
        NumToString.put(4, "strawberry");
        NumToString.put(5, "tomato");
        
        StringToNum.put("apple", 0);
        StringToNum.put("lemon", 1);
        StringToNum.put("mango",2);
        StringToNum.put("peach", 3);
        StringToNum.put("strawberry", 4);
        StringToNum.put("tomato", 5);
        
        init();
    
    }
    public void getPic()
    {
    	pic[0]=BitmapFactory.decodeResource(getResources(), R.drawable.apple);
    	pic[1]=BitmapFactory.decodeResource(getResources(), R.drawable.lemon);
    	pic[2]=BitmapFactory.decodeResource(getResources(), R.drawable.mango);
    	pic[3]=BitmapFactory.decodeResource(getResources(), R.drawable.peach);
    	pic[4]=BitmapFactory.decodeResource(getResources(), R.drawable.strawberry);
    	pic[5]=BitmapFactory.decodeResource(getResources(), R.drawable.tomato);
    	
    	pic2[0]=BitmapFactory.decodeResource(getResources(), R.drawable.apple_alpha);
    	pic2[1]=BitmapFactory.decodeResource(getResources(), R.drawable.lemon_alpha);
    	pic2[2]=BitmapFactory.decodeResource(getResources(), R.drawable.mango_alpha);
    	pic2[3]=BitmapFactory.decodeResource(getResources(), R.drawable.peach_alpha);
    	pic2[4]=BitmapFactory.decodeResource(getResources(), R.drawable.strawberry_alpha);
    	pic2[5]=BitmapFactory.decodeResource(getResources(), R.drawable.tomato_alpha);
    }
    public void getImageView()
    {
    	img[0]=(ImageView)findViewById(R.id.imageView1);
    	img[1]=(ImageView)findViewById(R.id.imageView2);
    	img[2]=(ImageView)findViewById(R.id.imageView3);
    	img[3]=(ImageView)findViewById(R.id.imageView4);
    	img[4]=(ImageView)findViewById(R.id.imageView5);
    	img[5]=(ImageView)findViewById(R.id.imageView6);
    	img[6]=(ImageView)findViewById(R.id.imageView7);
    	img[7]=(ImageView)findViewById(R.id.imageView8);
    	img[8]=(ImageView)findViewById(R.id.imageView9);
    	img[9]=(ImageView)findViewById(R.id.imageView10);
    	img[10]=(ImageView)findViewById(R.id.imageView11);
    	img[11]=(ImageView)findViewById(R.id.imageView12);
    	img[12]=(ImageView)findViewById(R.id.imageView13);
    	img[13]=(ImageView)findViewById(R.id.imageView14);
    	img[14]=(ImageView)findViewById(R.id.imageView15);
    	img[15]=(ImageView)findViewById(R.id.imageView16);
    	img[16]=(ImageView)findViewById(R.id.imageView17);
    	img[17]=(ImageView)findViewById(R.id.imageView18);
    	img[18]=(ImageView)findViewById(R.id.imageView19);
    	img[19]=(ImageView)findViewById(R.id.imageView20);
    	img[20]=(ImageView)findViewById(R.id.imageView21);
    	img[21]=(ImageView)findViewById(R.id.imageView22);
    	img[22]=(ImageView)findViewById(R.id.imageView23);
    	img[23]=(ImageView)findViewById(R.id.imageView24);
    	img[24]=(ImageView)findViewById(R.id.imageView25);
    }
    public void init()
    {
    	allFruit.clear();
    	getImageView();
        getPic();
    	
    	for(int i=0;i<6;i++)
    		fruitNum[i]=0;
    	
    	selected=r.nextInt(6);
    	num_focus=r.nextInt(8)+1;//1 to 8
    	fruitNum[selected]=num_focus;
    	
    	//int left=total-num_focus;
    	int count=25-num_focus;
    	while(count>=1)
    	{
    		int temp=r.nextInt(6);
    		if(temp==selected)
    		{
    			continue;
    		}
    		else
    		{
    			fruitNum[temp]++;
    			count--;
    		}
    	}
    	//Log.d("demo1",fruitNum[0]+" "+fruitNum[1]+" "+fruitNum[2]+" "+fruitNum[3]+" "+fruitNum[4]+" "+fruitNum[5]+" ");
    	//generating fruit objects into arraylist
    	int row=1;
    	int column=1;
    	for(int i=0;i<6;i++,row++)
    	{
    		for(int j=0;j<fruitNum[i];j++,column++)//be careful//////////////////////////////
    		{
    			if(i==selected)
    			{
    				allFruit.add(new Fruit(NumToString.get(i), pic[i], true,row,column));//know about their position
    				if(column==5)column=1;//I change it to 1 from 0
    			}
    			else
    			{
    				allFruit.add(new Fruit(NumToString.get(i), pic[i], false,row,column));//know about their position
    				if(column==5)column=1;
    			}
    		}
    	}
    	//shuffle and display
    	
    	addListener();
    	randomize();
    	display();
    	//Log.d("demo1","selected"+NumToString.get(selected));
    	//Log.d("demo1","num of selected"+num_focus);
    	
    }
    public void randomize()
    {
    	backup=new ArrayList<Fruit>(allFruit);
    	for(int i=5;i>=1;i--)
    		for(int j=5;j>=1;j--)
    		{
    			if(occupied[i][j]==true)
    			{
    				allFruit.remove(5*(i-1)+j-1);
    			}
    		}
    	//Log.d("demo1","allFruit size1"+allFruit.size());
    	Collections.shuffle(allFruit);
    	
    	for(int i=1;i<=5;i++)
    		for(int j=1;j<=5;j++)
    		{
    			if(occupied[i][j]==true)
    			{
    				allFruit.add((5*(i-1)+j-1), backup.get(5*(i-1)+j-1));
    			}
    		}
    	Log.d("demo1","allFruit size2"+allFruit.size());
    	//refresh the row and column
    	refresh();
    }
    public void addListener()
    {
    	btn1=(Button) findViewById(R.id.button1);
    	btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//randomize();
				//display();
				init();
				
			}
		});
    	btn2=(Button)findViewById(R.id.button2);
    	btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				android.os.Process.killProcess(android.os.Process.myPid()) ;
			}
		});
    	
    	img[0].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(allFruit.get(0).getName().equals(NumToString.get(selected)))
				{
					if(allFruit.get(0).getClicked())//already clicked
					{
						Log.d("demo1","already");
					}
					else
					{
						allFruit.get(0).setClicked(true);
						//allFruit.get(0)//change brightness
						int fruitNum=StringToNum.get(allFruit.get(0).getName());
						allFruit.get(0).setImage(pic2[fruitNum]);
						//allFruit.get(0).setImage(BitmapFactory.decodeResource(getResources(), pic2[i].);
						occupied[allFruit.get(0).row][allFruit.get(0).column]=true;////////////make sure it will not be shuffled
						//Log.d("demo1",allFruit.get(0).column+" "+allFruit.get(0).row+" "+occupied[allFruit.get(0).row][allFruit.get(0).column]);
						//refresh randomize
						//Log.d("demo1","right");
						lastClicked=v;
						randomize();
						display();
					}
				}
				else
				{
					Log.d("demo1","gameover");
					//gameover
					AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
			        builder.setTitle("Game over")
			        .setMessage("Play again?")
			        .setCancelable(false)
			        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Log.d("demo1", "Clicked yes");
							init();
						}
					})
					.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Log.d("demo1","Clicked No");
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					}).show();
				}
			}
		});
    	
    	img[1].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(allFruit.get(1).getName().equals(NumToString.get(selected)))
				{
					if(allFruit.get(1).getClicked())//already clicked
					{
						Log.d("demo1","already");
					}
					else
					{
						allFruit.get(1).setClicked(true);
						//allFruit.get(0)//change brightness
						int fruitNum=StringToNum.get(allFruit.get(1).getName());
						allFruit.get(1).setImage(pic2[fruitNum]);
						occupied[allFruit.get(1).row][allFruit.get(1).column]=true;
						//allFruit.get(1).setImage(BitmapFactory.decodeResource(getResources(), R.drawable.blank));
						//refresh randomize
						lastClicked=v;
						//Log.d("demo1","right");
						randomize();
						display();
					}
				}
				else
				{
					Log.d("demo1","gameover");
					//gameover
					AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
			        builder.setTitle("Game over")
			        .setMessage("Play again?")
			        .setCancelable(false)
			        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Log.d("demo1", "Clicked yes");
							init();
						}
					})
					.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Log.d("demo1","Clicked No");
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					}).show();
				}
			}
		});
    	
    	img[2].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(allFruit.get(2).getName().equals(NumToString.get(selected)))
				{
					if(allFruit.get(2).getClicked())//already clicked
					{
						Log.d("demo1","already");
					}
					else
					{
						allFruit.get(2).setClicked(true);
						//allFruit.get(0)//change brightness
						int fruitNum=StringToNum.get(allFruit.get(2).getName());
						allFruit.get(2).setImage(pic2[fruitNum]);
						occupied[allFruit.get(2).row][allFruit.get(2).column]=true;
						//allFruit.get(2).setImage(BitmapFactory.decodeResource(getResources(), R.drawable.blank));
						//refresh randomize
						//Log.d("demo1","right");
						lastClicked=v;
						randomize();
						display();
					}
				}
				else
				{
					Log.d("demo1","gameover");
					//gameover
					AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
			        builder.setTitle("Game over")
			        .setMessage("Play again?")
			        .setCancelable(false)
			        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Log.d("demo1", "Clicked yes");
							init();
						}
					})
					.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Log.d("demo1","Clicked No");
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					}).show();
				}
			}
		});
    	
    	img[3].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(allFruit.get(3).getName().equals(NumToString.get(selected)))
				{
					if(allFruit.get(3).getClicked())//already clicked
					{
						Log.d("demo1","already");
					}
					else
					{
						allFruit.get(3).setClicked(true);
						//allFruit.get(0)//change brightness
						int fruitNum=StringToNum.get(allFruit.get(3).getName());
						allFruit.get(3).setImage(pic2[fruitNum]);
						occupied[allFruit.get(3).row][allFruit.get(3).column]=true;
						//allFruit.get(3).setImage(BitmapFactory.decodeResource(getResources(), R.drawable.blank));
						//refresh randomize
						//Log.d("demo1","right");
						lastClicked=v;
						randomize();
						display();
					}
				}
				else
				{
					Log.d("demo1","gameover");
					//gameover
					AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
			        builder.setTitle("Game over")
			        .setMessage("Play again?")
			        .setCancelable(false)
			        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Log.d("demo1", "Clicked yes");
							init();
						}
					})
					.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Log.d("demo1","Clicked No");
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					}).show();
				}
			}
		});
    	
    	img[4].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(allFruit.get(4).getName().equals(NumToString.get(selected)))
				{
					if(allFruit.get(4).getClicked())//already clicked
					{
						Log.d("demo1","already");
					}
					else
					{
						allFruit.get(4).setClicked(true);
						//allFruit.get(0)//change brightness
						int fruitNum=StringToNum.get(allFruit.get(4).getName());
						allFruit.get(4).setImage(pic2[fruitNum]);
						occupied[allFruit.get(4).row][allFruit.get(4).column]=true;
						//allFruit.get(4).setImage(BitmapFactory.decodeResource(getResources(), R.drawable.blank));
						//refresh randomize
						//Log.d("demo1","right");
						lastClicked=v;
						randomize();
						display();
					}
				}
				else
				{
					Log.d("demo1","gameover");
					//gameover
					AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
			        builder.setTitle("Game over")
			        .setMessage("Play again?")
			        .setCancelable(false)
			        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Log.d("demo1", "Clicked yes");
							init();
						}
					})
					.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Log.d("demo1","Clicked No");
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					}).show();
				}
			}
		});
    	
    	img[5].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(allFruit.get(5).getName().equals(NumToString.get(selected)))
				{
					if(allFruit.get(5).getClicked())//already clicked
					{
						Log.d("demo1","already");
					}
					else
					{
						allFruit.get(5).setClicked(true);
						//allFruit.get(0)//change brightness
						//allFruit.get(5).setImage(BitmapFactory.decodeResource(getResources(), R.drawable.blank));
						int fruitNum=StringToNum.get(allFruit.get(5).getName());
						allFruit.get(5).setImage(pic2[fruitNum]);
						occupied[allFruit.get(5).row][allFruit.get(5).column]=true;
						//refresh randomize
						//Log.d("demo1","right");
						lastClicked=v;
						randomize();
						display();
					}
				}
				else
				{
					Log.d("demo1","gameover");
					//gameover
					AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
			        builder.setTitle("Game over")
			        .setMessage("Play again?")
			        .setCancelable(false)
			        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Log.d("demo1", "Clicked yes");
							init();
						}
					})
					.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Log.d("demo1","Clicked No");
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					}).show();
				}
			}
		});
    	
    	img[6].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(allFruit.get(6).getName().equals(NumToString.get(selected)))
				{
					if(allFruit.get(6).getClicked())//already clicked
					{
						Log.d("demo1","already");
					}
					else
					{
						allFruit.get(6).setClicked(true);
						//allFruit.get(0)//change brightness
						//allFruit.get(6).setImage(BitmapFactory.decodeResource(getResources(), R.drawable.blank));
						int fruitNum=StringToNum.get(allFruit.get(6).getName());
						allFruit.get(6).setImage(pic2[fruitNum]);
						occupied[allFruit.get(6).row][allFruit.get(6).column]=true;
						//refresh randomize
						//Log.d("demo1","right");
						lastClicked=v;
						randomize();
						display();
					}
				}
				else
				{
					Log.d("demo1","gameover");
					//gameover
					AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
			        builder.setTitle("Game over")
			        .setMessage("Play again?")
			        .setCancelable(false)
			        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Log.d("demo1", "Clicked yes");
							init();
						}
					})
					.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Log.d("demo1","Clicked No");
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					}).show();
				}
			}
		});
    	
    	img[7].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(allFruit.get(7).getName().equals(NumToString.get(selected)))
				{
					if(allFruit.get(7).getClicked())//already clicked
					{
						Log.d("demo1","already");
					}
					else
					{
						allFruit.get(7).setClicked(true);
						//allFruit.get(0)//change brightness
						//allFruit.get(7).setImage(BitmapFactory.decodeResource(getResources(), R.drawable.blank));
						int fruitNum=StringToNum.get(allFruit.get(7).getName());
						allFruit.get(7).setImage(pic2[fruitNum]);
						occupied[allFruit.get(7).row][allFruit.get(7).column]=true;
						//refresh randomize
						//Log.d("demo1","right");
						lastClicked=v;
						randomize();
						display();
					}
				}
				else
				{
					Log.d("demo1","gameover");
					//gameover
					AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
			        builder.setTitle("Game over")
			        .setMessage("Play again?")
			        .setCancelable(false)
			        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Log.d("demo1", "Clicked yes");
							init();
						}
					})
					.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Log.d("demo1","Clicked No");
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					}).show();
				}
			}
		});
    	
    	img[8].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(allFruit.get(8).getName().equals(NumToString.get(selected)))
				{
					if(allFruit.get(8).getClicked())//already clicked
					{
						Log.d("demo1","already");
					}
					else
					{
						allFruit.get(8).setClicked(true);
						//allFruit.get(0)//change brightness
						//allFruit.get(8).setImage(BitmapFactory.decodeResource(getResources(), R.drawable.blank));
						int fruitNum=StringToNum.get(allFruit.get(8).getName());
						occupied[allFruit.get(8).row][allFruit.get(8).column]=true;
						allFruit.get(8).setImage(pic2[fruitNum]);
						//refresh randomize
						//Log.d("demo1","right");
						lastClicked=v;
						randomize();
						display();
					}
				}
				else
				{
					Log.d("demo1","gameover");
					//gameover
					AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
			        builder.setTitle("Game over")
			        .setMessage("Play again?")
			        .setCancelable(false)
			        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Log.d("demo1", "Clicked yes");
							init();
						}
					})
					.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Log.d("demo1","Clicked No");
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					}).show();
				}
			}
		});
    	
    	img[9].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(allFruit.get(9).getName().equals(NumToString.get(selected)))
				{
					if(allFruit.get(9).getClicked())//already clicked
					{
						Log.d("demo1","already");
					}
					else
					{
						allFruit.get(9).setClicked(true);
						//allFruit.get(0)//change brightness
						//allFruit.get(9).setImage(BitmapFactory.decodeResource(getResources(), R.drawable.blank));
						int fruitNum=StringToNum.get(allFruit.get(9).getName());
						occupied[allFruit.get(9).row][allFruit.get(9).column]=true;
						allFruit.get(9).setImage(pic2[fruitNum]);
						//refresh randomize
						//Log.d("demo1","right");
						lastClicked=v;
						randomize();
						display();
					}
				}
				else
				{
					Log.d("demo1","gameover");
					//gameover
					AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
			        builder.setTitle("Game over")
			        .setMessage("Play again?")
			        .setCancelable(false)
			        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Log.d("demo1", "Clicked yes");
							init();
						}
					})
					.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Log.d("demo1","Clicked No");
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					}).show();
				}
			}
		});
    	
    	img[10].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(allFruit.get(10).getName().equals(NumToString.get(selected)))
				{
					if(allFruit.get(10).getClicked())//already clicked
					{
						Log.d("demo1","already");
					}
					else
					{
						allFruit.get(10).setClicked(true);
						//allFruit.get(0)//change brightness
						//allFruit.get(10).setImage(BitmapFactory.decodeResource(getResources(), R.drawable.blank));
						int fruitNum=StringToNum.get(allFruit.get(10).getName());
						occupied[allFruit.get(10).row][allFruit.get(10).column]=true;
						allFruit.get(10).setImage(pic2[fruitNum]);
						//refresh randomize
						//Log.d("demo1","right");
						lastClicked=v;
						randomize();
						display();
					}
				}
				else
				{
					Log.d("demo1","gameover");
					//gameover
					AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
			        builder.setTitle("Game over")
			        .setMessage("Play again?")
			        .setCancelable(false)
			        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Log.d("demo1", "Clicked yes");
							init();
						}
					})
					.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Log.d("demo1","Clicked No");
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					}).show();
				}
			}
		});
    	
    	img[11].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(allFruit.get(11).getName().equals(NumToString.get(selected)))
				{
					if(allFruit.get(11).getClicked())//already clicked
					{
						Log.d("demo1","already");
					}
					else
					{
						allFruit.get(11).setClicked(true);
						//allFruit.get(0)//change brightness
						//allFruit.get(11).setImage(BitmapFactory.decodeResource(getResources(), R.drawable.blank));
						int fruitNum=StringToNum.get(allFruit.get(11).getName());
						allFruit.get(11).setImage(pic2[fruitNum]);
						occupied[allFruit.get(11).row][allFruit.get(11).column]=true;
						//refresh randomize
						//Log.d("demo1","right");
						lastClicked=v;
						randomize();
						display();
					}
				}
				else
				{
					Log.d("demo1","gameover");
					//gameover
					AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
			        builder.setTitle("Game over")
			        .setMessage("Play again?")
			        .setCancelable(false)
			        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Log.d("demo1", "Clicked yes");
							init();
						}
					})
					.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Log.d("demo1","Clicked No");
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					}).show();
				}
			}
		});
    	
    	img[12].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(allFruit.get(12).getName().equals(NumToString.get(selected)))
				{
					if(allFruit.get(12).getClicked())//already clicked
					{
						Log.d("demo1","already");
					}
					else
					{
						allFruit.get(12).setClicked(true);
						//allFruit.get(0)//change brightness
						//allFruit.get(12).setImage(BitmapFactory.decodeResource(getResources(), R.drawable.blank));
						int fruitNum=StringToNum.get(allFruit.get(12).getName());
						allFruit.get(12).setImage(pic2[fruitNum]);
						occupied[allFruit.get(12).row][allFruit.get(12).column]=true;
						//refresh randomize
						//Log.d("demo1","right");
						lastClicked=v;
						randomize();
						display();
					}
				}
				else
				{
					Log.d("demo1","gameover");
					//gameover
					AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
			        builder.setTitle("Game over")
			        .setMessage("Play again?")
			        .setCancelable(false)
			        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Log.d("demo1", "Clicked yes");
							init();
						}
					})
					.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Log.d("demo1","Clicked No");
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					}).show();
				}
			}
		});
    	
    	img[13].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(allFruit.get(13).getName().equals(NumToString.get(selected)))
				{
					if(allFruit.get(13).getClicked())//already clicked
					{
						Log.d("demo1","already");
					}
					else
					{
						allFruit.get(13).setClicked(true);
						//allFruit.get(0)//change brightness
						//allFruit.get(13).setImage(BitmapFactory.decodeResource(getResources(), R.drawable.blank));
						int fruitNum=StringToNum.get(allFruit.get(13).getName());
						occupied[allFruit.get(13).row][allFruit.get(13).column]=true;
						allFruit.get(13).setImage(pic2[fruitNum]);
						//refresh randomize
						//Log.d("demo1","right");
						lastClicked=v;
						randomize();
						display();
					}
				}
				else
				{
					Log.d("demo1","gameover");
					//gameover
					AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
			        builder.setTitle("Game over")
			        .setMessage("Play again?")
			        .setCancelable(false)
			        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Log.d("demo1", "Clicked yes");
							init();
						}
					})
					.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Log.d("demo1","Clicked No");
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					}).show();
				}
			}
		});
    	
    	img[14].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(allFruit.get(14).getName().equals(NumToString.get(selected)))
				{
					if(allFruit.get(14).getClicked())//already clicked
					{
						Log.d("demo1","already");
					}
					else
					{
						allFruit.get(14).setClicked(true);
						//allFruit.get(0)//change brightness
						//allFruit.get(14).setImage(BitmapFactory.decodeResource(getResources(), R.drawable.blank));
						int fruitNum=StringToNum.get(allFruit.get(14).getName());
						occupied[allFruit.get(14).row][allFruit.get(14).column]=true;
						allFruit.get(14).setImage(pic2[fruitNum]);
						//refresh randomize
						//Log.d("demo1","right");
						lastClicked=v;
						randomize();
						display();
					}
				}
				else
				{
					Log.d("demo1","gameover");
					//gameover
					AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
			        builder.setTitle("Game over")
			        .setMessage("Play again?")
			        .setCancelable(false)
			        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Log.d("demo1", "Clicked yes");
							init();
						}
					})
					.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Log.d("demo1","Clicked No");
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					}).show();
				}
			}
		});
    	
    	img[15].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(allFruit.get(15).getName().equals(NumToString.get(selected)))
				{
					if(allFruit.get(15).getClicked())//already clicked
					{
						Log.d("demo1","already");
					}
					else
					{
						allFruit.get(15).setClicked(true);
						//allFruit.get(0)//change brightness
						//allFruit.get(15).setImage(BitmapFactory.decodeResource(getResources(), R.drawable.blank));
						int fruitNum=StringToNum.get(allFruit.get(15).getName());
						occupied[allFruit.get(15).row][allFruit.get(15).column]=true;
						allFruit.get(15).setImage(pic2[fruitNum]);
						//refresh randomize
						//Log.d("demo1","right");
						lastClicked=v;
						randomize();
						display();
					}
				}
				else
				{
					Log.d("demo1","gameover");
					//gameover
					AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
			        builder.setTitle("Game over")
			        .setMessage("Play again?")
			        .setCancelable(false)
			        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Log.d("demo1", "Clicked yes");
							init();
						}
					})
					.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Log.d("demo1","Clicked No");
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					}).show();
				}
			}
		});
    	
    	
    	img[16].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(allFruit.get(16).getName().equals(NumToString.get(selected)))
				{
					if(allFruit.get(16).getClicked())//already clicked
					{
						Log.d("demo1","already");
					}
					else
					{
						allFruit.get(16).setClicked(true);
						//allFruit.get(0)//change brightness
						//allFruit.get(16).setImage(BitmapFactory.decodeResource(getResources(), R.drawable.blank));
						int fruitNum=StringToNum.get(allFruit.get(16).getName());
						occupied[allFruit.get(16).row][allFruit.get(16).column]=true;
						allFruit.get(16).setImage(pic2[fruitNum]);
						//refresh randomize
						//Log.d("demo1","right");
						lastClicked=v;
						randomize();
						display();
					}
				}
				else
				{
					Log.d("demo1","gameover");
					//gameover
					AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
			        builder.setTitle("Game over")
			        .setMessage("Play again?")
			        .setCancelable(false)
			        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Log.d("demo1", "Clicked yes");
							init();
						}
					})
					.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Log.d("demo1","Clicked No");
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					}).show();
				}
			}
		});
    	
    	img[17].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(allFruit.get(17).getName().equals(NumToString.get(selected)))
				{
					if(allFruit.get(17).getClicked())//already clicked
					{
						Log.d("demo1","already");
					}
					else
					{
						allFruit.get(17).setClicked(true);
						//allFruit.get(0)//change brightness
						//allFruit.get(17).setImage(BitmapFactory.decodeResource(getResources(), R.drawable.blank));
						int fruitNum=StringToNum.get(allFruit.get(17).getName());
						occupied[allFruit.get(17).row][allFruit.get(17).column]=true;
						allFruit.get(17).setImage(pic2[fruitNum]);
						//refresh randomize
						//Log.d("demo1","right");
						lastClicked=v;
						randomize();
						display();
					}
				}
				else
				{
					Log.d("demo1","gameover");
					//gameover
					AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
			        builder.setTitle("Game over")
			        .setMessage("Play again?")
			        .setCancelable(false)
			        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Log.d("demo1", "Clicked yes");
							init();
						}
					})
					.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Log.d("demo1","Clicked No");
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					}).show();
				}
			}
		});
    	
    	img[18].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(allFruit.get(18).getName().equals(NumToString.get(selected)))
				{
					if(allFruit.get(18).getClicked())//already clicked
					{
						Log.d("demo1","already");
					}
					else
					{
						allFruit.get(18).setClicked(true);
						//allFruit.get(0)//change brightness
						//allFruit.get(18).setImage(BitmapFactory.decodeResource(getResources(), R.drawable.blank));
						int fruitNum=StringToNum.get(allFruit.get(18).getName());
						occupied[allFruit.get(18).row][allFruit.get(18).column]=true;
						allFruit.get(18).setImage(pic2[fruitNum]);
						//refresh randomize
						//Log.d("demo1","right");
						lastClicked=v;
						randomize();
						display();
					}
				}
				else
				{
					Log.d("demo1","gameover");
					//gameover
					AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
			        builder.setTitle("Game over")
			        .setMessage("Play again?")
			        .setCancelable(false)
			        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Log.d("demo1", "Clicked yes");
							init();
						}
					})
					.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Log.d("demo1","Clicked No");
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					}).show();
				}
			}
		});
    	
    	img[19].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(allFruit.get(19).getName().equals(NumToString.get(selected)))
				{
					if(allFruit.get(19).getClicked())//already clicked
					{
						Log.d("demo1","already");
					}
					else
					{
						allFruit.get(19).setClicked(true);
						//allFruit.get(0)//change brightness
						//allFruit.get(19).setImage(BitmapFactory.decodeResource(getResources(), R.drawable.blank));
						int fruitNum=StringToNum.get(allFruit.get(19).getName());
						occupied[allFruit.get(19).row][allFruit.get(19).column]=true;
						allFruit.get(19).setImage(pic2[fruitNum]);
						//refresh randomize
						//Log.d("demo1","right");
						lastClicked=v;
						randomize();
						display();
					}
				}
				else
				{
					Log.d("demo1","gameover");
					//gameover
					AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
			        builder.setTitle("Game over")
			        .setMessage("Play again?")
			        .setCancelable(false)
			        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Log.d("demo1", "Clicked yes");
							init();
						}
					})
					.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Log.d("demo1","Clicked No");
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					}).show();
				}
			}
		});
    	
    	img[20].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(allFruit.get(20).getName().equals(NumToString.get(selected)))
				{
					if(allFruit.get(20).getClicked())//already clicked
					{
						Log.d("demo1","already");
					}
					else
					{
						allFruit.get(20).setClicked(true);
						//allFruit.get(0)//change brightness
						//allFruit.get(20).setImage(BitmapFactory.decodeResource(getResources(), R.drawable.blank));
						int fruitNum=StringToNum.get(allFruit.get(20).getName());
						occupied[allFruit.get(20).row][allFruit.get(20).column]=true;
						allFruit.get(20).setImage(pic2[fruitNum]);
						//refresh randomize
						//Log.d("demo1","right");
						lastClicked=v;
						randomize();
						display();
					}
				}
				else
				{
					Log.d("demo1","gameover");
					//gameover
					AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
			        builder.setTitle("Game over")
			        .setMessage("Play again?")
			        .setCancelable(false)
			        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Log.d("demo1", "Clicked yes");
							init();
						}
					})
					.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Log.d("demo1","Clicked No");
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					}).show();
				}
			}
		});
    	
    	img[21].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(allFruit.get(21).getName().equals(NumToString.get(selected)))
				{
					if(allFruit.get(21).getClicked())//already clicked
					{
						Log.d("demo1","already");
					}
					else
					{
						allFruit.get(21).setClicked(true);
						//allFruit.get(0)//change brightness
						//allFruit.get(21).setImage(BitmapFactory.decodeResource(getResources(), R.drawable.blank));
						int fruitNum=StringToNum.get(allFruit.get(21).getName());
						occupied[allFruit.get(21).row][allFruit.get(21).column]=true;
						allFruit.get(21).setImage(pic2[fruitNum]);
						//refresh randomize
						//Log.d("demo1","right");
						lastClicked=v;
						randomize();
						display();
					}
				}
				else
				{
					Log.d("demo1","gameover");
					//gameover
					AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
			        builder.setTitle("Game over")
			        .setMessage("Play again?")
			        .setCancelable(false)
			        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Log.d("demo1", "Clicked yes");
							init();
						}
					})
					.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Log.d("demo1","Clicked No");
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					}).show();
				}
			}
		});
    	
    	img[22].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(allFruit.get(22).getName().equals(NumToString.get(selected)))
				{
					if(allFruit.get(22).getClicked())//already clicked
					{
						Log.d("demo1","already");
					}
					else
					{
						allFruit.get(22).setClicked(true);
						//allFruit.get(0)//change brightness
						//allFruit.get(22).setImage(BitmapFactory.decodeResource(getResources(), R.drawable.blank));
						int fruitNum=StringToNum.get(allFruit.get(22).getName());
						occupied[allFruit.get(22).row][allFruit.get(22).column]=true;
						allFruit.get(22).setImage(pic2[fruitNum]);
						//refresh randomize
						//Log.d("demo1","right");
						lastClicked=v;
						randomize();
						display();
					}
				}
				else
				{
					Log.d("demo1","gameover");
					//gameover
					AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
			        builder.setTitle("Game over")
			        .setMessage("Play again?")
			        .setCancelable(false)
			        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Log.d("demo1", "Clicked yes");
							init();
						}
					})
					.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Log.d("demo1","Clicked No");
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					}).show();
				}
			}
		});
    	
    	img[23].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(allFruit.get(23).getName().equals(NumToString.get(selected)))
				{
					if(allFruit.get(23).getClicked())//already clicked
					{
						Log.d("demo1","already");
					}
					else
					{
						allFruit.get(23).setClicked(true);
						//allFruit.get(0)//change brightness
						//allFruit.get(23).setImage(BitmapFactory.decodeResource(getResources(), R.drawable.blank));
						int fruitNum=StringToNum.get(allFruit.get(23).getName());
						occupied[allFruit.get(23).row][allFruit.get(23).column]=true;
						allFruit.get(23).setImage(pic2[fruitNum]);
						//refresh randomize
						//Log.d("demo1","right");
						lastClicked=v;
						randomize();
						display();
					}
				}
				else
				{
					Log.d("demo1","gameover");
					//gameover
					AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
			        builder.setTitle("Game over")
			        .setMessage("Play again?")
			        .setCancelable(false)
			        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Log.d("demo1", "Clicked yes");
							init();
						}
					})
					.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Log.d("demo1","Clicked No");
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					}).show();
				}
			}
		});
    	

    	img[24].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(allFruit.get(24).getName().equals(NumToString.get(selected)))
				{
					if(allFruit.get(24).getClicked())//already clicked
					{
						Log.d("demo1","already");
					}
					else
					{
						allFruit.get(24).setClicked(true);
						//allFruit.get(0)//change brightness
						//allFruit.get(24).setImage(BitmapFactory.decodeResource(getResources(), R.drawable.blank));
						int fruitNum=StringToNum.get(allFruit.get(24).getName());
						occupied[allFruit.get(24).row][allFruit.get(24).column]=true;
						allFruit.get(24).setImage(pic2[fruitNum]);
						//refresh randomize
						//Log.d("demo1","right");
						lastClicked=v;
						randomize();
						display();
					}
				}
				else
				{
					Log.d("demo1","gameover");
					//gameover
					AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
			        builder.setTitle("Game over")
			        .setMessage("Play again?")
			        .setCancelable(false)
			        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Log.d("demo1", "Clicked yes");
							init();
						}
					})
					.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Log.d("demo1","Clicked No");
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					}).show();
				}
			}
		});
    }
    public void refresh()
    {
    	if(num_focus==0)
    	{
    		//congrate window
    		Log.d("demo1","Found all shapes");
    		num_focus--;
    		AlertDialog.Builder builder=new AlertDialog.Builder(lastClicked.getContext());
	        builder.setTitle("Found all shapes")
	        .setMessage("Congratulations!!You have found all the "+NumToString.get(selected)+"s!!")
	        .setCancelable(false)
	        .setPositiveButton("New Game", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//Log.d("demo1", "Clicked yes");
					init();
				}
			})
			.setNegativeButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {

					//Log.d("demo1","Clicked No");
					//android.os.Process.killProcess(android.os.Process.myPid());
					dialog.dismiss();
				}
			}).show();
    	}
    	else
    	{	for(int i=0;i<total;i++)///////////change
    		{
    			allFruit.get(i).setRow(i/5+1);
    			allFruit.get(i).setColumn(i%5+1);//row to column
    		}
    		num_focus--;
    	}
    }
    public void display()
    {
    	for(int i=0;i<total;i++)
    	{
    		img[i].setImageBitmap(allFruit.get(i).image);
    	}
    	text=(TextView)findViewById(R.id.textView1);
    	text.setText("Find All "+NumToString.get(selected)+"s ("+(num_focus+1)+")");
    	
    }
}
