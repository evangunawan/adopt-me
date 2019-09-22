package com.evangunawan.adoptme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.evangunawan.adoptme.Home.AdoptedPetFragment;
import com.evangunawan.adoptme.Model.Pet;
import com.evangunawan.adoptme.Util.BitmapHandler;
import com.evangunawan.adoptme.Util.FileHandler;
import com.synnapps.carouselview.CarouselView;

import java.util.ArrayList;

public class PetDetailActivity extends AppCompatActivity {

    private ArrayList<Bitmap> petCarouselBitmaps;
    private ProgressBar loading_spin;
    private Button btnAdopt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_detail);

        //Initialize this activity, and get data from MainActivity.
        Intent i = getIntent();
        Pet targetPet = (Pet)i.getSerializableExtra("PetObject");
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) actionBar.setDisplayHomeAsUpEnabled(true);
        loading_spin = findViewById(R.id.carousel_progress);
        btnAdopt = findViewById(R.id.btnAdopt);

        initPetData(targetPet);

        //BitmapHandler: Download image from an URL into a Bitmap object.
        //This line listens Callback from BitmapHandler when the images is ready (downloaded from the internet).
        BitmapHandler bitmapHandler = new BitmapHandler(targetPet.getCarouselImageUrl(), bitmaps -> {
            petCarouselBitmaps = bitmaps;
            Log.i("Carousel","Carousel size: " + petCarouselBitmaps.size());
            CarouselView petCarousel = findViewById(R.id.petCarousel);
            initCarousel(petCarousel);
        });

        //Load the bitmap (execute the Threads).
        bitmapHandler.loadBitmap();

        btnAdopt.setOnClickListener(v -> {
            int petId = targetPet.getPetId();
            FileHandler.addAdoptedPet(petId);
            Log.i("FileHandler","Added petID " + petId + ", Size now: " + FileHandler.getAdoptedPetIdList().size());
        });


    }

    //When called, the ui thread will initialize the CarouselView with the downloaded Image (Bitmap).
    private void initCarousel(final CarouselView carousel){
        runOnUiThread(() -> {
            loading_spin.setVisibility(View.GONE);
            carousel.setVisibility(View.VISIBLE);
            carousel.setImageListener((position, imageView) -> imageView.setImageBitmap(petCarouselBitmaps.get(position)));
            carousel.setPageCount(petCarouselBitmaps.size());
        });
    }

    private void initPetData(Pet pet){
        TextView petTitle = findViewById(R.id.txtPetDetailTitle);
        petTitle.setText(pet.getPetTitle());
        TextView petSubtitle = findViewById(R.id.txtPetDetailSubtitle);
        petSubtitle.setText(pet.getPetBreed());

        TextView petDesc = findViewById(R.id.txtPetDetailDescription);
        petDesc.setText(pet.getPetDescription());
    }

    //Handle the back button click, it will finish() this activity.
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
