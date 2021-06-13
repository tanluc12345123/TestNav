package com.example.testnav.ui.photo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "photos")
public class Photo {
    @PrimaryKey(autoGenerate = true)
    private int photoId;

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    @SerializedName("link_Meme")
    @ColumnInfo(name = "link_Meme")
    private String link_Meme;

    public Photo(String link_Meme) {

        this.link_Meme = link_Meme;
    }

    public String getLink_Meme() {
        return link_Meme;
    }
}