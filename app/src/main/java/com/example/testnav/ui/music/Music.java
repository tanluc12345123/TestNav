package com.example.testnav.ui.music;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "musics")
public class Music {

    @PrimaryKey(autoGenerate = true)
    private int musicId;

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    @SerializedName("Song")
    @ColumnInfo(name = "Song")
    public String Song;

    @SerializedName("link_Music")
    @ColumnInfo(name = "link_Music")
    public String link_Music;

    public Music(String Song, String link_Music) {
        this.Song = Song;
        this.link_Music = link_Music;
    }

    public String getLink_Music() {
        return link_Music;
    }
    public String getSong() {
        return Song;
    }
}