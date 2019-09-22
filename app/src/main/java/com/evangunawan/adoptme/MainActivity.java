package com.evangunawan.adoptme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.evangunawan.adoptme.Home.AdoptedPetFragment;
import com.evangunawan.adoptme.Home.AdoptionFragment;
import com.evangunawan.adoptme.Util.FileHandler;
import com.evangunawan.adoptme.Util.PetLoader;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView botNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botNavView = findViewById(R.id.btm_nav_main);
        loadFragment(new AdoptionFragment());
        this.setTitle("Available Adoptions");
        PetLoader.loadPets(this);

        FileHandler fileHandler = new FileHandler(this);
        ArrayList<Integer> adoptedPets = fileHandler.getAdoptedPetIdList();
        Log.i("FileHandler", "Adopted Pets: " + adoptedPets.size() );

//        Log.i("Activity","Hello");
        botNavView.setOnNavigationItemSelectedListener(menuItem -> {
            Fragment fragment = null;
            switch (menuItem.getItemId()){
                case R.id.home_menu:
                    fragment = new AdoptionFragment();
                    break;
                case R.id.adopted_pet_menu:
                    fragment = new AdoptedPetFragment();
                    break;
            }
            return loadFragment(fragment);
        });

    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_about){
            Log.i("PetApp","About me clicked");
            Intent i = new Intent(getApplicationContext(), AboutActivity.class);
            startActivity(i);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
