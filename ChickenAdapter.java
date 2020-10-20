package com.example.favoriteorders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
//author: rolando
public class ChickenAdapter extends ArrayAdapter<Chicken> {

    private Context mContext;
    private List<Chicken> chickenList = new ArrayList<Chicken>();

    public ChickenAdapter( Context context, ArrayList<Chicken> list) {
        super ( context, 0 ,list) ;
        mContext = context;
        chickenList = list;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.chicken_view,parent,false);

        Chicken currentChicken = chickenList.get(position);


        TextView name = (TextView) listItem.findViewById(R.id.textView_Name);
        name.setText(currentChicken.getName());

        TextView size = (TextView) listItem.findViewById(R.id.textView_Size);
        size.setText(currentChicken.getSize());

        TextView spice = (TextView) listItem.findViewById(R.id.textView_Spice);
        spice.setText(currentChicken.getSpice());

        TextView sauce = (TextView) listItem.findViewById(R.id.textView_Sauce);
        sauce.setText(currentChicken.getSauce());

        return listItem;
    }
}
