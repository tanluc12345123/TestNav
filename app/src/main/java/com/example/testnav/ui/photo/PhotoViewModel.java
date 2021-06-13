package com.example.testnav.ui.photo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class PhotoViewModel extends AndroidViewModel {

    private PhotoRepository photoRepository;
    private LiveData<List<Photo>> getAllPhotos;

    public PhotoViewModel(@NonNull @NotNull Application application) {
        super(application);
        photoRepository = new PhotoRepository(application);
        getAllPhotos = photoRepository.getAllCaptions();
    }

    public void insert(List<Photo> list){
        photoRepository.insert(list);
    }

    public LiveData<List<Photo>> getAllPhotos()
    {
        return getAllPhotos;
    }
}
