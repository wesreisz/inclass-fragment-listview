package com.wesleyreisz.mycis411mostlymodernmusicstore.home.model;

import java.util.Date;

/**
 * Created by wesleyreisz on 10/16/16.
 */
public class Song {
    private String songId;
    private String songTitle;
    private String albumTitle;
    private Date songPublishedDate;
    private Date lastUpdatedDate;
    private String youtubeId;
    private String artistName;

    public Song(){}

    public Song(String songId, String songTitle, String artist, String album, Date songPublishedDate) {
        this.songId = songId;
        this.songTitle=songTitle;
        this.artistName=artist;
        this.albumTitle=album;
        this.songPublishedDate=songPublishedDate;
    }

    public Song(String songTitle, String artist, String album, Date songPublishedDate) {
        this.songTitle=songTitle;
        this.artistName=artist;
        this.albumTitle=album;
        this.songPublishedDate=songPublishedDate;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public Date getSongPublishedDate() {
        return songPublishedDate;
    }

    public void setSongPublishedDate(Date songPublishedDate) {
        this.songPublishedDate = songPublishedDate;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        if (!songId.equals(song.songId)) return false;
        if (!songTitle.equals(song.songTitle)) return false;
        if (albumTitle != null ? !albumTitle.equals(song.albumTitle) : song.albumTitle != null)
            return false;
        if (songPublishedDate != null ? !songPublishedDate.equals(song.songPublishedDate) : song.songPublishedDate != null)
            return false;
        if (lastUpdatedDate != null ? !lastUpdatedDate.equals(song.lastUpdatedDate) : song.lastUpdatedDate != null)
            return false;
        if (youtubeId != null ? !youtubeId.equals(song.youtubeId) : song.youtubeId != null)
            return false;
        return artistName != null ? artistName.equals(song.artistName) : song.artistName == null;

    }

    @Override
    public int hashCode() {
        int result = songId.hashCode();
        result = 31 * result + songTitle.hashCode();
        result = 31 * result + (albumTitle != null ? albumTitle.hashCode() : 0);
        result = 31 * result + (songPublishedDate != null ? songPublishedDate.hashCode() : 0);
        result = 31 * result + (lastUpdatedDate != null ? lastUpdatedDate.hashCode() : 0);
        result = 31 * result + (youtubeId != null ? youtubeId.hashCode() : 0);
        result = 31 * result + (artistName != null ? artistName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songId='" + songId + '\'' +
                ", songTitle='" + songTitle + '\'' +
                ", albumTitle='" + albumTitle + '\'' +
                ", songPublishedDate=" + songPublishedDate +
                ", lastUpdatedDate=" + lastUpdatedDate +
                ", youtubeId='" + youtubeId + '\'' +
                ", artistName='" + artistName + '\'' +
                '}';
    }
}
