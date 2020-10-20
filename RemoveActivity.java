package com.example.favoriteorders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

//author: alyssa
public class RemoveActivity extends AppCompatActivity {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);
        myRefBoba = FirebaseDatabase.getInstance().getReference().child("boba");
        myRefChicken = FirebaseDatabase.getInstance().getReference().child("chicken");

        bobaList = new ArrayList<Boba>();
        bobaSearchResults = new ArrayList<Boba>();

        chickenList = new ArrayList<Chicken> ();
        chickenSearchResults = new ArrayList<Chicken> ();


        childEventListenerBoba = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                bobaList.add(dataSnapshot.getValue(Boba.class));
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
                chickenList.add(dataSnapshot.getValue(Chicken.class));

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

        ListView results = findViewById(R.id.listViewResultsBoba);
        results.setAdapter(bobaAdapter);

        ListView chickenResults = findViewById(R.id.listViewResultsChicken);
        chickenResults.setAdapter(chickenAdapter);

        results.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Boba selectedItem = (Boba) parent.getItemAtPosition(position);

                myRefBoba.child(selectedItem.getKey()).removeValue();

                bobaList.remove(selectedItem);

                refreshBoba(selectedItem.getName());

            }
        });

        chickenResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Chicken selectedItem = (Chicken) parent.getItemAtPosition(position);

                myRefChicken.child(selectedItem.getKey()).removeValue();

                chickenList.remove(selectedItem);

                refreshChicken(selectedItem.getName());

            }
        });
    }

    public void refreshBoba (String update) {
        bobaAdapter.clear();
        for (Boba b : bobaList) {
            if (b.getName().equalsIgnoreCase(update)) {
                bobaAdapter.add(b);
            }
        }
    }

    public void refreshChicken (String update) {
        chickenAdapter.clear();
        for (Chicken c : chickenList) {
            if (c.getName().equalsIgnoreCase(update)) {
                chickenAdapter.add(c);
            }
        }
    }


    public void removeRecord(View view) {
        bobaAdapter.clear();
        chickenAdapter.clear();
        boolean found = false;

        EditText text = (EditText) findViewById(R.id.editTextName);
        String search = text.getText().toString();
        int bobacount = 0;
        int chickencount = 0;
        for (Boba b : bobaList) {
            if (b.getName().equalsIgnoreCase(search)) {
               bobaAdapter.add(b);
                found = true;
                bobacount += 1;
            }
        }
        for (Chicken c : chickenList) {
            if (c.getName().equalsIgnoreCase(search)) {
                chickenAdapter.add(c);
                found = true;
                chickencount += 1;
            }
        }

        if (!found) {
            Toast.makeText(this, text.getText().toString() + " not found.", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, String.valueOf(bobacount) + " | " + String.valueOf(chickencount), Toast.LENGTH_LONG).show();
        }
        text.setText("");
    }
}
