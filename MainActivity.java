package com.example.favoriteorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void addOrder (View view) {
        Intent intent = new Intent (this, AddOrderActivity.class);
        Toast.makeText(this, " switching to new page.", Toast.LENGTH_LONG).show();

        startActivity(intent);
    }

    public void removeOrder (View view) {
        Intent intent = new Intent (this, RemoveActivity.class);
        Toast.makeText(this, " switching to remove page.", Toast.LENGTH_LONG).show();

        startActivity(intent);
    }
    public void viewAll (View view) {

        Intent intent = new Intent (this, ViewAllActivity.class);
        startActivity(intent);
    }

    public void updateOrder(View view)
    {
        Intent intent = new Intent(this, updateOrderActivity.class);
        startActivity(intent);
    }

    public void search(View view)
    {
        Intent intent = new Intent (this, SearchActivity.class);
        startActivity(intent);
    }


}
