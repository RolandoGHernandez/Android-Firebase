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
public class BobaAdapter extends ArrayAdapter<Boba> {

    private Context mContext;
    private List<Boba> bobaList = new ArrayList<Boba>();

    public BobaAdapter( Context context, ArrayList<Boba> list) {
        super ( context, 0 ,list) ;
        mContext = context;
        bobaList = list;
    }
    //author: beatrice
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.boba_view,parent,false);

        Boba currentBoba = bobaList.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.textView_Name);
        name.setText(currentBoba.getName());

        TextView tea = (TextView) listItem.findViewById(R.id.textView_Tea);
        tea.setText(currentBoba.getTea());

        TextView milk = (TextView) listItem.findViewById(R.id.textView_Milk);
        milk.setText(currentBoba.getMilk());

        TextView boba = (TextView) listItem.findViewById(R.id.textView_Boba);
        boba.setText(currentBoba.getBoba());

        return listItem;
    }
}
