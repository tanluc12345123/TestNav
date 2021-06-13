package com.example.testnav.ui.caption;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "captions")
public class Caption {
    @PrimaryKey(autoGenerate = true)
    private int captionId;

    @SerializedName("Caption")
    @ColumnInfo(name = "Caption")
    private String Caption;

    @SerializedName("link_Icon")
    @ColumnInfo(name = "link_Icon")
    private String link_Icon;

    public Caption(String Caption, String link_Icon) {
        this.Caption = Caption;
        this.link_Icon = link_Icon;
    }

    public String getCaption() {
        return Caption;
    }
    public String getLink_Icon(){return link_Icon;}

    public int getCaptionId() {
        return captionId;
    }

    public void setCaptionId(int captionId) {
        this.captionId = captionId;
    }

    @Override
    public String toString() {
        return "Caption{" +
                "captionId=" + captionId +
                ", Caption='" + Caption + '\'' +
                ", link_Icon='" + link_Icon + '\'' +
                '}';
    }
}