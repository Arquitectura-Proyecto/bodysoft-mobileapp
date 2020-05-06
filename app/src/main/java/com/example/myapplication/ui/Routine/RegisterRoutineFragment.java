package com.example.myapplication.ui.Routine;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.internal.ApolloLogger;
import com.example.apollographqlandroid.CrearRutinaMutation;
import com.example.apollographqlandroid.GetAllTypeRoutineQuery;
import com.example.apollographqlandroid.GetRoutinesByIdOwnerQuery;
import com.example.myapplication.Model.Entities.RoutineEntity;
import com.example.myapplication.Model.Models.RoutineModel;
import com.example.myapplication.Model.Repositories.RoutineRepository;
import com.example.myapplication.Model.Store.RoutineStore;
import com.example.myapplication.R;
import com.example.myapplication.ui.AuthGlobalState;
import com.example.myapplication.ui.GlobalState;
import com.example.myapplication.ui.Routine.EventListener.GetAllTypesRoutineListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterRoutineFragment extends Fragment {
    private MaterialSpinner spinnerTypeRoutines;
    private RoutineStore routineStore;
    private Button btnRegister;
    private TreeMap <String,Integer>ListTypeRoutines;
    private TextInputEditText nameTextInput;
    private TextInputEditText priceTextInput;
    private TextInputEditText descriptionTextInput;
    private TextInputEditText linkPreviewTextInput;
    private AuthGlobalState authGlobalState;
    public RegisterRoutineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_routine, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.routineStore= ViewModelProviders.of(getActivity()).get(RoutineStore.class);
        this.authGlobalState= ViewModelProviders.of(getActivity()).get(AuthGlobalState.class);
        this.ListTypeRoutines=new TreeMap<>();
        this.priceTextInput=view.findViewById(R.id.txtInputPriceRegisterRoutine);
        this.nameTextInput=(TextInputEditText)view.findViewById(R.id.txtInputNameRegisterRoutine);
        this.descriptionTextInput=(TextInputEditText)view.findViewById(R.id.txtInputDescriptionRegisterRoutine);
        this.linkPreviewTextInput=(TextInputEditText)view.findViewById(R.id.txtInputLinkPreviewRegisterRoutine);
        this.linkPreviewTextInput.setText("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4");
        this.btnRegister=(Button)view.findViewById(R.id.btnRoutineRegister);
        this.btnRegister.setOnClickListener(new btnClickListener());
        spinnerTypeRoutines = (MaterialSpinner) view.findViewById(R.id.spinner);

        //spinner.setItems("Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow");
        this.routineStore.getTypeRoutines().observe(getViewLifecycleOwner(), new Observer<List<GetAllTypeRoutineQuery.TypeRoutine>>() {
            @Override
            public void onChanged(List<GetAllTypeRoutineQuery.TypeRoutine> typeRoutines) {
                for (GetAllTypeRoutineQuery.TypeRoutine type :typeRoutines ){

                            ListTypeRoutines.put(type.getName(),Integer.parseInt(type.getId()));

                }
                spinnerTypeRoutines.setItems(ListTypeRoutines.keySet().toArray());

            }
        });
        RoutineModel.getAllTypeRoutine(new GetAllTypesRoutineListener(getActivity(),routineStore));

    }

/*
    private class getAllTypesRoutineListener extends ApolloCall.Callback<GetAllTypeRoutineQuery.Data>{

        @Override
        public void onResponse(@NotNull Response<GetAllTypeRoutineQuery.Data> response) {
            getActivity().runOnUiThread(new Runnable() {
                @Override public void run() {
                    routineStore.setTypeRoutines(response.data().TypeRoutines());
                }
            });
        }

        @Override
        public void onFailure(@NotNull ApolloException e) {

        }
    }
*/
    private class btnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            RoutineEntity routineEntity=new RoutineEntity();
            try{

                routineEntity.setName(nameTextInput.getText().toString().trim());
                routineEntity.setPrice(Float.parseFloat(priceTextInput.getText().toString().trim()));


                routineEntity.setDescription(descriptionTextInput.getText().toString().trim());
                routineEntity.setLink_preview(linkPreviewTextInput.getText().toString().trim());
                String typeSelected= (String) spinnerTypeRoutines.getItems().get(spinnerTypeRoutines.getSelectedIndex());
                Integer idType=ListTypeRoutines.get(typeSelected);
                routineEntity.setIdtype(idType);
                RoutineModel.RegisterRoutine(new ApolloCall.Callback<CrearRutinaMutation.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<CrearRutinaMutation.Data> response) {
                        System.out.println("correcto:"+response.errors());
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        System.out.println("error es "+e.getMessage());
                    }
                },routineEntity, authGlobalState.getToken().getValue());

            }catch (Exception e){
                Toast.makeText(v.getContext(),
                        e.getMessage(), Toast.LENGTH_SHORT).show();////poner bien esto pues ocurre cuando el float no se puede castear
            }


        }
    }
}
