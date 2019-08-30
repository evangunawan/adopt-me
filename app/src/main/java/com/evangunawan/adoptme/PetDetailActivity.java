package com.evangunawan.adoptme;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.evangunawan.adoptme.Model.Pet;

public class PetDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_detail);
        Intent i = getIntent();
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) actionBar.setDisplayHomeAsUpEnabled(true);


        TextView txt1 = (TextView) findViewById(R.id.txt1);
        Pet petReceived = (Pet) i.getSerializableExtra("PetObject");
        txt1.setText(petReceived.getPetTitle());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
