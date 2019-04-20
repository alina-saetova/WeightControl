package com.example.diana.weightcontrol;

import android.app.Application;
import android.arch.persistence.room.Room;

public class App extends Application {

    private static App instance;

    private DataBaseHelper db;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        db = Room.databaseBuilder(getApplicationContext(), DataBaseHelper.class, "data-database")
                .allowMainThreadQueries()
                .build();
    }

    public DataBaseHelper getDatabaseInstance() {
        return db;
    }
}
