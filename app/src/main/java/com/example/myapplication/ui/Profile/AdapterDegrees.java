package com.example.myapplication.ui.Profile;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class AdapterDegrees extends BaseAdapter {

    private Context context;
    private ArrayList<EntityDegree> listDegrees;

    public AdapterDegrees(Context context, ArrayList<EntityDegree> listDegrees) {
        this.context = context;
        this.listDegrees = listDegrees;
    }


    @Override
    public int getCount() {
        return listDegrees.size();
    }

    @Override
    public Object getItem(int position) {
        return listDegrees.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EntityDegree item = (EntityDegree) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item_degree,null);
        ImageView degreePhoto = convertView.findViewById(R.id.itemDegreePhoto);
        TextView degreeTitle = convertView.findViewById(R.id.titleDegree);
        TextView degreeInstitution = convertView.findViewById(R.id.institutionDegree);
        TextView degreeYear = convertView.findViewById(R.id.yearDegree);

        degreePhoto.setImageResource(item.getDegree_image());
        degreeTitle.setText(item.getDegree_title());
        degreeInstitution.setText(item.getInstitution());
        degreeYear.setText(item.getYear());

        return convertView;
    }
}
