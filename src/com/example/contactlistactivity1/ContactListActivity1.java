package com.example.contactlistactivity1;

import android.app.Activity;
import java.io.*;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.provider.ContactsContract.Data;

import java.text.SimpleDateFormat;
import java.util.*;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

public class ContactListActivity1 extends ListActivity {
	

	static ArrayList<String> ids = new ArrayList<String>();
	static ArrayList<Integer> invisibilty = new ArrayList<Integer>();
	

	ListView lv;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View v = getLayoutInflater().inflate(R.layout.contactlistitem, null);
		lv = getListView();

		ContactList1 contactList = this.getContacts();
		ArrayAdapter<Contact1> adapter = new ContactAdapter1(this,
				contactList.getContacts());

		// System.out.println("I want to know lv::"+lv);
		// LoadMore button
		Button btnLoadMore = new Button(this);
		btnLoadMore.setText("Form the group");

		lv.addFooterView(btnLoadMore);

		btnLoadMore.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				SimpleAlert(v);
				// ids.clear();

			}

		});
		// lv.setAdapter(adapter);
		setListAdapter(adapter);

	}
	//private static int save = -1;
	int pos = 0;
	int save = -1;
	

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		invisibilty.add(position);
		System.out.println("I want to see the position:"+ invisibilty);
		v.setVisibility(View.GONE);
		
		
	
		// v.setBackgroundColor(Color.BLUE);
		// getListView().setVisibility(View.GONE);
		/*This enables the list to disappear from the list */
		// v.setVisibility(Color.WHITE);
		// 
		
		 System.out.println("position::" +position);

		 

         /* if (pos == 0) {
              if (save != -1) {
                  l.getChildAt(save).setBackgroundColor(Color.BLUE);
                 // v.setVisibility(Color.WHITE);
              }
              save = position;
              System.out.println("save::"+save +"position::" +position);
              pos++;
          //    Log.d("Pos = 0", "Running");

          } *//*else {
              l.getChildAt(save).setBackgroundColor(Color.BLUE);
              save = position;
              pos = 0;
              Log.d("Pos # 0", "Running");
          } */

	
    

		
			 Object o = this.getListAdapter().getItem(position);			 
			 Contact1 c = (Contact1) o;		
			 //ContactAdapter1 con = new ContactAdapter1(null, null) ;
			  //invisibilty.add(position);
			// System.out.println("These are the clicked buttons that needs to be disappeared" +con.invisibilty.add(position) );
			 
			 
			// Toast.makeText(this, c.getDisplayName(),
			// Toast.LENGTH_SHORT).show();
			
			 
			 // Toast.makeText(this, c.getId(), Toast.LENGTH_SHORT).show();
			 ids.add(c.getDisplayName()); 
			 System.out.println("Testing id" + ids);
			
			 
			 
		}

	
	

	

	public void SimpleAlert(View v) { // passing the view object from the
										// adapter class
		if (ids == null) {
			System.out.println("I am the reason");
		} else {
			System.out.println("Am I getting the signal");
			System.out.println("Tracking the clicks !! ");
			for (int i = 0; i < ids.size(); i++) {
				System.out.println(ids.get(i));
			}

			// converting array list to array
			final CharSequence[] items = ids.toArray(new CharSequence[ids
					.size()]);

			boolean[] itemsChecked = new boolean[items.length];

			System.out.println(Arrays.toString(items));
			Builder builder = new AlertDialog.Builder(v.getContext());

			builder.setTitle("Groups you have chosen:");

			builder.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							
						
					
						//	This code will write the group in a file
							try
						    {
							
								File sdcard= Environment.getExternalStorageDirectory();
								// Creating a file in a time based event
								Date date = new Date();
							//	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;

								File dir = new File(sdcard.getAbsolutePath() + "/dir1/dir2");
								dir.mkdirs();
							//	File file = new File(dir, dateFormat.format(date)+ ".txt");
								File file = new File(dir, "my_group.txt");


								FileOutputStream fOut = new FileOutputStream(file,true);
							
								PrintWriter pw = new PrintWriter(fOut);

								for(int i=0;i<ids.size();i++)
								{
							
									pw.write(ids.get(i));
								    
									pw.write("\n");
								
								
								}
							
								pw.write("**********************************");
								pw.write("\n");
								pw.close();
								fOut.close();
								
						}
							 catch(IOException e)
							    {
							         e.printStackTrace();
							       
							    }

							
							ids.clear();
							System.out.println("Freeing arraylist" + ids);
							
						
						}});

			builder.setMultiChoiceItems(items, itemsChecked,
					new DialogInterface.OnMultiChoiceClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which,
								boolean isChecked) {

						}
					});

			AlertDialog alert = builder.create();
			alert.show();
		}
	}

	/*
	 * public void just_print() {
	 * 
	 * //System.out.println("Am I getting the signal"); for(int i=0;
	 * i<ids.size(); i++) { // System.out.println(ids.get(i)); }
	 * 
	 * 
	 * }
	 */

	//Handling the Menu button
	public boolean onKeyUp(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_MENU) {
	    	final Context context = this;
	    	
	    	// Intent intent = new Intent(context, App2Activity.class);
             //startActivity(intent);  
             startActivity(new Intent(ContactListActivity1.this,App2Activity.class)); 
             finish();

            // System.out.println("Am I Coming here  !! "); 
             /*
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
	        
	        setContentView(R.layout.file_view);
	        TextView tv = (TextView)findViewById(R.id.text_view);
	        //System.out.println("Am I Coming here  !! ");
	        System.out.println(text);	        
	        tv.setText(text);
	   */    
	    }
	   
	    return true;
	}


	private ContactList1 getContacts() {
		ContactList1 contactList = new ContactList1();
		Uri uri = ContactsContract.Contacts.CONTENT_URI;
		ContentResolver cr = getContentResolver();
		String sortOrder = ContactsContract.Contacts.DISPLAY_NAME
				+ " COLLATE LOCALIZED ASC";
		Cursor cur = cr.query(uri, null, null, null, sortOrder);

		if (cur.getCount() > 0) {
			String id;
			String img;
			String name;
			while (cur.moveToNext()) {
				Contact1 c = new Contact1();
				id = cur.getString(cur
						.getColumnIndex(ContactsContract.Contacts._ID));
				img = cur.getString(cur
						.getColumnIndex(ContactsContract.Contacts.PHOTO_ID));
				name = cur
						.getString(cur
								.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				// System.out.println("checking going on !!");
				// System.out.println("ID:"+ids.get(0)+":Name"+name);

				final Bitmap photo;
				if (img != null) {
					photo = queryContactBitmap(img);

				} else {
					photo = null;
				}

				c.setId(id);
				// c.setId(img);

				c.setImage(photo);
				c.setDisplayName(name);
				contactList.addContact(c);

			}
		}
		// cur.close();
		return contactList;
	}

	private Bitmap queryContactBitmap(String photoId) {
		final Cursor photo = managedQuery(Data.CONTENT_URI,
				new String[] { Photo.PHOTO }, // column where the blob is stored
				Data._ID + "=?", // select row by id
				new String[] { photoId }, // filter by the given photoId
				null);

		final Bitmap photoBitmap;
		if (photo.moveToFirst()) {
			byte[] photoBlob = photo.getBlob(photo.getColumnIndex(Photo.PHOTO));
			photoBitmap = BitmapFactory.decodeByteArray(photoBlob, 0,
					photoBlob.length);
		} else {
			photoBitmap = null;
		}
		photo.close();

		return photoBitmap;

	}
}
