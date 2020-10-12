package com.example.easytrail.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.easytrail.dao.LocalAnimalDAO;
import com.example.easytrail.database.LocalAnimalDatabase;
import com.example.easytrail.entity.LocalAnimal;

import java.time.LocalDate;
import java.util.List;

public class LocalAnimalRepository {

    private LocalAnimalDAO dao;
    private LiveData<List<LocalAnimal>> allLocalAnimals;
    private LocalAnimal localAnimal;

    public LocalAnimalRepository(Application application){
        LocalAnimalDatabase db = LocalAnimalDatabase.getInstance(application);
        dao = db.localAnimalDAO();
    }

    public LiveData<List<LocalAnimal>> getAllLocalAnimals(){
        allLocalAnimals = dao.getAll();
        return allLocalAnimals;
    }

    public void insert(final LocalAnimal localAnimal){
        LocalAnimalDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insert(localAnimal);
            }
        });
    }

    public void deleteAll() {
        LocalAnimalDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.deleteAll();
            }
        });
    }

    public void delete(final LocalAnimal localAnimal){
        LocalAnimalDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.delete(localAnimal);
            }
        });
    }

    public void insertAll(final LocalAnimal... localAnimals){
        LocalAnimalDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insertAll(localAnimals);
            }
        });
    }

    public void updateLocalAnimals(final LocalAnimal... localAnimals){
        LocalAnimalDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.updateLocalAnimals(localAnimals);
            }
        });
    }

    public void updateLocalAnimalsByID(final int localAnimal_id, String localAnimal_name){
        LocalAnimalDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.updateByID(localAnimal_id,localAnimal_name);
            }
        });
    }

    public LocalAnimal findByID(final int localAnimal_id){
        LocalAnimalDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                LocalAnimal localAnimal = dao.findByID(localAnimal_id);
                setLocalAnimal(localAnimal);
            }
        });
        return localAnimal;
    }

    public void setLocalAnimal(LocalAnimal localAnimal){
        this.localAnimal = localAnimal;
    }

    public void deleteByIDs(final int fk_history_id, int localAnimal_id){
        LocalAnimalDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.deleteByIDs(fk_history_id,localAnimal_id);
            }
        });
    }

    public LocalAnimal findLatestOne(){
        LocalAnimalDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                LocalAnimal localAnimal = dao.findLatestOne();
                setLocalAnimal(localAnimal);
            }
        });
        return localAnimal;
    }

}
