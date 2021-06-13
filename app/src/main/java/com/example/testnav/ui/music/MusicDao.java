package com.example.testnav.ui.music;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.testnav.ui.caption.Caption;

import java.util.List;

@Dao
public interface MusicDao {


    @Query("SELECT * FROM musics")
    LiveData<List<Music>> getAllMusics();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Music> musicList);

    @Query("DELETE FROM musics")
    void deleteAll();
}
