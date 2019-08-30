package com.evangunawan.adoptme.Model;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.evangunawan.adoptme.Util.BitmapHandler;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.io.Serializable;
import java.util.ArrayList;

public class Pet  implements Serializable {
    private String petTitle, petType, petBreed, thumbnail, description;
    private CarouselView petCarousel;
    private ArrayList<String> carouselImageUrl;

    public Pet() { }
    public Pet(String title, String type, String breed, String photo) {
        this.petTitle = title;
        this.petType = type;
        this.petBreed = breed;
        this.thumbnail = photo;
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

    public void setCarouselImageUrl(ArrayList<String> list){
        this.carouselImageUrl = list;
    }
    public ArrayList<String> getCarouselImageUrl(){
        return carouselImageUrl;
    }

}
