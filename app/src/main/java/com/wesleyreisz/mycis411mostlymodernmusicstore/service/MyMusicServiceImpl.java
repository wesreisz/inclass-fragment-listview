package com.wesleyreisz.mycis411mostlymodernmusicstore.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.wesleyreisz.mycis411mostlymodernmusicstore.home.adapter.SongAdapter;
import com.wesleyreisz.mycis411mostlymodernmusicstore.home.model.Song;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by wesleyreisz on 10/30/16.
 */

public class MyMusicServiceImpl implements IMusicService {
    private static final String MY_MUSIC_ENDPOINT = "http://10.0.3.2:3000/songs";
    private static final String DEBUG_TAG = "Music";
    private Context mContext;
    private SongAdapter mSongAdapter;
    public MyMusicServiceImpl(Context context, SongAdapter songAdapter){
        mContext = context;
        mSongAdapter = songAdapter;
        new GetMyMusicAsyncTask().execute(MY_MUSIC_ENDPOINT);
    }

    List<Song> songs = new ArrayList<Song>();

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

            try {
                JSONArray songArray = null;
                songArray = new JSONArray(content);

                for(int i = 0; i < songArray.length(); i++){
                    JSONObject entryObjects = songArray.getJSONObject(i);
                    Song song = new Song();
                    song.setSongTitle(entryObjects.getString("title"));
                    song.setAlbumTitle(entryObjects.getString("album"));
                    song.setArtistName(entryObjects.getString("artist"));
                    song.setSongPublishedDate(getDate(entryObjects.getString("published_date")));
                    songs.add(song);
                }

            } catch (JSONException e) {
                Toast.makeText(mContext,content,Toast.LENGTH_LONG).show();
                Log.d(DEBUG_TAG,content);
                e.printStackTrace();
            }

            Log.d(DEBUG_TAG,content);
            mSongAdapter.addAll(songs);
            mSongAdapter.notifyDataSetChanged();
        }

        // Reads a url
        private String downloadUrl(String myurl) throws IOException {
            InputStream is = null;

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
                String contentAsString = readIt(is);
                return contentAsString;
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }

        // Reads an InputStream and converts it to a String.
        public String readIt(InputStream stream) throws IOException {
            BufferedReader r = new BufferedReader(new InputStreamReader(stream));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }
            return total.toString();
        }

    }

    private static Date getDate(String input) {
        Calendar c = Calendar.getInstance();
        c.set(1,1,1,0,0);//need to fix
        return c.getTime();
    }
}
