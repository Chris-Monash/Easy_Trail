package com.example.easytrail.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.easytrail.dao.HistoryDAO;
import com.example.easytrail.dao.LocalAnimalDAO;
import com.example.easytrail.entity.History;
import com.example.easytrail.entity.LocalAnimal;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {LocalAnimal.class, History.class}, version = 2, exportSchema = false)
public abstract class LocalAnimalDatabase extends RoomDatabase {
    public abstract LocalAnimalDAO localAnimalDAO();
    public abstract HistoryDAO historyDAO();
    private static LocalAnimalDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static synchronized LocalAnimalDatabase getInstance(final Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), LocalAnimalDatabase.class, "LocalAnimalDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}

