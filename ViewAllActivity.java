package com.example.favoriteorders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class ViewAllActivity extends AppCompatActivity {

    private DatabaseReference myRefBoba;
    private ChildEventListener childEventListenerBoba;

    private ArrayList<Boba> bobaList;

    private ArrayList<Boba> bobaSearchResults;

    private BobaAdapter bobaAdapter;

    private DatabaseReference myRefChicken;
    private ChildEventListener childEventListenerChicken;

    private ArrayList<Chicken> chickenList;

    private ArrayList<Chicken> chickenSearchResults;

    private ChickenAdapter chickenAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        myRefBoba = FirebaseDatabase.getInstance().getReference().child("boba");
        myRefChicken = FirebaseDatabase.getInstance().getReference().child("chicken");

        bobaList = new ArrayList<Boba>();
        bobaSearchResults = new ArrayList<Boba>();

        chickenList = new ArrayList<Chicken>();
        chickenSearchResults = new ArrayList<Chicken>();


        childEventListenerBoba = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                bobaAdapter.add(dataSnapshot.getValue(Boba.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        childEventListenerChicken = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                chickenAdapter.add(dataSnapshot.getValue(Chicken.class));

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        myRefBoba.addChildEventListener(childEventListenerBoba);
        bobaAdapter = new BobaAdapter(this, bobaSearchResults);

        myRefChicken.addChildEventListener(childEventListenerChicken);
        chickenAdapter = new ChickenAdapter(this, chickenSearchResults);

        ListView bobaResults = findViewById(R.id.listViewAllResultsBoba);
        bobaResults.setAdapter(bobaAdapter);

        ListView chickenResults = findViewById(R.id.listViewAllResultsChicken);
        chickenResults.setAdapter(chickenAdapter);


    }
}

