package com.example.testnav.ui.photo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PhotoRepository {

    private PhotoDatabase database;
    private LiveData<List<Photo>> getAllPhotos;



    public PhotoRepository(Application application){
        database = PhotoDatabase.getInstance(application);
        getAllPhotos = database.photoDao().getAllPhotos();
    }
    public void insert(List<Photo> photoList){
        new PhotoRepository.InsertAsyncTask(database).execute(photoList);
    }
    public LiveData<List<Photo>> getAllCaptions(){

        return getAllPhotos;
    }
    public void delete(){
        new PhotoRepository.DeleteAsyncTask(database).execute();
    }
    class DeleteAsyncTask extends AsyncTask<Void,Void,Void>{
        private PhotoDao photoDao;
        DeleteAsyncTask(PhotoDatabase photoDatabase){
            photoDao = photoDatabase.photoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            photoDao.deleteAll();
            return null;
        }
    }
    class InsertAsyncTask extends AsyncTask<List<Photo>,Void,Void>{

        private PhotoDao photoDao;
        InsertAsyncTask(PhotoDatabase photoDatabase){
            photoDao = photoDatabase.photoDao();
        }
        @Override
        protected Void doInBackground(List<Photo>... lists) {
            photoDao.insert(lists[0]);
            return null;
        }
    }
}
