package com.example.testnav.ui.caption;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import org.jetbrains.annotations.NotNull;

@Database(entities = {Caption.class}, version = 1)
public abstract class CaptionDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "captions";

    public abstract CaptionDao captionDao();

    private static volatile CaptionDatabase INSTANCE;
    public static CaptionDatabase getInstance(Context context){
        if(INSTANCE == null){
            synchronized (CaptionDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context, CaptionDatabase.class,DATABASE_NAME)
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
            new CaptionDatabase.PopulateAsyncTask(INSTANCE);
        }
    };
    static class PopulateAsyncTask extends AsyncTask<Void,Void,Void>{

        private CaptionDao captionDao;
        PopulateAsyncTask(CaptionDatabase captionDatabase){
            captionDao = captionDatabase.captionDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            captionDao.deleteAll();
            return null;
        }
    }
}

