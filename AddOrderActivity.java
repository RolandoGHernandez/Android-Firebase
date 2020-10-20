package com.example.favoriteorders;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//author: beatrice
public class AddOrderActivity extends AppCompatActivity {

    private DatabaseReference myRefBoba;
    private DatabaseReference myRefChicken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        myRefBoba = FirebaseDatabase.getInstance().getReference().child("boba");
        myRefChicken = FirebaseDatabase.getInstance().getReference().child("chicken");

    }
    //author: rolando
    public void addOrder (View view) {

        /// Add Boba Order
        EditText editName = findViewById(R.id.nameTextButton);
        String name = editName.getText().toString();
        // Parse radio button group choices
        RadioGroup teaRG = findViewById(R.id.teaOptionGroup);
        int teaID = teaRG.getCheckedRadioButtonId();
        RadioButton teaRB = findViewById(teaID);
        String tea = teaRB.getText().toString();

        RadioGroup milkRG = findViewById(R.id.milkOptionGroup);
        int milkID = milkRG.getCheckedRadioButtonId();
        RadioButton milkRB = findViewById(milkID);
        String milk = milkRB.getText().toString();

        RadioGroup bobaRG = findViewById(R.id.bobaOptionGroup);
        int bobaID = bobaRG.getCheckedRadioButtonId();
        RadioButton bobaRB = findViewById(bobaID);
        String boba = bobaRB.getText().toString();

        // Add to database
        String bobaKey = myRefBoba.push().getKey();
        Boba b = new Boba(name, tea, milk, boba, bobaKey);
        myRefBoba.child(bobaKey).setValue(b);

        /// Add Chicken Order
       RadioGroup sizeRG = findViewById(R.id.sizeOptionGroup);
        int sizeID = sizeRG.getCheckedRadioButtonId();
        RadioButton sizeRB = findViewById(sizeID);
        String size = sizeRB.getText().toString();

        // Parse radio group choices
        RadioGroup spiceRG = findViewById(R.id.spiceOptionGroup);
        int spiceID = spiceRG.getCheckedRadioButtonId();
        RadioButton spiceRB = findViewById(spiceID);
        String spice = spiceRB.getText().toString();

        RadioGroup sauceRG = findViewById(R.id.sauceOptionGroup);
        int sauceID = sauceRG.getCheckedRadioButtonId();
        RadioButton sauceRB = findViewById(sauceID);
        String sauce = sauceRB.getText().toString();

        // Add to database
        String chickenKey = myRefChicken.push().getKey();
        Chicken c = new Chicken(name, size, spice, sauce, chickenKey);
        myRefChicken.child(chickenKey).setValue(c);

       // You can do it!:0 - toniekungpao

        //Reset values to 0 or empty state
        teaRG.clearCheck();
        milkRG.clearCheck();
        bobaRG.clearCheck();
        sizeRG.clearCheck();
        spiceRG.clearCheck();
        sauceRG.clearCheck();
        editName.setText("");


    }

}
