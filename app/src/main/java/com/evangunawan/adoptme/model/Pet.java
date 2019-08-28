package com.evangunawan.adoptme.model;

public class Pet {
    private String petTitle;
    private String petType;
    private String petBreed;
    private String petPhoto;

    public Pet(String title, String type, String breed, String photo) {
        this.petTitle = title;
        this.petType = type;
        this.petBreed = breed;
        this.petPhoto = photo;

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

    public String getPetPhoto() {
        return petPhoto;
    }

    public void setPetPhoto(String petPhoto) {
        this.petPhoto = petPhoto;
    }
}
