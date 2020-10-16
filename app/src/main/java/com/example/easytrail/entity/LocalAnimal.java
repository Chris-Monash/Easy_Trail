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

    @ColumnInfo(name = "localAnimal_abundance")
    public String localAnimal_abundance;

    @ColumnInfo (name = "localAnimal_conservation")
    public String localAnimal_conservation;

    @ColumnInfo (name = "localAnimal_habitat")
    public String localAnimal_habitat;

    @ColumnInfo(name = "localAnimal_image")
    public String localAnimal_image;

    @ForeignKey(entity = History.class, parentColumns = "history_id", childColumns = "fk_history_id",onDelete = CASCADE, onUpdate = CASCADE)
    public int fk_history_id;

    @ForeignKey(entity = History.class,parentColumns = "created_date", childColumns = "fk_history_created_date",onDelete = CASCADE, onUpdate = CASCADE)
    public String fk_history_created_date;

    public LocalAnimal(String localAnimal_name, String created_date, int localAnimal_score, String localAnimal_abundance, String localAnimal_conservation, String localAnimal_habitat, String localAnimal_image, int fk_history_id, String fk_history_created_date) {
        this.localAnimal_name = localAnimal_name;
        this.created_date = created_date;
        this.localAnimal_score = localAnimal_score;
        this.localAnimal_abundance = localAnimal_abundance;
        this.localAnimal_conservation = localAnimal_conservation;
        this.localAnimal_habitat = localAnimal_habitat;
        this.localAnimal_image = localAnimal_image;
        this.fk_history_id = fk_history_id;
        this.fk_history_created_date = fk_history_created_date;
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

    public String getLocalAnimal_abundance() {
        return localAnimal_abundance;
    }

    public void setLocalAnimal_abundance(String localAnimal_abundance) {
        this.localAnimal_abundance = localAnimal_abundance;
    }

    public String getLocalAnimal_conservation() {
        return localAnimal_conservation;
    }

    public void setLocalAnimal_conservation(String localAnimal_conservation) {
        this.localAnimal_conservation = localAnimal_conservation;
    }

    public String getLocalAnimal_habitat() {
        return localAnimal_habitat;
    }

    public void setLocalAnimal_habitat(String localAnimal_habitat) {
        this.localAnimal_habitat = localAnimal_habitat;
    }

    public String getLocalAnimal_image() {
        return localAnimal_image;
    }

    public void setLocalAnimal_image(String localAnimal_image) {
        this.localAnimal_image = localAnimal_image;
    }

    public String getFk_history_created_date() {
        return fk_history_created_date;
    }

    public void setFk_history_created_date(String fk_history_created_date) {
        this.fk_history_created_date = fk_history_created_date;
    }
}
