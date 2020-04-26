package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;

import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import com.example.apollographqlandroid.CrearRutinaMutation;
import com.example.apollographqlandroid.GetRoutinesQuery;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApolloClient apolloClient=ApolloClient.builder().serverUrl("http://192.168.0.12:3000/graphql").build();




        apolloClient.mutate(CrearRutinaMutation.builder().price(200.3).name("nombre de android").description("desde el celular").link_preview("www.google.com").idType(2).token("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJJRCI6MiwiUHJvZmlsZSI6ZmFsc2UsIlR5cGVJRCI6MSwiZXhwIjoxNTg3OTI5ODczfQ.rX3r7JAEaWYfs1qc4-sS-_DQoV7WOuI12mw0vYTHp_Y").build()).enqueue(
                new ApolloCall.Callback<CrearRutinaMutation.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<CrearRutinaMutation.Data> response) {
                        System.out.println("agregado:"+response.data());
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        System.out.println( e);
                    }
                }
        );
        ////
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.listRoutineFragment)/////los que esten aqui tendra tendran el boton para ver el navigation viewer
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
