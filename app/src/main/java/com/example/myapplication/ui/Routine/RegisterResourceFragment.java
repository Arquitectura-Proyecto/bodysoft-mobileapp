package com.example.myapplication.ui.Routine;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.apollographql.apollo.ApolloCall;
import com.example.apollographqlandroid.RegisterResourceMutation;
import com.example.myapplication.Model.Entities.ResourceEntity;
import com.example.myapplication.Model.Store.RoutineStore;
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
    private RoutineStore routineStore;
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
        this.routineStore= ViewModelProviders.of(getActivity()).get(RoutineStore.class);
        this.descriptionTextInput=(TextInputEditText)view.findViewById(R.id.txtInputDescriptionRegisterResource);
        this.TitleTextInput=(TextInputEditText)view.findViewById(R.id.txtInputNameRegisterResource);
        this.positionTextInput=(TextInputEditText)view.findViewById(R.id.txtInputPositionRegisterResource);
        this.linkTextInpunt=(TextInputEditText)view.findViewById(R.id.txtInputLinkPreviewRegisterResource);
        this.btnRegiserResource=(Button)view.findViewById(R.id.btnRoutineResource);



    }
private class btnRegisterResourceListener implements View.OnClickListener{
    private  int DEFAULT_TYPE_RESOURCE=1;
    @Override
    public void onClick(View v) {
        ResourceEntity newResource=new ResourceEntity();
        try{
            newResource.setIdRoutine(Integer.parseInt(routineStore.getInformationRoutine().getValue().getId()));
            newResource.setIdType(DEFAULT_TYPE_RESOURCE);
            newResource.setLink(linkTextInpunt.getText().toString());
            newResource.setPosition(Integer.parseInt(positionTextInput.getText().toString().trim()));
            newResource.setTitle(TitleTextInput.getText().toString());
            newResource.setDescription(descriptionTextInput.getText().toString());
        }catch (Exception e){
            Toast.makeText(v.getContext(),
                    e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}
}
