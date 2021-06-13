package com.example.testnav.ui.caption;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import java.util.List;

@Dao
public interface CaptionDao {


    @Query("SELECT * FROM captions")
    LiveData<List<Caption>> getAllCaptions();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Caption> captionList);

    @Query("DELETE FROM captions")
    void deleteAll();
}
