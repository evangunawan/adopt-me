package com.evangunawan.adoptme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.evangunawan.adoptme.Home.AdoptionFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView botNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botNavView = findViewById(R.id.btm_nav_main);
        loadFragment(new AdoptionFragment());
        this.setTitle("Available Adoptions");

        botNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()){
                    case R.id.home_menu:
                        fragment = new AdoptionFragment();
                        break;
//                    case R.id.account_menu:
//                        fragment = new AccountFragment();
//                        break;
                }
                return loadFragment(fragment);
            }
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

}
