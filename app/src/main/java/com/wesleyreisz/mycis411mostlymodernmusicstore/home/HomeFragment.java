package com.wesleyreisz.mycis411mostlymodernmusicstore.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wesleyreisz.mycis411mostlymodernmusicstore.R;
import com.wesleyreisz.mycis411mostlymodernmusicstore.home.adapter.SongAdapter;
import com.wesleyreisz.mycis411mostlymodernmusicstore.home.model.Song;
import com.wesleyreisz.mycis411mostlymodernmusicstore.service.MockMusicService;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        List<Song> songs = new MockMusicService().findAll();
        ListView listViewSongs = (ListView)view.findViewById(R.id.listViewMusicList);
        SongAdapter songAdapter = new SongAdapter(getActivity(),R.layout.fragment_home,songs);
        listViewSongs.setAdapter(songAdapter);

        return view;
    }

}
