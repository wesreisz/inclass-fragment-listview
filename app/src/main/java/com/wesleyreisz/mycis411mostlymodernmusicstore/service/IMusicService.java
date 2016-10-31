package com.wesleyreisz.mycis411mostlymodernmusicstore.service;

import com.wesleyreisz.mycis411mostlymodernmusicstore.home.model.Song;

import java.util.List;

/**
 * Created by wesleyreisz on 10/30/16.
 */
public interface IMusicService {
    List<Song> findAll();

    Song findOne(String name);
}
