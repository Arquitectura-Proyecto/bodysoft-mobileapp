package com.example.myapplication.ui.authentication.changePassword;

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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.AuthChagePasswordMutation;
import com.example.apollographqlandroid.AuthRecoverPasswordQuery;
import com.example.myapplication.MainActivity;
import com.example.myapplication.Model.Models.AuthModel;
import com.example.myapplication.R;
import com.example.myapplication.ui.AuthGlobalState;
import com.example.myapplication.ui.NotificationId;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class changePasswordFragment extends Fragment {

    AuthGlobalState globalState;
    NotificationId notificationId;

    public changePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_change_password, container, false);
        globalState= ViewModelProviders.of(getActivity()).get(AuthGlobalState.class);
        notificationId= ViewModelProviders.of(getActivity()).get(NotificationId.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        final Bundle prop = new Bundle();


        MaterialButton ChangeButton = view.findViewById(R.id.next_button);


        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);

        final TextInputLayout new_passwordTextInput = view.findViewById(R.id.new_password_text_input);
        final TextInputEditText new_passwordEditText = view.findViewById(R.id.new_password_edit_text);

        Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity().getApplicationContext(), 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity().getApplicationContext(), "Notification")
                .setSmallIcon(R.mipmap.logo)
                .setContentTitle("Cambio exitoso")
                .setContentText("Su contrasña se ha actulizado exitosamente")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Su contrasña se ha actulizado exitosamente"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity().getApplicationContext());


        ChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String token = globalState.getToken().getValue();
                String password = passwordEditText.getText().toString();
                String new_password = new_passwordEditText.getText().toString();
                //authChagePassword

                AuthModel.authChagePassword(token,password, new_password, new ApolloCall.Callback<AuthChagePasswordMutation.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<AuthChagePasswordMutation.Data> response) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (response.hasErrors()) {
                                    String error = response.errors().get(0).message().toString().substring(3);
                                    passwordTextInput.setError(error);
                                    new_passwordTextInput.setError(" ");
                                } else {

                                    int id = notificationId.getID().getValue();
                                    // notificationId is a unique int for each notification that you must define
                                    notificationManager.notify(id, builder.build());
                                    notificationId.setID(id+1);
                                    NavOptions navOption = new NavOptions.Builder().setPopUpTo(R.id.login, true).build();
                                    navController.navigate(R.id.go_to_main,null,navOption);


                                }
                            }
                        });
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {

                    }
                });
            }

        });

    }

}


