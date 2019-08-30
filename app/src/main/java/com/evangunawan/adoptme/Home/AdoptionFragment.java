package com.evangunawan.adoptme.Home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.evangunawan.adoptme.Controller.PetAdapter;
import com.evangunawan.adoptme.PetDetailActivity;
import com.evangunawan.adoptme.R;
import com.evangunawan.adoptme.Model.Pet;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class AdoptionFragment extends Fragment {

    private RecyclerView petRecyclerView;
    private PetAdapter petAdapter;
    private ArrayList<Pet> pets;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_adoption, container, false);
        petRecyclerView = v.findViewById(R.id.petRecView);
        initPetData();
        initRecyclerView();
        return v;
    }

    private void initRecyclerView(){
        petRecyclerView.setHasFixedSize(true);
        petRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        petAdapter = new PetAdapter(pets);
        petAdapter.setOnClickListener(new PetAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Pet item) {
                Intent i = new Intent(getActivity(), PetDetailActivity.class);
                i.putExtra("PetObject",item);
                startActivity(i);
            }
        });
        petRecyclerView.setAdapter(petAdapter);
    }

    private void initPetData(){
        pets = new ArrayList<>();
        ArrayList<String> petCarouselImgs = new ArrayList<>();
        petCarouselImgs.add("https://www.pets4homes.co.uk/images/articles/4688/large/how-popular-is-the-pomeranian-dog-breed-5ab0cc9437fb0.jpg");
        petCarouselImgs.add("https://g77v3827gg2notadhhw9pew7-wpengine.netdna-ssl.com/wp-content/uploads/2017/09/how-to-train-a-pomeranian_canna-pet-1024x683.jpg");

        Pet nobi = new Pet("Nobi","Dog","Pomeranian","https://www.petguide.com/wp-content/uploads/2013/02/pomeranian1.jpg");
        nobi.setCarouselImageUrl(petCarouselImgs);
        pets.add(nobi);

        Pet bella = new Pet("Bella", "Dog", "Pomeranian", "https://www.petguide.com/wp-content/uploads/2013/02/pomeranian1.jpg");
        bella.setCarouselImageUrl(petCarouselImgs);
        pets.add(bella);
    }
}
