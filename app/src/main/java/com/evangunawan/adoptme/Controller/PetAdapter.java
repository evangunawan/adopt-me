package com.evangunawan.adoptme.Controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.evangunawan.adoptme.R;
import com.evangunawan.adoptme.Model.Pet;

import java.util.ArrayList;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder>{

    public interface ItemClickListener {
        void onItemClick(Pet item);
    }

    private ArrayList<Pet> pets;
    private ItemClickListener listener;

    public PetAdapter(ArrayList<Pet> data){
//        this.inflater = LayoutInflater.from(context);
        this.pets = data;
    }

    public void setOnClickListener(ItemClickListener listener){
        this.listener = listener;
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
        holder.petContinent.setText(petItem.getPetContinent());

        Glide.with(holder.itemView.getContext()).load(petItem.getThumbnail()).into(holder.petImage);

        holder.bindListener(pets.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    String getItemTitle(int id){
        return pets.get(id).getPetTitle();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder{
        private TextView petTitle;
        private TextView petSubtitle;
        private ImageView petImage;
        private TextView petContinent;
        public PetViewHolder(View v){
            super(v);
            petTitle = v.findViewById(R.id.petTitle);
            petSubtitle = v.findViewById(R.id.petSubtitle);
            petImage = v.findViewById(R.id.petImage);
            petContinent = v.findViewById(R.id.petContinent);
        }

        public void bindListener(final Pet item, final ItemClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

}
