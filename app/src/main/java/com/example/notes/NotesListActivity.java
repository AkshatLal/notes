package com.example.notes;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class NotesListActivity extends ListActivity {
	private ListView mNotesList;
	private Button mNewNote;
	private Button mNewNote1;
	private final static String NOTES = "Notes.txt";
	private List<String> mNData = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		checkForFile();
	}

	public void onResume() {
		super.onResume();
		checkForFile();
		try {			
			InputStream in = openFileInput(NOTES);
			mNData.clear();
			if (in != null){
				InputStreamReader tmp = new InputStreamReader(in);
				BufferedReader reader = new BufferedReader(tmp);
				String str, str1="";								
				while ((str = reader.readLine()) != null){
					if(!str.equalsIgnoreCase("#%*"))
					{
						str1 = str1 + str + "\n";
					}
					else
					{
						mNData.add(str1);
						str1="";
					}
				}				
				in.close();
			}
			ArrayAdapter<String> taskList = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mNData);
            setListAdapter(taskList);			
			
		} catch (java.io.FileNotFoundException e) {
			// that's OK, we probably haven't created it yet
		} catch (Throwable t){
			Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_SHORT).show();
		}		
	}	
	public class MyClickListener implements OnClickListener{		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent mNewNoteIntent = new Intent(NotesListActivity.this,
				AddNoteActivity.class);
				startActivity(mNewNoteIntent);
			}		
	}
	public void checkForFile(){
		File file = getBaseContext().getFileStreamPath(NOTES);
		if(!file.exists()){			
			setContentView(R.layout.nonote);
			mNewNote1 = (Button) findViewById(R.id.nolistbutton1);
			mNewNote1.setOnClickListener(new MyClickListener());
		}else{
		setContentView(R.layout.main);
		mNotesList = (ListView) findViewById(android.R.id.list);
		mNewNote = (Button) findViewById(R.id.listbutton1);			
		mNewNote.setOnClickListener(new MyClickListener());
		}
	}
}

