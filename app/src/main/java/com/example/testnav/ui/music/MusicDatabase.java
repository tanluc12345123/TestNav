package com.example.testnav.ui.music;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.example.testnav.ui.caption.Caption;
import com.example.testnav.ui.caption.CaptionDao;

import org.jetbrains.annotations.NotNull;

@Database(entities = {Music.class}, version = 1)
public abstract class MusicDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "musics";

    public abstract MusicDao musicDao();

    private static volatile MusicDatabase INSTANCE;
    public static MusicDatabase getInstance(Context context){
        if(INSTANCE == null){
            synchronized (MusicDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context, MusicDatabase.class,DATABASE_NAME)
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
            new MusicDatabase.PopulateAsyncTask(INSTANCE);
        }
    };
    static class PopulateAsyncTask extends AsyncTask<Void,Void,Void>{

        private MusicDao musicDao;
        PopulateAsyncTask(MusicDatabase musicDatabase){
            musicDao = musicDatabase.musicDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            musicDao.deleteAll();
            return null;
        }
    }
}
