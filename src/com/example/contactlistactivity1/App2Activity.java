package com.example.contactlistactivity1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

public class App2Activity extends ContactListActivity1 {
	
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		System.out.println("Am I Coming here  !! ");
		setContentView(R.layout.file_view);
		
		
		
		
		 File sdcard= Environment.getExternalStorageDirectory();
	        File dir = new File(sdcard.getAbsolutePath() + "/dir1/dir2");
		
			File file = new File(dir, "my_group.txt");
	     
	        StringBuilder text = new StringBuilder();
	        try {
	            BufferedReader br = new BufferedReader(new FileReader(file));
	            String line;

	            while ((line = br.readLine()) != null) {
	                text.append(line);
	                text.append('\n');
	                System.out.println("I want to see the change !! ");
	            }
	        }
	        catch (IOException e) {
	            
	        	e.printStackTrace();
	        	

	        }
	        
	       
	        TextView tv = (TextView)findViewById(R.id.text_view);
	        System.out.println("Am I Coming here  !! ");
	        System.out.println(text);	        
	        tv.setText(text);
	       
	}

}
