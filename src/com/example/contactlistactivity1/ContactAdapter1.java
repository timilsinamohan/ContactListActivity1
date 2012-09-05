package com.example.contactlistactivity1;

import java.util.ArrayList;
import java.util.List;

import android.R.color;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Button;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import android.os.Bundle;

public class ContactAdapter1 extends ArrayAdapter<Contact1> {
	
	
	
	private final List<Contact1> _contacts;
	private final Activity _context;
	public int index=0,cnt;
	//ListView lv;

	public ContactAdapter1(Activity context, List<Contact1> contacts) {
		super(context, R.layout.contactlistitem, contacts);
		this._contacts = contacts;
		this._context = context;
	}

	static class ViewHolder {
		protected TextView text;
		private Contact1 _contact;
		public ImageView imageview;

		protected void setContact(Contact1 contact) {
			text.setText(contact.getDisplayName());
			imageview.setImageBitmap(contact.getImage());
			_contact = contact;
		}

		protected Contact1 getContact() {
			return _contact;
		}
	}

	@Override
	public Contact1 getItem(int position) {
		System.out.println("Checking current position:"
				+ _contacts.get(position));
		return _contacts.get(position);
	}

	@Override
	
	public View getView(int position, View convertView, ViewGroup parent) {
		
	    ContactListActivity1 con = null;
	    View view = null;
	    LayoutInflater inflater = _context.getLayoutInflater();
		view = inflater.inflate(R.layout.contactlistitem, null);
		final ViewHolder viewHolder = new ViewHolder();
		viewHolder.text = (TextView) view.findViewById(R.id.txtDisplayName);
		viewHolder.imageview = (ImageView) view
				.findViewById(R.id.contact_image);
		viewHolder.setContact(_contacts.get(position));		
		
		view.setTag(viewHolder);
	    
		
		boolean blnFound = con.invisibilty.contains(position);
		if(blnFound)
		{
		    System.out.println("Disable the position number::" + position);
		    viewHolder.text.setVisibility(View.INVISIBLE);
		    viewHolder.imageview.setVisibility(View.INVISIBLE);
		}
		
		//if(invisibilty.get(position)!=position){convertView.setVisibility(View.GONE);}
		
		return view;
	}
	

}
