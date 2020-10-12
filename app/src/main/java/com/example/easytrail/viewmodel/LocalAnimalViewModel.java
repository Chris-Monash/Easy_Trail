package com.example.easytrail.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easytrail.entity.LocalAnimal;
import com.example.easytrail.repository.LocalAnimalRepository;

import java.util.List;

public class LocalAnimalViewModel extends ViewModel {
    private LocalAnimalRepository localAnimalRepository;
    private MutableLiveData<List<LocalAnimal>> allLocalAnimals;

    public LocalAnimalViewModel(){
        allLocalAnimals = new MutableLiveData<>();
    }

    public void setLocalAnimals(List<LocalAnimal> localAnimals){
        allLocalAnimals.setValue(localAnimals);
    }

    public LiveData<List<LocalAnimal>> getAllLocalAnimals(){
        return localAnimalRepository.getAllLocalAnimals();
    }

    public void initializeVars(Application application){
        localAnimalRepository = new LocalAnimalRepository(application);
    }

    public void insert(LocalAnimal localAnimal){
        localAnimalRepository.insert(localAnimal);
    }

    public void insertAll(LocalAnimal... localAnimals){
        localAnimalRepository.insertAll(localAnimals);
    }

    public void deleteAll(){
        localAnimalRepository.deleteAll();
    }

    public void update(LocalAnimal... localAnimals){
        localAnimalRepository.updateLocalAnimals(localAnimals);
    }

    public void updateByID(int localAnimal_id,String localAnimal_name){
        localAnimalRepository.updateLocalAnimalsByID(localAnimal_id,localAnimal_name);
    }

    public LocalAnimal findByID(int localAnimal_id){
        return localAnimalRepository.findByID(localAnimal_id);
    }

    public void deleteByIDs(int fk_history_id, int localAnimal_id){
        localAnimalRepository.deleteByIDs(fk_history_id, localAnimal_id);
    }

    public LocalAnimal findLatestOne(){
        return localAnimalRepository.findLatestOne();
    }
}
