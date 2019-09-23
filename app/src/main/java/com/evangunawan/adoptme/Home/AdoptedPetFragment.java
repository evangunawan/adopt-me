package com.evangunawan.adoptme.Home;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.evangunawan.adoptme.Controller.AdoptedPetAdapter;
import com.evangunawan.adoptme.Model.Pet;
import com.evangunawan.adoptme.R;
import com.evangunawan.adoptme.Util.FileHandler;
import com.evangunawan.adoptme.Util.OnRemoveButtonClickListener;

import java.util.ArrayList;

public class AdoptedPetFragment extends Fragment implements OnRemoveButtonClickListener {

    private AdoptedPetAdapter aPetAdapter;
    private RecyclerView aPetRecyclerView;
    private ArrayList<Integer> adoptedPetIds;
    private TextView txtNone;

    public AdoptedPetFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_adopted_pet, container, false);
        adoptedPetIds = FileHandler.getAdoptedPetIdList();
        aPetAdapter = new AdoptedPetAdapter(adoptedPetIds);
        aPetAdapter.registerRemoveButtonClickListener(this);

        txtNone = v.findViewById(R.id.txtNone);
        aPetRecyclerView = v.findViewById(R.id.adoptedRecView);

        aPetRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false));
        aPetRecyclerView.setHasFixedSize(true);
        aPetRecyclerView.setAdapter(aPetAdapter);
        aPetAdapter.notifyDataSetChanged();

        if(adoptedPetIds.size()==0) txtNone.setVisibility(View.VISIBLE);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
//        refreshAdapter();
    }

    @Override
    public void onRemoveButtonClick(Pet petItem, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(petItem.getPetTitle()).setMessage("Are you sure to delete this pet?")
                .setPositiveButton("OK", (dialog, which) -> {
                    FileHandler.removeAdoptedPet(petItem.getPetId());
                    aPetAdapter.notifyItemRemoved(position);
                    aPetAdapter.notifyDataSetChanged();
                    //Refresh the fragment
                    getActivity().getSupportFragmentManager().beginTransaction().detach(this).attach(this).commit();
                    Toast.makeText(getActivity(), "Pet " + petItem.getPetTitle() + " deleted.",Toast.LENGTH_SHORT).show();
                    Log.i("RemoveEvent","Pet id: " + petItem.getPetId() + " removed.");
                })
                .setNegativeButton("CANCEL", (dialog, which) -> {})
                .create().show();


    }
}
