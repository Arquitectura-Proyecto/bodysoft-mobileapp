package com.example.myapplication.ui.session;


import android.os.Build;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class SessionFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment with the ProductGrid theme
        View view = inflater.inflate(R.layout.fragment_session, container, false);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.findViewById(R.id.product_grid).setBackgroundResource(R.drawable.shr_product_grid_background_shape);
        }

        // Set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
          List<Session> list = new ArrayList();

          /** Borrar */
          list.add(new Session("12-03-2020","12:00:00","13:00:00"));
          list.add(new Session("13-03-2020","12:00:00","13:00:00"));
          list.add(new Session("14-03-2020","12:00:00","13:00:00"));
          list.add(new Session("15-03-2020","12:00:00","13:00:00"));
          /*********/

        CurrentCardRecyclerViewAdapter adapter = new CurrentCardRecyclerViewAdapter(list);

        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small);
        recyclerView.addItemDecoration(new ProductGridItemDecoration(largePadding, smallPadding));

        return view;
    }

}
