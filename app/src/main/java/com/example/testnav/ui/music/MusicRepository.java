package com.example.testnav.ui.music;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import java.util.List;

public class MusicRepository {

    private MusicDatabase database;
    private LiveData<List<Music>> getAllMusics;



    public MusicRepository(Application application){
        database = MusicDatabase.getInstance(application);
        getAllMusics = database.musicDao().getAllMusics();
    }
    public void insert(List<Music> musicList){
        new MusicRepository.InsertAsyncTask(database).execute(musicList);
    }
    public LiveData<List<Music>> getAllMusics(){

        return getAllMusics;
    }
    public void delete(){
        new MusicRepository.DeleteAsyncTask(database).execute();
    }
    class DeleteAsyncTask extends AsyncTask<Void,Void,Void> {
        private MusicDao musicDao;
        DeleteAsyncTask(MusicDatabase musicDatabase){
            musicDao = musicDatabase.musicDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            musicDao.deleteAll();
            return null;
        }
    }
    class InsertAsyncTask extends AsyncTask<List<Music>,Void,Void>{

        private MusicDao musicDao;
        InsertAsyncTask(MusicDatabase musicDatabase){
            musicDao = musicDatabase.musicDao();
        }
        @Override
        protected Void doInBackground(List<Music>... lists) {
            musicDao.insert(lists[0]);
            return null;
        }
    }
}
