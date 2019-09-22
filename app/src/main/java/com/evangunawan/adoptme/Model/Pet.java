package com.evangunawan.adoptme.Model;

import com.synnapps.carouselview.CarouselView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Pet  implements Serializable {
    private int petId;
    private String petTitle, petType, petBreed, thumbnail, petOwner, petDescription, petContinent;
    private CarouselView petCarousel;
    private ArrayList<String> carouselImageUrl;

    public Pet(){

    }
    public Pet(HashMap<String,Object> item) {
            petId = (int) item.get("id");
            petTitle = (String) item.get("name");
            petType = (String) item.get("type");
            petBreed = (String) item.get("breed");
            petOwner = (String) item.get("owner");
            petDescription = (String) item.get("description");
            petContinent = (String) item.get("continent");
            thumbnail = (String) item.get("thumbnail");
            carouselImageUrl = (ArrayList<String>) item.get("carousel");
    }
//    public Pet(String title, String type, String breed, String photo) {
//        this.petTitle = title;
//        this.petType = type;
//        this.petBreed = breed;
//        this.thumbnail = photo;
//    }

    public Integer getPetId() { return petId; }

    public String getPetDescription(){
        return petDescription;
    }

    public String getPetTitle() {
        return petTitle;
    }

    public String getPetType() {
        return petType;
    }
    public String getPetBreed() {
        return petBreed;
    }
    public String getThumbnail() {
        return thumbnail;
    }

//    public void setCarouselImageUrl(ArrayList<String> list){
//        this.carouselImageUrl = list;
//    }
    public ArrayList<String> getCarouselImageUrl(){
        return carouselImageUrl;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public void setPetTitle(String petTitle) {
        this.petTitle = petTitle;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setPetOwner(String petOwner) {
        this.petOwner = petOwner;
    }

    public void setPetDescription(String petDescription) {
        this.petDescription = petDescription;
    }

    public void setPetContinent(String petContinent) {
        this.petContinent = petContinent;
    }

    public void setPetCarousel(CarouselView petCarousel) {
        this.petCarousel = petCarousel;
    }

    public void setCarouselImageUrl(ArrayList<String> carouselImageUrl) {
        this.carouselImageUrl = carouselImageUrl;
    }
}
