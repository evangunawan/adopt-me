package com.evangunawan.adoptme.Controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.evangunawan.adoptme.Model.Pet;
import com.evangunawan.adoptme.R;
import com.evangunawan.adoptme.Util.OnRemoveButtonClickListener;
import com.evangunawan.adoptme.Util.PetLoader;

import java.util.ArrayList;

public class AdoptedPetAdapter extends RecyclerView.Adapter<AdoptedPetAdapter.PetViewHolder> {

    private OnRemoveButtonClickListener removeButtonClickListener;
    private ArrayList<Pet> adoptedPets;
    private ArrayList<Integer> petIds;

    public AdoptedPetAdapter(ArrayList<Integer> idList){
        adoptedPets = new ArrayList<>();
        this.petIds = idList;
        adoptedPets = loadAdoptedPets(petIds);
    }

    public void registerRemoveButtonClickListener(OnRemoveButtonClickListener listener){
        this.removeButtonClickListener = listener;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adopted_pet_item,parent,false);
        AdoptedPetAdapter.PetViewHolder vh = new AdoptedPetAdapter.PetViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        Pet petItem = adoptedPets.get(position);
        String petSubtitle = petItem.getPetType() + ", "+ petItem.getPetBreed();
        holder.petTitle.setText(petItem.getPetTitle());
        holder.petSubtitle.setText(petSubtitle);
        holder.btnRemove.setOnClickListener(v -> {
            if(removeButtonClickListener!=null){
                this.removeButtonClickListener.onRemoveButtonClick(petItem,position);
            }
        });

        Glide.with(holder.itemView.getContext()).load(petItem.getThumbnail()).into(holder.petImage);
    }

    @Override
    public int getItemCount() {
        return adoptedPets.size();
    }

    private ArrayList<Pet> loadAdoptedPets(ArrayList<Integer> petIds){
        ArrayList<Pet> aPets = new ArrayList<>();
        for (int x : petIds){
            for (Pet pet : PetLoader.petList){
                if(pet.getPetId() == x){
                    aPets.add(pet);
                }
            }
        }
        return aPets;
    }

    protected static class PetViewHolder extends RecyclerView.ViewHolder{
        private TextView petTitle;
        private TextView petSubtitle;
        private ImageView petImage;
        private ImageButton btnRemove;
        private PetViewHolder(View v){
            super(v);
            petTitle = v.findViewById(R.id.aPetTitle);
            petSubtitle = v.findViewById(R.id.aPetSubtitle);
            petImage = v.findViewById(R.id.aPetImage);
            btnRemove = v.findViewById(R.id.aPetBtnDelete);
        }
    }


}
