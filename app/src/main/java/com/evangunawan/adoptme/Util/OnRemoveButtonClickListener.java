package com.evangunawan.adoptme.Util;

import com.evangunawan.adoptme.Controller.AdoptedPetAdapter;
import com.evangunawan.adoptme.Model.Pet;

import java.util.ArrayList;

public interface OnRemoveButtonClickListener{
    void onRemoveButtonClick(Pet petItem, int position);
}