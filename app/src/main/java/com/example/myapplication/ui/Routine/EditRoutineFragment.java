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
import android.widget.Button;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.GetAllTypeRoutineQuery;
import com.example.apollographqlandroid.GetRoutinesByIdOwnerQuery;
import com.example.apollographqlandroid.UpdateRoutineMutation;
import com.example.myapplication.Model.Entities.RoutineEntity;
import com.example.myapplication.Model.Models.RoutineModel;
import com.example.myapplication.Model.Repositories.RoutineRepository;
import com.example.myapplication.Model.Store.RoutineStore;
import com.example.myapplication.R;
import com.example.myapplication.ui.GlobalState;
import com.example.myapplication.ui.Routine.EventListener.GetAllTypesRoutineListener;
import com.google.android.material.textfield.TextInputEditText;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.TreeMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditRoutineFragment extends Fragment {

    private MaterialSpinner spinnerTypeRoutines;
    private RoutineStore routineStore;
    private TreeMap<String,Integer> ListTypeRoutines;
    private Button btnRoutineEdit;
    private TextInputEditText priceTextInput;
    private TextInputEditText nameTextInput;
    private  TextInputEditText descriptionTextInput;

    public EditRoutineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_routine, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.routineStore= ViewModelProviders.of(getActivity()).get(RoutineStore.class);
        GetRoutinesByIdOwnerQuery.Routine routine=routineStore.getInformationRoutine().getValue();
        this.spinnerTypeRoutines = (MaterialSpinner) view.findViewById(R.id.spinnerEditRoutine);

        this.priceTextInput=view.findViewById(R.id.txtInputPriceEditRoutine);
        this.priceTextInput.setText(""+routine.getPrice());
        this.nameTextInput=(TextInputEditText)view.findViewById(R.id.txtInputNameEditRoutine);
        this.nameTextInput.setText(routine.getName());
        this.descriptionTextInput=(TextInputEditText)view.findViewById(R.id.txtInputDescriptionEditRoutine);
        this.descriptionTextInput.setText(routine.getDescription());
        this.ListTypeRoutines=new TreeMap<>();
        this.btnRoutineEdit=(Button)view.findViewById(R.id.btnRoutineEdit);
        this.btnRoutineEdit.setOnClickListener(new btnEditRoutineListener());
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
   private class btnEditRoutineListener implements View.OnClickListener{

       @Override
       public void onClick(View v) {
           GetRoutinesByIdOwnerQuery.Routine routine=routineStore.getInformationRoutine().getValue();

           try{
               RoutineEntity editedRoutine=new RoutineEntity();//new RoutineEntity(Integer.parseInt(routine.getId()),routine.getIdOwner(),routine.getRaiting())
               editedRoutine.setId(Integer.parseInt(routine.getId()));

               editedRoutine.setPrice(Float.parseFloat(priceTextInput.getText().toString().trim()));
               editedRoutine.setName(nameTextInput.getText().toString());
               editedRoutine.setDescription(descriptionTextInput.getText().toString());
               editedRoutine.setLink_preview(routine.getLinkPreview());

               String typeSelected= (String) spinnerTypeRoutines.getItems().get(spinnerTypeRoutines.getSelectedIndex());
               Integer idType=ListTypeRoutines.get(typeSelected);
               editedRoutine.setIdtype(idType);
               RoutineModel.EditRoutine(new ApolloCall.Callback<UpdateRoutineMutation.Data>() {
                   @Override
                   public void onResponse(@NotNull Response<UpdateRoutineMutation.Data> response) {
                       System.out.println("editada"+response.data());
                   }

                   @Override
                   public void onFailure(@NotNull ApolloException e) {
                       e.printStackTrace();
                   }
               },editedRoutine, GlobalState.getToken());
           }catch (Exception e){
               System.out.println("errorcillo");
           }

       }
   }
}
