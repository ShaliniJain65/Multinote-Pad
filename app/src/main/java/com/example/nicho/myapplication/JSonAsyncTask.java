package com.example.nicho.myapplication;

import android.media.MediaActionSound;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by shalini on 2/13/2018.
 */

public class JSonAsyncTask extends AsyncTask<String,Void,String> {
    private static final String TAG = "JSonAsyncTask";
    MainActivity mainActivity;

    public JSonAsyncTask(MainActivity ma) {
        mainActivity=ma;
    }

    @Override
    protected String doInBackground(String... strings) {
        String filename = strings[0];
        String fileAsString = null;
        try {
            int n;
            FileInputStream fis = mainActivity.getApplicationContext().openFileInput(filename);
            StringBuffer fileContent = new StringBuffer("");

            byte[] buffer = new byte[1024*1024];

            while ((n = fis.read(buffer)) != -1) {
                fileContent.append(new String(buffer, 0, n));
            }
            fileAsString = fileContent.toString();
            Log.d(TAG, "doInBackground: json string /n "+ fileAsString);
            Log.d(TAG, "doInBackground: "+  fileAsString);

        } catch (Exception e) {
                e.printStackTrace();
        }
        return fileAsString;
    }
   @Override
   protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String fileAsString) {
        ArrayList<Notes> notearraylist=new ArrayList<>();
        try{
            super.onPostExecute(fileAsString);
            Notes notes;
            //inputstream and json reader is used to read the file name and encoding format
            InputStream stream;
            stream=new ByteArrayInputStream(fileAsString.getBytes(mainActivity.getString(R.string.encoding)));
            Log.d(TAG, "onPostExecute: "+ fileAsString);
           // inputstream = mainActivity.getApplicationContext().openFileInput(mainActivity.getString(R.string.filename));
            JsonReader jsonreader = new JsonReader(new InputStreamReader(stream , mainActivity.getString(R.string.encoding)));
            Log.d(TAG, "onPostExecute: "+notearraylist.size());

            //start reading
            jsonreader.beginArray();
            while(jsonreader.hasNext()) {
                notes=new Notes();
                jsonreader.beginObject();

                //go in the if loop till has things to read
                while (jsonreader.hasNext()) {
                    String textvalue = jsonreader.nextName();
                    if (textvalue.equals("title")) {
                        notes.setTitle(jsonreader.nextString());
                    } else if (textvalue.equals("date")) {
                        notes.setDate(jsonreader.nextString());
                    } else if (textvalue.equals("description")) {
                        notes.setDescription(jsonreader.nextString());
                    } else {
                        jsonreader.skipValue();
                    }
                }
                //end of object
                jsonreader.endObject();
                Log.d(TAG, "onPostExecute: "+notearraylist.size());
                notearraylist.add(notes);
                Log.d(TAG, "onPostExecute: "+notearraylist.size());
            }
            jsonreader.endArray();
        }

        //catch statements for handling exceptions
        catch (FileNotFoundException filenotfound)
        {
            Log.d(TAG, "loadFile: No File Exists");
        }
        catch (IOException e) {
            Log.d(TAG, "loadFile: IO Exception");
            e.printStackTrace();
        }
        catch (Exception e)
        {
            Log.d(TAG, "loadFile: Exception e");
        }
        mainActivity.fetchAsynTaskData(notearraylist);
    }
 }

