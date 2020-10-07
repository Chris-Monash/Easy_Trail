package com.example.easytrail.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TrailResult implements Parcelable {
    private int trail_id;
    private String trail_name;
    private String address;
    private String route_type;
    private String distance;
    private String complete_time;
    private String facilities;
    private String description;
    private String trail_image;
    private String environment;
    private String difficulty_name;
    private String difficulty_image;

    public TrailResult(Parcel in){
        this.trail_id = in.readInt();
        this.trail_name = in.readString();
        this.address = in.readString();
        this.route_type = in.readString();
        this.distance = in.readString();
        this.complete_time = in.readString();
        this.facilities = in.readString();
        this.description = in.readString();
        this.trail_image = in.readString();
        this.environment = in.readString();
        this.difficulty_name = in.readString();
        this.difficulty_image = in.readString();
    }



    public TrailResult(int trail_id, String trail_name, String address, String route_type, String distance, String complete_time, String facilities, String description, String trail_image, String environment, String difficulty_name, String difficulty_image) {
        this.trail_id = trail_id;
        this.trail_name = trail_name;
        this.address = address;
        this.route_type = route_type;
        this.distance = distance;
        this.complete_time = complete_time;
        this.facilities = facilities;
        this.description = description;
        this.trail_image = trail_image;
        this.environment = environment;
        this.difficulty_name = difficulty_name;
        this.difficulty_image = difficulty_image;
    }
    public void writeToParcel(Parcel parcel, int flags){
        parcel.writeInt(trail_id);
        parcel.writeString(trail_name);
        parcel.writeString(address);
        parcel.writeString(route_type);
        parcel.writeString(distance);
        parcel.writeString(complete_time);
        parcel.writeString(facilities);
        parcel.writeString(description);
        parcel.writeString(trail_image);
        parcel.writeString(environment);
        parcel.writeString(difficulty_name);
        parcel.writeString(difficulty_image);

    }

    public int describeContents(){
        return 0;
    }

    public static final Creator<TrailResult> CREATOR = new Creator<TrailResult>() {
        @Override
        public TrailResult createFromParcel(Parcel source) {
            return new TrailResult(source);
        }

        @Override
        public TrailResult[] newArray(int size) {
            return new TrailResult[size];
        }
    };

    public int getTrail_id() {
        return trail_id;
    }

    public void setTrail_id(int trail_id) {
        this.trail_id = trail_id;
    }

    public String getTrail_name() {
        return trail_name;
    }

    public void setTrail_name(String trail_name) {
        this.trail_name = trail_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoute_type() {
        return route_type;
    }

    public void setRoute_type(String route_type) {
        this.route_type = route_type;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getComplete_time() {
        return complete_time;
    }

    public void setComplete_time(String complete_time) {
        this.complete_time = complete_time;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrail_image() {
        return trail_image;
    }

    public void setTrail_image(String trail_image) {
        this.trail_image = trail_image;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getDifficulty_name() {
        return difficulty_name;
    }

    public void setDifficulty_name(String difficulty_name) {
        this.difficulty_name = difficulty_name;
    }

    public String getDifficulty_image() {
        return difficulty_image;
    }

    public void setDifficulty_image(String difficulty_image) {
        this.difficulty_image = difficulty_image;
    }
}
