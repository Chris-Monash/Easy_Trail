package com.example.easytrail.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.easytrail.entity.History;
import com.example.easytrail.entity.LocalAnimal;

import java.util.List;

@Dao
public interface HistoryDAO {
    @Query ( "SELECT * FROM history" )
    List<History> getAll();

    @Query ( "SELECT * FROM history WHERE history_id = :history_id LIMIT 1" )
    History findByID ( int history_id );

    @Insert
    void insertAll ( History... histories );

    @Insert
    long insert (History history );

//    @Insert
//    void insertLocalAnimals(LocalAnimal localAnimal);

    @Delete
    void delete ( History history );

    @Update
    void updateHistories ( History... histories );

    @Query("UPDATE history SET current_score = :current_score WHERE history_id = :history_id")
    void updateScores (int history_id, int current_score);

    @Query("SELECT * FROM history ORDER BY created_date DESC LIMIT 1")
    History getLatestOne();

    @Query("DELETE FROM history")
    void deleteAll();

    @Query("SELECT * FROM history ORDER BY created_date DESC LIMIT 1")
    History getNewestOne();

}
