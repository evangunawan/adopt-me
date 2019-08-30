package com.evangunawan.adoptme.Model;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

public class Pet  implements Serializable {
    private String petTitle;
    private String petType;
    private String petBreed;
    private String thumbnail;
    private String description;
    private ArrayList<Bitmap> petCarousel;

    public Pet(){ }


    public Pet(String title, String type, String breed, String photo) {
        this.petTitle = title;
        this.petType = type;
        this.petBreed = breed;
        this.thumbnail = photo;

    }

    public String getPetTitle() {
        return petTitle;

    }

    public void setPetTitle(String petTitle) {
        this.petTitle = petTitle;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Bitmap> getPetCarousel() {
        return petCarousel;
    }

    public void addPetCarousel(Bitmap bm){
        this.petCarousel.add(bm);
    }
}
