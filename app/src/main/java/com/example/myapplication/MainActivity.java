package com.example.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;

import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import com.example.apollographqlandroid.CrearRutinaMutation;
import com.example.apollographqlandroid.GetRoutinesQuery;

import com.example.myapplication.ui.AuthGlobalState;
import com.example.myapplication.ui.NotificationId;


import com.example.myapplication.Model.Models.ProfileModel;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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
    private AuthGlobalState authGlobalState;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        //http://192.168.0.41:3800/graphql
        ApolloClient apolloClient=ApolloClient.builder().serverUrl("http://192.168.0.5:3800/graphql").build();

        createNotificationChannel();

        authGlobalState = ViewModelProviders.of(this).get(AuthGlobalState.class);



/*Ejemplo mutacion
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
        );*/
        ////

        setContentView(R.layout.activity_main);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer;
        NavigationView navigationView;
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);




        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.listRoutineFragment,R.id.rootRoutineFragment, R.id.userRoutineListFragment)/////los que esten aqui tendra tendran el boton para ver el navigation viewer
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

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Notification", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
