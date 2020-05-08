package com.example.myapplication.ui.authentication.register;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.AuthCreateUserMutation;
import com.example.apollographqlandroid.AuthVerifyAcountMutation;
import com.example.myapplication.MainActivity;
import com.example.myapplication.Model.Models.AuthModel;
import com.example.myapplication.R;
import com.example.myapplication.ui.AuthGlobalState;
import com.example.myapplication.ui.GlobalState;
import com.example.myapplication.ui.NotificationId;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;


public class RegisterFragment extends Fragment {
    private Integer typeID;
    AuthGlobalState globalState;
    NotificationId notificationId;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        globalState= ViewModelProviders.of(getActivity()).get(AuthGlobalState.class);

        notificationId= ViewModelProviders.of(getActivity()).get(NotificationId.class);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        return view;
    }

   /*
        In reality, this will have more complex logic including, but not limited to, actual
        authentication of the username and password.
     */


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController= Navigation.findNavController(view);

        /** Botones */
        MaterialButton nextButton = view.findViewById(R.id.next_button);
        MaterialButton next_coach_button = view.findViewById(R.id.next_coach_button);
        MaterialButton tengocodigo = view.findViewById(R.id.tengocodigo);
        MaterialButton corfirmButton = view.findViewById(R.id.confirm_button);
        MaterialButton corfirmButton2 = view.findViewById(R.id.confirm2_button);

        /** Layout */
        LinearLayout registerLayout = view.findViewById(R.id.registerLayout);
        LinearLayout layoutCodigo  = view.findViewById(R.id.LayoutCodigo);
        LinearLayout layoutCodigo2  = view.findViewById(R.id.LayoutCodigo2);

        /** Text input edit */


        TextInputEditText confirm_edit_text = view.findViewById(R.id.confirm_edit_text);
        TextInputEditText user_edit_text = view.findViewById(R.id.User_edit_text);
        TextInputEditText confirm_edit_text2 = view.findViewById(R.id.confirm2_edit_text);
        TextInputEditText user_edit_text2 = view.findViewById(R.id.email_edit_text);
        TextInputEditText password_edit_text = view.findViewById(R.id.password_edit_text);


        /** Text input*/
        TextInputLayout confirm_text_input = view.findViewById(R.id.confirm_text_input);
        TextInputLayout user_text_input = view.findViewById(R.id.User_text_input);
        TextInputLayout confirm_text_input2 = view.findViewById(R.id.confirm2_text_input);
        TextInputLayout user_text_input2 = view.findViewById(R.id.email_text_input);
        TextInputLayout password_text_input = view.findViewById(R.id.password_text_input);

        Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity().getApplicationContext(), 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity().getApplicationContext(), "Notification")
                .setSmallIcon(R.mipmap.logo)
                .setContentTitle("Registro exitoso")
                .setContentText("Su codigo de verificación ha sido validado correctamente")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Su codigo de verificación ha sido validado correctamente"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity().getApplicationContext());

        layoutCodigo.setVisibility(View.GONE);
        layoutCodigo2.setVisibility(View.GONE);

        /** Coach register  */
        next_coach_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = user_edit_text.getText().toString().trim();
                String password = password_edit_text.getText().toString();
                typeID = 1;
                AuthModel.authCreateUser(email,password,typeID,new ApolloCall.Callback<AuthCreateUserMutation.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<AuthCreateUserMutation.Data> response) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override public void run() {
                                if(response.hasErrors()){
                                    String error = response.errors().get(0).message().toString().substring(3);
                                    user_text_input.setError(error);
                                    password_text_input.setError(" ");
                                    tengocodigo.setVisibility(View.VISIBLE);
                                }else{
                                    registerLayout.setVisibility(view.GONE);
                                    tengocodigo.setVisibility(View.GONE);
                                    layoutCodigo.setVisibility(View.VISIBLE);

                                }

                            }
                        });
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override public void run() {
                                System.out.println(e.getMessage());
                            }
                        });
                    }
                });

            }
        });

        /** Tengo codigo**/
        tengocodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerLayout.setVisibility(view.GONE);
                tengocodigo.setVisibility(View.GONE);
                layoutCodigo2.setVisibility(View.VISIBLE);


            }
        });

        /** User  register  */
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = user_edit_text.getText().toString().trim();
                String password = password_edit_text.getText().toString();
                typeID = 2;
                AuthModel.authCreateUser(email,password,typeID,new ApolloCall.Callback<AuthCreateUserMutation.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<AuthCreateUserMutation.Data> response) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override public void run() {
                                if(response.hasErrors()){
                                    String error = response.errors().get(0).message().toString().substring(3);
                                    user_text_input.setError(error);
                                    password_text_input.setError(" ");
                                    tengocodigo.setVisibility(View.VISIBLE);
                                }else{
                                    registerLayout.setVisibility(view.GONE);
                                    tengocodigo.setVisibility(View.GONE);
                                    layoutCodigo.setVisibility(View.VISIBLE);
                                    // notificationId is a unique int for each notification that you must define

                                }

                            }
                        });
                    }
                    //authVerifyAcount
                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override public void run() {
                                user_text_input.setError("");
                                password_text_input.setError("");
                            }
                        });
                    }
                });

            }

        });

        corfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = user_edit_text.getText().toString().trim();
                int vcode = Integer.parseInt(confirm_edit_text.getText().toString());
                AuthModel.authVerifyAcount(email,vcode,new ApolloCall.Callback<AuthVerifyAcountMutation.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<AuthVerifyAcountMutation.Data> response) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override public void run() {
                                if(response.hasErrors()){
                                    String error = response.errors().get(0).message().toString().substring(3);
                                    confirm_text_input.setError(error);
                                }else{

                                    NavOptions navOption = new NavOptions.Builder().setPopUpTo(R.id.login, true).build();

                                    navController.navigate(R.id.go_to_login,null,navOption);


                                    int id = notificationId.getID().getValue();
                                    // notificationId is a unique int for each notification that you must define
                                    notificationManager.notify(id, builder.build());

                                    notificationId.setID(id+1);



                                }

                            }
                        });
                    }
                    //authVerifyAcount
                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override public void run() {
                                user_text_input.setError("");
                                password_text_input.setError("");
                            }
                        });
                    }
                });



            }
        });
        corfirmButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = user_edit_text2.getText().toString().trim();
                int vcode = Integer.parseInt(confirm_edit_text2.getText().toString());
                AuthModel.authVerifyAcount(email,vcode,new ApolloCall.Callback<AuthVerifyAcountMutation.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<AuthVerifyAcountMutation.Data> response) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override public void run() {
                                if(response.hasErrors()){
                                    String error = response.errors().get(0).message().toString().substring(3);
                                    confirm_text_input2.setError(error);
                                }else{

                                    NavOptions navOption = new NavOptions.Builder().setPopUpTo(R.id.login, true).build();

                                    navController.navigate(R.id.go_to_login,null,navOption);
                                    // notificationId is a unique int for each notification that you must define
                                    notificationManager.notify(1, builder.build());



                                }

                            }
                        });
                    }
                    //authVerifyAcount
                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override public void run() {
                            }
                        });
                    }
                });



            }
        });

    }


}
