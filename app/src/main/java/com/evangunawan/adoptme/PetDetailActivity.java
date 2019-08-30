package com.evangunawan.adoptme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.evangunawan.adoptme.Model.Pet;
import com.evangunawan.adoptme.Util.BitmapHandler;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

public class PetDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_detail);

        Intent i = getIntent();
        Pet targetPet = (Pet)i.getSerializableExtra("PetObject");
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) actionBar.setDisplayHomeAsUpEnabled(true);

        BitmapHandler bitmapHandler = new BitmapHandler(targetPet.getCarouselImageUrl());
        final ArrayList<Bitmap> petCarouselBitmaps = bitmapHandler.getProcessedBitmap();

        CarouselView petCarousel = findViewById(R.id.petCarousel);
        petCarousel.setPageCount(petCarouselBitmaps.size());
        petCarousel.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageBitmap(petCarouselBitmaps.get(position));
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
