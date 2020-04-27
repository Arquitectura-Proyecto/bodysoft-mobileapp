package com.example.myapplication.ui.authentication.register;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

import com.example.myapplication.R;
import com.example.myapplication.ui.GlobalState;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class RegisterFragment extends Fragment {
    GlobalState globalState;
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        globalState= ViewModelProviders.of(getActivity()).get(GlobalState.class);

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
        MaterialButton corfirmButton = view.findViewById(R.id.confirm_button);

        /** Layout */
        LinearLayout registerLayout = view.findViewById(R.id.registerLayout);
        LinearLayout layoutCodigo  = view.findViewById(R.id.LayoutCodigo);

        /** Text input edit */


        TextInputEditText confirm_edit_text = view.findViewById(R.id.confirm_edit_text);
        TextInputEditText user_edit_text = view.findViewById(R.id.User_edit_text);
        TextInputEditText password_edit_text = view.findViewById(R.id.password_edit_text);


        /** Text input*/
        TextInputLayout confirm_text_input = view.findViewById(R.id.confirm_text_input);
        TextInputLayout user_text_input = view.findViewById(R.id.User_text_input);
        TextInputLayout password_text_input = view.findViewById(R.id.password_text_input);

        layoutCodigo.setVisibility(View.GONE);

        /** Coach register  */
        next_coach_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerLayout.setVisibility(view.GONE);
                layoutCodigo.setVisibility(View.VISIBLE);
            }
        });

        /** User  register  */
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerLayout.setVisibility(view.GONE);
                layoutCodigo.setVisibility(View.VISIBLE);
            }
        });

        corfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    NavOptions navOption = new NavOptions.Builder().setPopUpTo(R.id.login, true).build();
                    navController.navigate(R.id.go_to_main,null,navOption);

                     ((AppCompatActivity)getActivity()).getSupportActionBar().show();

            }
        });

    }
}
