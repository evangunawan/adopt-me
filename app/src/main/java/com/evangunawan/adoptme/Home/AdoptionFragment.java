package com.evangunawan.adoptme.Home;

import android.content.Intent;
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
import com.evangunawan.adoptme.Util.PetLoader;

import java.util.ArrayList;
import java.util.HashMap;

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
//        pets = new ArrayList<>();
//        for(HashMap<String,Object> item : PetLoader.petHashList){
//            Pet pet = new Pet(item);
//            pets.add(pet);
//        }
        pets = PetLoader.petList;
    }
}
