package com.evangunawan.adoptme.Util;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//This class controls the file input and output to record and keep the adopted pets data.
//When user adopting a pet, it will write a json object to the device system.
//It can be modified to load a realtime data from a REST API.

public class FileHandler {

    private static FileReader fileReader = null;
    private static FileWriter fileWriter = null;
    private static BufferedReader bufReader = null;
    private static BufferedWriter bufWriter = null;
    private static final String FILE_NAME = "adopted-pets";
    private static File file;
    private static JSONObject jsonObject;

    public FileHandler(Context context){
        file = new File(context.getFilesDir(), FILE_NAME);
        if(!file.exists()){
            try{
                file.createNewFile();
                fileWriter = new FileWriter(file.getAbsoluteFile());
                bufWriter = new BufferedWriter(fileWriter);
                bufWriter.write("{\"saved_pets\":[]}");
                bufWriter.close();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        readFile();
    }

    public static void addAdoptedPet(int pet_id){
        try {
            readFile();
            jsonObject.getJSONArray("saved_pets").put(pet_id);
            saveToFile(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static void removeAdoptedPet(int pet_id){
        if(file!=null){
            try {
                int index=0;
                readFile();
                JSONArray savedPets = jsonObject.getJSONArray("saved_pets");
                for (int i=0 ; i < savedPets.length() ; i++){
                    if(savedPets.getInt(i) == pet_id){
                        index = i;
                    }
                }

                jsonObject.getJSONArray("saved_pets").remove(index);
                saveToFile(jsonObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<Integer> getAdoptedPetIdList(){
        readFile();
        ArrayList<Integer> resultList = new ArrayList<>();
        try {
            JSONArray idList = jsonObject.getJSONArray("saved_pets");
            if(idList!=null){
                for (int i=0; i<idList.length();i++){
                    resultList.add(idList.getInt(i));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public static boolean isIdExist(int petId){
        for (int x : getAdoptedPetIdList()){
            if(x == petId){
                return true;
            }
        }
        return false;
    }

    private static void readFile(){
        try{
            StringBuffer output = new StringBuffer();
            fileReader = new FileReader(file.getAbsolutePath());
            bufReader = new BufferedReader(fileReader);
            String line = "";
            while((line = bufReader.readLine())!= null){
                output.append(line + "\n");
            }
            String resultStr = output.toString();
            jsonObject = getJson(resultStr);
            bufReader.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private static void saveToFile(String str){
        try{
            fileWriter = new FileWriter(file.getAbsoluteFile());
            bufWriter = new BufferedWriter(fileWriter);
            bufWriter.write(str);
            bufWriter.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }

    }

    private static JSONObject getJson(String jsonString){
        JSONObject messageDetails = null;
        if(jsonString !=null){
            try {
                 messageDetails = new JSONObject(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return messageDetails;
    }

}
