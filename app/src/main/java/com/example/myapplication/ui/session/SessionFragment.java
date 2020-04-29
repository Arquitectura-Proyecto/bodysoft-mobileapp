package com.example.myapplication.ui.session;


import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.GetCurrentbyIdQuery;
import com.example.myapplication.Model.Entities.SessionEntity;
import com.example.myapplication.Model.Models.SessionModel;
import com.example.myapplication.Model.Store.AuthGlobalState;
import com.example.myapplication.Model.Store.SessionStore;
import com.example.myapplication.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SessionFragment extends Fragment {

    SessionStore sessionStore;
    AuthGlobalState globalState;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment with the ProductGrid theme
        View view = inflater.inflate(R.layout.fragment_session, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.sessionStore= ViewModelProviders.of(getActivity()).get(SessionStore.class);
        this.globalState= ViewModelProviders.of(getActivity()).get(AuthGlobalState.class);

        String token = globalState.getSharedString().getValue();
        System.out.println("hola    +++++++"+token);

        SessionModel.getCurrentbyId(token,new ApolloCall.Callback<GetCurrentbyIdQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetCurrentbyIdQuery.Data> response) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override public void run() {
                        if (response.hasErrors()) {
                            String error = response.errors().get(0).message().toString().substring(3);

                        } else {
                            sessionStore.setSession(response.data().schedule());

                            RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
                            List<SessionEntity> list = new ArrayList();

                            /** Borrar */

                            list.add(new SessionEntity("12-03-2020","12:00:00","13:00:00"));
                            list.add(new SessionEntity("13-03-2020","12:00:00","13:00:00"));
                            list.add(new SessionEntity("14-03-2020","12:00:00","13:00:00"));
                            list.add(new SessionEntity("15-03-2020","12:00:00","13:00:00"));
                            /*********/

                            CurrentCardRecyclerViewAdapter adapter = new CurrentCardRecyclerViewAdapter(list);

                            recyclerView.setAdapter(adapter);
                            int largePadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing);
                            int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small);
                            recyclerView.addItemDecoration(new ProductGridItemDecoration(largePadding, smallPadding));
                         }
                    }
                });
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });


        // Set up the RecyclerView


    }

}
