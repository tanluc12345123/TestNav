package com.example.testnav.ui.photo;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import org.jetbrains.annotations.NotNull;

@Database(entities = {Photo.class}, version = 1)
public abstract class PhotoDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "photos";

    public abstract PhotoDao photoDao();

    private static volatile PhotoDatabase INSTANCE;
    public static PhotoDatabase getInstance(Context context){
        if(INSTANCE == null){
            synchronized (PhotoDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context, PhotoDatabase.class,DATABASE_NAME)
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PhotoDatabase.PopulateAsyncTask(INSTANCE);
        }
    };
    static class PopulateAsyncTask extends AsyncTask<Void,Void,Void>{

        private PhotoDao photoDao;
        PopulateAsyncTask(PhotoDatabase photoDatabase){
            photoDao = photoDatabase.photoDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            photoDao.deleteAll();
            return null;
        }
    }
}
