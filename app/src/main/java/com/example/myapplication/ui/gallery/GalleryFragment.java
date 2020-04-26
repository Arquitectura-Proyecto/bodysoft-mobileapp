package com.example.myapplication.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.myapplication.R;
import com.example.myapplication.ui.GlobalState;

public class GalleryFragment extends Fragment {
    EditText txt;
   // private GalleryViewModel galleryViewModel;
    GlobalState globalState;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_gallery,container,false);
        globalState= ViewModelProviders.of(getActivity()).get(GlobalState.class);

    return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnGoToRoutine=(Button)view.findViewById(R.id.btnGotoroutine);
        txt=(EditText)view.findViewById(R.id.text_gallery);
        globalState.getSharedString().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txt.setText(s);
            }
        });
        final NavController navController=Navigation.findNavController(view);
        final Bundle prop=new Bundle();
        prop.putString("name","Nombre desde galeria");
        btnGoToRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalState.setString(txt.getText().toString());
                navController.navigate(R.id.goToRoutine,prop);
            }
        });
    }
}
