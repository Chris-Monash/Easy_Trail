package com.example.easytrail.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.easytrail.dao.HistoryDAO;
import com.example.easytrail.database.LocalAnimalDatabase;
import com.example.easytrail.entity.History;

import java.util.List;

public class HistoryRepository {
    private HistoryDAO dao;
    private List<History> allHistories;
    private History history;

    public HistoryRepository(Application application){
        LocalAnimalDatabase db = LocalAnimalDatabase.getInstance(application);
        dao = db.historyDAO();
    }

    public List<History> getAllHistories(){
        allHistories = dao.getAll();
        return allHistories;
    }

    public History getNewestOne(){
        LocalAnimalDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                History tempHistory = dao.getNewestOne();
                setHistory(tempHistory);
            }
        });
        return history;
    }

    public void insert(final History history){
        LocalAnimalDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insert(history);
            }
        });
    }

    public void deleteAll(){
        LocalAnimalDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.deleteAll();
            }
        });
    }

    public void delete(final History history){
        LocalAnimalDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.delete(history);
            }
        });
    }

    public void insertAll(final History... histories){
        LocalAnimalDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insertAll(histories);
            }
        });
    }

    public void updateHistories(final History... histories){
        LocalAnimalDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.updateHistories(histories);
            }
        });
    }

    public void updateScore(final int history_id, int current_score){
        LocalAnimalDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.updateScores(history_id,current_score);
            }
        });
    }

    public History findByID(final int history_id){
        LocalAnimalDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                History tempHistory;
                tempHistory = dao.findByID(history_id);
                setHistory(tempHistory);
            }
        });
        return history;
    }

    public void setHistory(History history){
        this.history = history;
    }
}
