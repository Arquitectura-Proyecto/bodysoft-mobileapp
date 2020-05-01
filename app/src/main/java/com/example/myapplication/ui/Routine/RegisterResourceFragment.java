package com.example.myapplication.ui.Routine;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterResourceFragment extends Fragment {
    private TextInputEditText TitleTextInput;
    private TextInputEditText linkTextInpunt;
    private TextInputEditText positionTextInput;
    private TextInputEditText descriptionTextInput;
    private Button btnRegiserResource;
    public RegisterResourceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_resource, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.descriptionTextInput=(TextInputEditText)view.findViewById(R.id.txtInputDescriptionRegisterResource);
        this.TitleTextInput=(TextInputEditText)view.findViewById(R.id.txtInputNameRegisterResource);
        this.positionTextInput=(TextInputEditText)view.findViewById(R.id.txtInputPositionRegisterResource);
        this.linkTextInpunt=(TextInputEditText)view.findViewById(R.id.txtInputLinkPreviewRegisterResource);
        this.btnRegiserResource=(Button)view.findViewById(R.id.btnRoutineResource);



    }

}
