package com.evangunawan.adoptme.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.evangunawan.adoptme.PetAdapter;
import com.evangunawan.adoptme.R;
import com.evangunawan.adoptme.model.Pet;

import java.util.ArrayList;

public class AdoptionFragment extends Fragment {

    private RecyclerView petRecyclerView;
    private RecyclerView.Adapter petAdapter;
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
        petRecyclerView.setAdapter(petAdapter);

    }

    private void initPetData(){
        pets = new ArrayList<>();
        pets.add(new Pet("Nobi", "Dog", "Pomeranian", "https://www.petguide.com/wp-content/uploads/2013/02/pomeranian1.jpg"));
        pets.add(new Pet("Bella", "Dog", "Pomeranian", "https://www.petguide.com/wp-content/uploads/2013/02/pomeranian1.jpg"));
    }
}
