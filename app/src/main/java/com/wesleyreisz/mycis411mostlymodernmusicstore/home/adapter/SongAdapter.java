package com.wesleyreisz.mycis411mostlymodernmusicstore.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wesleyreisz.mycis411mostlymodernmusicstore.R;
import com.wesleyreisz.mycis411mostlymodernmusicstore.home.model.Song;

import java.util.List;

/**
 * Created by wesleyreisz on 10/16/16.
 */
public class SongAdapter extends ArrayAdapter<Song>{
    private Context mContext;
    private List<Song>mSongs;
    public SongAdapter(Context context, int textViewResourceId, List<Song> songs) {
        super(context, textViewResourceId, songs);
        mContext = context;
        mSongs = songs;
    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view==null){
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_for_each_song,parent,false);
        }
        final Song song =mSongs.get(position);

        TextView textViewArtist = (TextView)view.findViewById(R.id.textViewArtistName);
        textViewArtist.setText(song.getArtistName());
        TextView textViewSong = (TextView)view.findViewById(R.id.textViewSongName);
        textViewSong.setText(song.getSongTitle());
        TextView textViewRelease = (TextView)view.findViewById(R.id.textViewReleaseDate);
        textViewRelease.setText(song.getSongPublishedDate().toString());

        return view;
    }
}
