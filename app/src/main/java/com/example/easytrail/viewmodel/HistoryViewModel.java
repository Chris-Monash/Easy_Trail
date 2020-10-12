package com.example.easytrail.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easytrail.entity.History;
import com.example.easytrail.repository.HistoryRepository;

public class HistoryViewModel extends ViewModel {
    private HistoryRepository historyRepository;
    private MutableLiveData<History> historyMutableLiveData;

    public HistoryViewModel(){
        historyMutableLiveData = new MutableLiveData<>();
    }

//    public void setHistoryLiveData(History historyLiveData){
//        historyMutableLiveData.setValue(historyLiveData);
//    }

//    public LiveData<History> getHistoryLiveData(){
//        return historyRepository.getHistoryLiveData();
//    }

    public void initializeVars(Application application){
        historyRepository = new HistoryRepository(application);
    }

    public void insert(History history){
        historyRepository.insert(history);
    }

    public void insertAll(History... histories){
        historyRepository.insertAll(histories);
    }

    public void deleteAll(){
        historyRepository.deleteAll();
    }

    public void update(History... histories){
        historyRepository.updateHistories(histories);
    }

    public void updateScore(int history_id, int current_score){
        historyRepository.updateScore(history_id, current_score);
    }

    public History findByID(int history_id){
        return historyRepository.findByID(history_id);
    }

    public History getNewestOne(){
        return historyRepository.getNewestOne();
    }
}
