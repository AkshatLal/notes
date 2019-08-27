package com.example.notes;



import android.app.Activity;
import java.io.OutputStreamWriter;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNoteActivity extends Activity {
    /** Called when the activity is first created. */
	private EditText mHeading;
	private EditText mNote;
	private Button mSaveButton;
	private final static String NOTES="Notes.txt";
	
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnote);
        mHeading = (EditText)findViewById(R.id.editText1);
    	mNote = (EditText)findViewById(R.id.editText2);
    	mSaveButton = (Button)findViewById(R.id.button1);
    	mSaveButton.setOnClickListener(new buttonClick());    	
    }
    public class buttonClick implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub				
			finish();		
		}
    }
    public void onPause(){
    	super.onPause();
    	String mHeadingText = mHeading.getText().toString();
		String mNoteText = mNote.getText().toString();
    	try {
    		OutputStreamWriter out=
    		new OutputStreamWriter(openFileOutput(NOTES, Context.MODE_APPEND));
    		out.append(mHeadingText+":\n"+mNoteText+"\n#%*\n");
    		out.close();
    		}
    		catch (Throwable t) {
    		Toast.makeText(this, "Exception: "+ t.toString(), Toast.LENGTH_SHORT).show();
    		}
    }   
}
