package com.example.easytrail.model;

public class AnimalResult {
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

    public AnimalResult(int animal_id, String comm_name, String sci_name, String animal_type, String animal_size, String animal_diet, String animal_location, String conservation_status, String regional_distribution, String abundance, String vic_conservation_status, String act, String animal_image, String animal_habitat, int animal_score) {
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
    }

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
}
