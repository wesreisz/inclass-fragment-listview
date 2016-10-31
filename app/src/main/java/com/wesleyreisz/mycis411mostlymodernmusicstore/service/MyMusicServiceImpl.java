package com.wesleyreisz.mycis411mostlymodernmusicstore.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.wesleyreisz.mycis411mostlymodernmusicstore.home.adapter.SongAdapter;
import com.wesleyreisz.mycis411mostlymodernmusicstore.home.model.Song;
import com.wesleyreisz.mycis411mostlymodernmusicstore.util.HttpUtil;
import com.wesleyreisz.mycis411mostlymodernmusicstore.util.StringUtil;

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
    private List<Song> songs = new ArrayList<Song>();

    public MyMusicServiceImpl(Context context, SongAdapter songAdapter){
        mContext = context;
        mSongAdapter = songAdapter;
    }

    @Override
    public List<Song> findAll() {
        if (songs.size()<=0)
            refresh();

        return songs;
    }

    @Override
    public Song findOne(String name) {
        if (songs.size()<=0)
            refresh();

        for(Song song:songs){
            if(song.getSongTitle().equals(name)){
                return song;
            }
        }
        return new Song();
    }

    public void refresh(){
        new GetMyMusicAsyncTask().execute(MY_MUSIC_ENDPOINT);
    }

    protected class GetMyMusicAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return HttpUtil.downloadUrl(urls[0]);
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
                    song.setSongPublishedDate(StringUtil.getDate(entryObjects.getString("published_date")));
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

    }
}
