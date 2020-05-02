package com.example.myapplication.Model.Repositories;

import android.widget.Toast;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.ProfileUsersQuery;
import com.example.apollographqlandroid.ProfileUserQuery;
import com.example.apollographqlandroid.ProfileTrainerQuery;
import com.example.apollographqlandroid.EditProfileUserMutation;
import com.example.apollographqlandroid.EditProfileTrainerMutation;
import com.example.myapplication.Model.Models.ModelListener;
import com.example.myapplication.Model.Repositories.Data.Client;
import com.example.myapplication.ui.GlobalState;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ProfileTrainerRepository {
    public static ApolloClient apolloClient= Client.getApolloClient();

    public ProfileTrainerRepository(){

    }
    public static void getProfileUsers(ApolloCall.Callback<ProfileUsersQuery.Data>listener){
        apolloClient.query(ProfileUsersQuery.builder().build()).enqueue(new ApolloCall.Callback<ProfileUsersQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<ProfileUsersQuery.Data> response) {
                System.out.println("los datos son "+response.data().toString());
                List<ProfileUsersQuery.ProfileUser> array= response.data().profileUsers();
                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                System.out.println("el error es "+e.getMessage());

            }
        });
    }

    public static void getProfileTrainer(ApolloCall.Callback<ProfileTrainerQuery.Data>listener, String token){
        apolloClient.query(ProfileTrainerQuery.builder().token(token).build()).enqueue(new ApolloCall.Callback<ProfileTrainerQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<ProfileTrainerQuery.Data> response) {
                ProfileTrainerQuery.ProfileTrainer user= response.data().profileTrainer();
                System.out.println("repo" + user);
                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                System.out.println("el error es "+e.getMessage());
            }
        });
    }

    public static void editProfileTrainer(ApolloCall.Callback<EditProfileTrainerMutation.Data>listener, String trainer_name, int age, String photo, String telephone, String city, int sum_ratings, int num_ratings, String description, String resources, String work_experience, String token){
        apolloClient.mutate(EditProfileTrainerMutation.builder().trainer_name(trainer_name).age(age).photo(photo).telephone(telephone).city(city).sum_ratings(sum_ratings).num_ratings(num_ratings).description(description).resources(resources).work_experience(work_experience).token(token).build()).enqueue(new ApolloCall.Callback<EditProfileTrainerMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<EditProfileTrainerMutation.Data> response) {
                EditProfileTrainerMutation.UpdateProfileTrainer trainer= response.data().updateProfileTrainer();
                System.out.println("repo" + trainer);
                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                System.out.println("el error es "+e.getMessage());
            }
        });
    }






}