package com.example.easytrail.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AnimalResult implements Parcelable {
    private int animal_id;
    private String comm_name;
    private String sci_name;
    private String animal_type;
    private String animal_size;
    private String animal_diet;
    private String animal_location;
    private String conservation_status;
    private String regional_distribution;
    private String abundance;
    private String vic_conservation_status;
    private String act;
    private String animal_image;
    private String animal_habitat;
    private int animal_score;
    private String active_time;
    private String inhabit_area;

    public AnimalResult(Parcel in){
        this.animal_id = in.readInt();
        this.comm_name = in.readString();
        this.sci_name = in.readString();
        this.animal_type = in.readString();
        this.animal_size = in.readString();
        this.animal_diet = in.readString();
        this.animal_location = in.readString();
        this.conservation_status = in.readString();
        this.regional_distribution = in.readString();
        this.abundance = in.readString();
        this.vic_conservation_status = in.readString();
        this.act = in.readString();
        this.animal_image = in.readString();
        this.animal_habitat = in.readString();
        this.animal_score = in.readInt();
        this.active_time = in.readString();
        this.inhabit_area= in.readString();
    }

    public AnimalResult(int animal_id, String comm_name, String sci_name, String animal_type, String animal_size, String animal_diet, String animal_location, String conservation_status, String regional_distribution, String abundance, String vic_conservation_status, String act, String animal_image, String animal_habitat, int animal_score, String active_time, String inhabit_area) {
        this.animal_id = animal_id;
        this.comm_name = comm_name;
        this.sci_name = sci_name;
        this.animal_type = animal_type;
        this.animal_size = animal_size;
        this.animal_diet = animal_diet;
        this.animal_location = animal_location;
        this.conservation_status = conservation_status;
        this.regional_distribution = regional_distribution;
        this.abundance = abundance;
        this.vic_conservation_status = vic_conservation_status;
        this.act = act;
        this.animal_image = animal_image;
        this.animal_habitat = animal_habitat;
        this.animal_score = animal_score;
        this.active_time = active_time;
        this.inhabit_area = inhabit_area;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(animal_id);
        dest.writeString(comm_name);
        dest.writeString(sci_name);
        dest.writeString(animal_type);
        dest.writeString(animal_size);
        dest.writeString(animal_diet);
        dest.writeString(animal_location);
        dest.writeString(conservation_status);
        dest.writeString(regional_distribution);
        dest.writeString(abundance);
        dest.writeString(vic_conservation_status);
        dest.writeString(act);
        dest.writeString(animal_image);
        dest.writeString(animal_habitat);
        dest.writeInt(animal_score);
        dest.writeString(active_time);
        dest.writeString(inhabit_area);



    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AnimalResult> CREATOR = new Creator<AnimalResult>() {
        @Override
        public AnimalResult createFromParcel(Parcel source) {
            return new AnimalResult(source);
        }

        @Override
        public AnimalResult[] newArray(int size) {
            return new AnimalResult[size];
        }
    };

    public int getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }

    public String getComm_name() {
        return comm_name;
    }

    public void setComm_name(String comm_name) {
        this.comm_name = comm_name;
    }

    public String getSci_name() {
        return sci_name;
    }

    public void setSci_name(String sci_name) {
        this.sci_name = sci_name;
    }

    public String getAnimal_type() {
        return animal_type;
    }

    public void setAnimal_type(String animal_type) {
        this.animal_type = animal_type;
    }

    public String getAnimal_size() {
        return animal_size;
    }

    public void setAnimal_size(String animal_size) {
        this.animal_size = animal_size;
    }

    public String getAnimal_diet() {
        return animal_diet;
    }

    public void setAnimal_diet(String animal_diet) {
        this.animal_diet = animal_diet;
    }

    public String getAnimal_location() {
        return animal_location;
    }

    public void setAnimal_location(String animal_location) {
        this.animal_location = animal_location;
    }

    public String getConservation_status() {
        return conservation_status;
    }

    public void setConservation_status(String conservation_status) {
        this.conservation_status = conservation_status;
    }

    public String getRegional_distribution() {
        return regional_distribution;
    }

    public void setRegional_distribution(String regional_distribution) {
        this.regional_distribution = regional_distribution;
    }

    public String getAbundance() {
        return abundance;
    }

    public void setAbundance(String abundance) {
        this.abundance = abundance;
    }

    public String getVic_conservation_status() {
        return vic_conservation_status;
    }

    public void setVic_conservation_status(String vic_conservation_status) {
        this.vic_conservation_status = vic_conservation_status;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getAnimal_image() {
        return animal_image;
    }

    public void setAnimal_image(String animal_image) {
        this.animal_image = animal_image;
    }

    public String getAnimal_habitat() {
        return animal_habitat;
    }

    public void setAnimal_habitat(String animal_habitat) {
        this.animal_habitat = animal_habitat;
    }

    public int getAnimal_score() {
        return animal_score;
    }

    public void setAnimal_score(int animal_score) {
        this.animal_score = animal_score;
    }

    public String getActive_time() {
        return active_time;
    }

    public void setActive_time(String active_time) {
        this.active_time = active_time;
    }

    public String getInhabit_area() {
        return inhabit_area;
    }

    public void setInhabit_area(String inhabit_area) {
        this.inhabit_area = inhabit_area;
    }
}
