package com.example.easytrail.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity
public class History {
    @PrimaryKey(autoGenerate = true)
    public int history_id;

    @ColumnInfo(name = "trail_name")
    public String trail_name;

    @ColumnInfo(name = "current_score")
    public int current_score;

    @ColumnInfo(name = "created_date")
    public String created_date;



    public History(String trail_name, int current_score, String created_date) {
        this.trail_name = trail_name;
        this.current_score = current_score;
        this.created_date = created_date;
    }

    public int getHistory_id() {
        return history_id;
    }

    public void setHistory_id(int history_id) {
        this.history_id = history_id;
    }

    public String getTrail_name() {
        return trail_name;
    }

    public void setTrail_name(String trail_name) {
        this.trail_name = trail_name;
    }

    public int getCurrent_score() {
        return current_score;
    }

    public void setCurrent_Score(int current_score) {
        this.current_score = current_score;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }


}
