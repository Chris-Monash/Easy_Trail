package com.example.easytrail.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.easytrail.entity.LocalAnimal;

import java.util.List;

@Dao
public interface LocalAnimalDAO {
    @Query("SELECT * FROM localanimal")
    LiveData<List<LocalAnimal>> getAll();

    @Query("SELECT * FROM localanimal WHERE localAnimal_id = :localAnimal_id LIMIT 1")
    LocalAnimal findByID(int localAnimal_id);

    @Insert
    void insertAll (LocalAnimal... localAnimals);

    @Insert
    long insert(LocalAnimal localAnimal);

    @Delete
    void delete(LocalAnimal localAnimal);

    @Update//(onConflict = REPLACE)
    void updateLocalAnimals(LocalAnimal... localAnimals);

    @Query("DELETE FROM localanimal")
    void deleteAll();

    @Query("UPDATE localanimal SET localAnimal_name = :localAnimal_name WHERE localAnimal_id = :localAnimal_id")
    void updateByID(int localAnimal_id, String localAnimal_name);

    @Query("SELECT * FROM localanimal WHERE fk_history_id = :fk_history_id AND fk_history_created_date = :fk_history_created_date")
    List<LocalAnimal> findAnimalForHistory(int fk_history_id, String fk_history_created_date);

    @Query("DELETE FROM localanimal WHERE fk_history_id = :fk_history_id AND localAnimal_id = :localAnimal_id")
    void deleteByIDs(int fk_history_id, int localAnimal_id);

    @Query("SELECT * FROM localanimal ORDER BY created_date DESC LIMIT 1")
    LocalAnimal findLatestOne();




}
