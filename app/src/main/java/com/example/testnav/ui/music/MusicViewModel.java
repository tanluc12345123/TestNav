package com.example.testnav.ui.music;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class MusicViewModel extends AndroidViewModel {

    private MusicRepository musicRepository;
    private LiveData<List<Music>> getAllMusics;

    public MusicViewModel(@NonNull @NotNull Application application) {
        super(application);
        musicRepository = new MusicRepository(application);
        getAllMusics = musicRepository.getAllMusics();
    }

    public void insert(List<Music> list){
        musicRepository.insert(list);
    }

    public LiveData<List<Music>> getAllMusics()
    {
        return getAllMusics;
    }
}