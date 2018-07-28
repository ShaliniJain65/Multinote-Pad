package com.example.nicho.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Info_activity extends AppCompatActivity {
    private static final String TAG = "Info_activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_activity);

       TextView textview1=findViewById(R.id.textView4);TextView textview2=findViewById(R.id.textView5);
       TextView textview3=findViewById(R.id.textView6);

        Intent intent=getIntent();

        if(intent.hasExtra("MultiNotes"))
        {
            String name=intent.getStringExtra("MultiNotes");
            String author=intent.getStringExtra("author");
            String version=intent.getStringExtra("version");
            textview1.setText(name);
            textview2.setText(author);
            textview3.setText(version);
        }
    }


}
