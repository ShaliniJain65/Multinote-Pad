package com.example.nicho.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.TestCase;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;

public class NewNote_activity extends AppCompatActivity {

    private String formattedDate;

    private static final String TAG = "NewNote_activity";
    EditText title;
    EditText description;

    private Notes notes;
    Intent data;
    ArrayList<Notes> notearraylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: in create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note_activity);

        title = (EditText)findViewById(R.id.title);
        description =(EditText) findViewById(R.id.description);

        data = getIntent();
        notes = (Notes) data.getSerializableExtra("Notes");
        if(notes!=null)
        {
            Log.d(TAG, "onCreate: "+ notes.getTitle());
            title.setText(notes.getTitle());
            description.setText(notes.getDescription());
        }
    }

    protected void onResume() {
        Log.d(TAG, "onResume: Resume");
        super.onResume();

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.newnote_menu, menu);
        return true;
    }
  /*  protected void onStop()
    {
        Log.d(TAG, "onStop: In Stop Method");
        try {
            saveNotes();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        super.onStop();
    }*/
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.save_menu:
                Log.d(TAG, "onOptionsItemSelected: ");
                formattedDate=new SimpleDateFormat("EEE MMM  d, HH:mm a").format(Calendar.getInstance().getTime());
                notes.setTitle(title.getText().toString());
                notes.setDescription(description.getText().toString());
                notes.setDate(formattedDate);
                data.putExtra("noteOBJ", notes);
                setResult(RESULT_OK, data);

                finish();
            default:

        }
        return super.onOptionsItemSelected(item);
    }
    protected void onPause(){
        Log.d(TAG, "onPause: Pause");


      notes.setDate(new SimpleDateFormat("EEE MMM  d, HH:mm a").format(Calendar.getInstance().getTime()));
        super.onPause();
    }
/*
    public void doReverse(View v)
    {
        Log.d(TAG, "doReverse: IN REVERSE");
        Collections.reverse(notearraylist);
        
    }*/
    public void onBackPressed()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "onBackPressed: BACK");
                notes.setTitle(title.getText().toString());
                notes.setDescription(description.getText().toString());
                notes.setDate(new SimpleDateFormat("EEE MMM  d, HH:mm a").format(Calendar.getInstance().getTime()));
                data.putExtra("noteOBJ", notes);
                setResult(RESULT_OK, data);

                Log.d(TAG, "onClick: be");
                finish();
                Log.d(TAG, "onClick: af");
             }
            });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
        builder.setMessage("Save Note  'Set DVR'?");
        builder.setTitle("Your Note is Not Saved !");

        AlertDialog dialog = builder.create();
        dialog.show();

        
    }

}


