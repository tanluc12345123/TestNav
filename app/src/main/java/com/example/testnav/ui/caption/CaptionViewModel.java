package com.example.testnav.ui.caption;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;



import org.jetbrains.annotations.NotNull;

import java.util.List;


public class CaptionViewModel extends AndroidViewModel {

    private CaptionRepository captionRepository;
    private LiveData<List<Caption>> getAllCaptions;

    public CaptionViewModel(@NonNull @NotNull Application application) {
        super(application);
        captionRepository = new CaptionRepository(application);
        getAllCaptions = captionRepository.getAllCaptions();
    }

    public void insert(List<Caption> list){
        captionRepository.insert(list);
    }

    public LiveData<List<Caption>> getAllCaptions()
    {
        return getAllCaptions;
    }
}
