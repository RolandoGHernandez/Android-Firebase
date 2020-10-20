package com.example.favoriteorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//author: beatrice
public class UpdateBobaOrder extends AppCompatActivity {

    private DatabaseReference myRefBoba;
    private String name;
    private String tea;
    private String milk;
    private String boba;
    private String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_boba_order);
        myRefBoba = FirebaseDatabase.getInstance().getReference().child("boba");

        Intent intent = getIntent();
        Bundle bobaB = intent.getExtras();
        name = bobaB.getString("name");
        tea = bobaB.getString("tea");
        milk = bobaB.getString("milk");
        boba = bobaB.getString("boba");
        key = bobaB.getString("key");

        TextView textName = (TextView)findViewById(R.id.textViewInstructions);
        textName.setText(textName.getText().toString() + " " + name);
    }

    public void update(View view)
    {
        /// Add Boba Order
        EditText editName = findViewById(R.id.nameTextButton);
        String new_name = editName.getText().toString();

        // Parse radio button group choices
        RadioGroup teaRG = findViewById(R.id.teaOptionGroup);
        int teaID = teaRG.getCheckedRadioButtonId();
        RadioButton teaRB = findViewById(teaID);
        String new_tea = teaRB.getText().toString();

        RadioGroup milkRG = findViewById(R.id.milkOptionGroup);
        int milkID = milkRG.getCheckedRadioButtonId();
        RadioButton milkRB = findViewById(milkID);
        String new_milk = milkRB.getText().toString();

        RadioGroup bobaRG = findViewById(R.id.bobaOptionGroup);
        int bobaID = bobaRG.getCheckedRadioButtonId();
        RadioButton bobaRB = findViewById(bobaID);
        String new_boba = bobaRB.getText().toString();

        Boba newb = new Boba(new_name, new_tea, new_milk, new_boba, key);
        myRefBoba.child(key).setValue(newb);

        Toast.makeText(this, "Updated.", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, updateOrderActivity.class);
        startActivity(intent);

    }

}
