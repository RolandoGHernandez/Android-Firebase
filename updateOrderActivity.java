package com.example.favoriteorders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

//author: beatrice
public class updateOrderActivity extends AppCompatActivity {

    // Boba variables
    private DatabaseReference myRefBoba;
    private ChildEventListener childEventListenerBoba;

    private ArrayList<Boba> bobaList;
    private ArrayList<Boba> bobaSearchResults;

    private BobaAdapter bobaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_order);

        myRefBoba = FirebaseDatabase.getInstance().getReference().child("boba");
        bobaList = new ArrayList<Boba>();
        bobaSearchResults = new ArrayList<Boba>();

        childEventListenerBoba = new ChildEventListener()
        {

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

        myRefBoba.addChildEventListener(childEventListenerBoba);
        bobaAdapter = new BobaAdapter(this, bobaSearchResults);
        ListView bobaResults = findViewById(R.id.listViewResultsBoba);
        bobaResults.setAdapter(bobaAdapter);

        bobaResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Boba selectedBoba = (Boba)parent.getItemAtPosition(position);
                Bundle bobaB = new Bundle();
                bobaB.putString("name", selectedBoba.getName());
                bobaB.putString("tea", selectedBoba.getTea());
                bobaB.putString("milk", selectedBoba.getMilk());
                bobaB.putString("boba", selectedBoba.getBoba());
                bobaB.putString("key", selectedBoba.getKey());
                Intent intentBoba = new Intent(view.getContext(), UpdateBobaOrder.class);
                intentBoba.putExtras(bobaB);
                startActivity(intentBoba);
            }
        });


    }

    public void search(View view)
    {
        bobaAdapter.clear();

        boolean found = false;

        for(Boba b : bobaList)
        {
            EditText text = (EditText)findViewById(R.id.editTextName);
            String search = text.getText().toString();

            if(b.getName().equalsIgnoreCase(search))
            {
                bobaAdapter.add(b);
                found = true;
            }
        }

        EditText search = (EditText) findViewById(R.id.editTextName);
        if(!found)
        {
            Toast.makeText(this, search.getText().toString() + "not found.", Toast.LENGTH_LONG).show();
        }

        search.setText("");
    }
}
