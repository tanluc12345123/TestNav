package com.example.testnav.ui.photo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import java.util.List;
@Dao
public interface PhotoDao {
    @Query("SELECT * FROM photos")
    LiveData<List<Photo>> getAllPhotos();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Photo> photoList);

    @Query("DELETE FROM photos")
    void deleteAll();
}
