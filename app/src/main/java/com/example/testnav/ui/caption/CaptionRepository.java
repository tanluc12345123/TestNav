package com.example.testnav.ui.caption;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CaptionRepository {

    private CaptionDatabase database;
    private LiveData<List<Caption>> getAllCaptions;



    public CaptionRepository(Application application){
        database = CaptionDatabase.getInstance(application);
        getAllCaptions = database.captionDao().getAllCaptions();
    }
    public void insert(List<Caption> captionList){
        new CaptionRepository.InsertAsyncTask(database).execute(captionList);
    }
    public LiveData<List<Caption>> getAllCaptions(){

        return getAllCaptions;
    }
    public void delete(){
        new CaptionRepository.DeleteAsyncTask(database).execute();
    }
    class DeleteAsyncTask extends AsyncTask<Void,Void,Void> {
        private CaptionDao captionDao;
        DeleteAsyncTask(CaptionDatabase captionDatabase){
            captionDao = captionDatabase.captionDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            captionDao.deleteAll();
            return null;
        }
    }
    class InsertAsyncTask extends AsyncTask<List<Caption>,Void,Void>{

        private CaptionDao captionDao;
        InsertAsyncTask(CaptionDatabase captionDatabase){
            captionDao = captionDatabase.captionDao();
        }
        @Override
        protected Void doInBackground(List<Caption>... lists) {
            captionDao.insert(lists[0]);
            return null;
        }
    }
}
