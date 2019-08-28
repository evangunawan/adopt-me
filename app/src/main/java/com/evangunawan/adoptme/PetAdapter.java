package com.evangunawan.adoptme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.evangunawan.adoptme.model.Pet;

import java.util.ArrayList;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Pet> pets;

    public PetAdapter(ArrayList<Pet> data){
//        this.inflater = LayoutInflater.from(context);
        this.pets = data;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = inflater.inflate(R.layout.pet_list_item,parent,false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_list_item,parent,false);
        PetViewHolder vh = new PetViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        Pet petItem = pets.get(position);
        String petSubtitle = petItem.getPetType() + ", "+ petItem.getPetBreed();

        holder.petTitle.setText(petItem.getPetTitle());
        holder.petSubtitle.setText(petSubtitle);

        Glide.with(holder.itemView.getContext()).load(petItem.getPetPhoto()).into(holder.petImage);
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder{
        private TextView petTitle;
        private TextView petSubtitle;
        private ImageView petImage;
        public PetViewHolder(View v){
            super(v);
            petTitle = v.findViewById(R.id.petTitle);
            petSubtitle = v.findViewById(R.id.petSubtitle);
            petImage = v.findViewById(R.id.petImage);
        }
    }

    String getItemTitle(int id){
        return pets.get(id).getPetTitle();
    }


}
