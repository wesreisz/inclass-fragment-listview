package com.wesleyreisz.mycis411mostlymodernmusicstore.service;

import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import com.wesleyreisz.mycis411mostlymodernmusicstore.home.model.Song;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by wesleyreisz on 10/30/16.
 */

public class MyMusicServiceImpl implements IMusicService {
    private static final String MY_MUSIC_ENDPOINT = "http://10.0.3.2:3000/songs";
    private static final String DEBUG_TAG = "Music";
    private Context mContext;
    public MyMusicServiceImpl(Context context){
        mContext = context;
        new GetMyMusicAsyncTask().execute(MY_MUSIC_ENDPOINT);
    }
    @Override
    public List<Song> findAll() {
        return null;
    }

    @Override
    public Song findOne(String name) {
        return null;
    }

    protected class GetMyMusicAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        @Override
        protected void onPostExecute(String content) {
            super.onPostExecute(content);

            Toast.makeText(mContext,content,Toast.LENGTH_LONG).show();
            Log.d(DEBUG_TAG,content);
            
            //set the adapter set teh list and update here
        }

        // Reads a url
        private String downloadUrl(String myurl) throws IOException {
            InputStream is = null;
            // Only display the first 500 characters of the retrieved
            // web page content.
            int len = 500;

            try {
                URL url = new URL(myurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
                int response = conn.getResponseCode();
                Log.d(DEBUG_TAG, "The response is: " + response);
                is = conn.getInputStream();

                // Convert the InputStream into a string
                String contentAsString = readIt(is, len);
                return contentAsString;

                // Makes sure that the InputStream is closed after the app is
                // finished using it.
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }

        // Reads an InputStream and converts it to a String.
        public String readIt(InputStream stream, int len) throws IOException {
            Reader reader = null;
            reader = new InputStreamReader(stream, "UTF-8");
            char[] buffer = new char[len];
            reader.read(buffer);
            return new String(buffer);
        }
    }
}
