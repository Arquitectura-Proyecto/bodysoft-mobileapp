package com.example.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.AuthValidateAuthTokenQuery;
import com.example.myapplication.Model.Models.AuthModel;
import com.example.myapplication.ui.AuthGlobalState;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivityUser extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private AuthGlobalState authGlobalState;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        //http://192.168.0.41:3800/graphql
        ApolloClient apolloClient=ApolloClient.builder().serverUrl("http://192.168.0.4:3800/graphql").build();

        createNotificationChannel();

        authGlobalState = ViewModelProviders.of(this).get(AuthGlobalState.class);
        Intent intent = getIntent();
        String token = intent.getStringExtra("token");
        int fragment = intent.getIntExtra("fragment",R.id.nav_home);

        AuthModel.authValidateAuthToken(token, new ApolloCall.Callback<AuthValidateAuthTokenQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<AuthValidateAuthTokenQuery.Data> response) {
                runOnUiThread(new Runnable() {
                    @Override public void run() {
                        if (response.hasErrors()) {

                        } else {
                            Integer typeId = response.data().authValidateAuthToken().TypeID();
                            Boolean profile = response.data().authValidateAuthToken().Profile();
                            authGlobalState.setTypeID(typeId);


                        }
                    }
                });
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });


        setContentView(R.layout.activity_main_user);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer;
        NavigationView navigationView;
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);



        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);  // Hostfragment
        NavInflater inflater = navHostFragment.getNavController().getNavInflater();
        NavGraph graph = inflater.inflate(R.navigation.mobile_navigation);
        graph.setStartDestination(fragment);

        navHostFragment.getNavController().setGraph(graph);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.listRoutineFragment,R.id.rootRoutineFragment, R.id.userRoutineListFragment, R.id.fragment_profile)/////los que esten aqui tendra tendran el boton para ver el navigation viewer
                .setDrawerLayout(drawer)
                .build();
        NavController navController = navHostFragment.getNavController();
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
