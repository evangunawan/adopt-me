package com.evangunawan.adoptme.Util;

import android.content.Context;
import android.util.Log;

import com.evangunawan.adoptme.Model.Pet;
import com.evangunawan.adoptme.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;

public class PetLoader {

    //This class loads pet objects from JSON Raw file.

    public static ArrayList<Pet> petList;

    public static void loadPets(Context context){
        try {
            InputStream is = context.getResources().openRawResource(R.raw.pets);
            Writer wr = new StringWriter();
            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    wr.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }
            String jsonString = wr.toString();

            JSONObject json = new JSONObject(jsonString);
            JSONArray petArray = json.getJSONArray("pets");
            petList = new ArrayList<>();

            for (int i=0 ; i<petArray.length();i++){
                JSONObject itemPet = petArray.getJSONObject(i);
                Integer petId = itemPet.getInt("id");
                String petName = itemPet.getString("name");
                String petType = itemPet.getString("type");
                String petBreed = itemPet.getString("breed");
                String petContinent = itemPet.getString("continent");
                String petOwner = itemPet.getString("owner");
                String petDesc = itemPet.getString("description");
                String petThumbnail = itemPet.getString("thumbnail");
                JSONArray carouselArray = itemPet.getJSONArray("carousel");
                ArrayList<String> petCarousel = new ArrayList<>();
                for(int j=0;j<carouselArray.length();j++){
                    petCarousel.add(carouselArray.getString(j));
                }

                Pet pet = new Pet();
                pet.setPetId(petId);
                pet.setPetTitle(petName);
                pet.setPetType(petType);
                pet.setPetBreed(petBreed);
                pet.setPetContinent(petContinent);
                pet.setPetDescription(petDesc);
                pet.setPetOwner(petOwner);
                pet.setThumbnail(petThumbnail);
                pet.setCarouselImageUrl(petCarousel);

                petList.add(pet);
            }

            Log.wtf("PetLoader>>","Loaded " + petArray.length() + " pets object from JSON");


        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }
}
