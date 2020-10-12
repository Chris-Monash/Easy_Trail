package com.example.easytrail.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity
public class LocalAnimal {
    @PrimaryKey(autoGenerate = true)
    public int localAnimal_id;

    @ColumnInfo(name = "localAnimal_name")
    public String localAnimal_name;

    @ColumnInfo(name = "created_date")
    public String created_date;

    @ColumnInfo(name = "localAnimal_score")
    public int localAnimal_score;

    @ForeignKey(entity = History.class, parentColumns = "history_id", childColumns = "fk_history_id",onDelete = CASCADE, onUpdate = CASCADE)
    public int fk_history_id;

    public LocalAnimal(String localAnimal_name, String created_date, int localAnimal_score, int fk_history_id) {
        this.localAnimal_name = localAnimal_name;
        this.created_date = created_date;
        this.localAnimal_score = localAnimal_score;
        this.fk_history_id = fk_history_id;
    }

    public int getLocalAnimal_id() {
        return localAnimal_id;
    }

    public void setLocalAnimal_id(int localAnimal_id) {
        this.localAnimal_id = localAnimal_id;
    }

    public String getLocalAnimal_name() {
        return localAnimal_name;
    }

    public void setLocalAnimal_name(String localAnimal_name) {
        this.localAnimal_name = localAnimal_name;
    }

    public int getFk_history_id() {
        return fk_history_id;
    }

    public void setFk_history_id(int fk_history_id) {
        this.fk_history_id = fk_history_id;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public int getLocalAnimal_score() {
        return localAnimal_score;
    }

    public void setLocalAnimal_score(int localAnimal_score) {
        this.localAnimal_score = localAnimal_score;
    }
}
