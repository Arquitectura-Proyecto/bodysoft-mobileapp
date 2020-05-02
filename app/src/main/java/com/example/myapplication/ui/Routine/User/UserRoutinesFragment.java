package com.example.myapplication.ui.Routine.User;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.myapplication.R;
import com.jaredrummler.materialspinner.MaterialSpinner;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserRoutinesFragment extends Fragment {
    private RecyclerView recyclerUserRoutines;

    public UserRoutinesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_routines, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.recyclerUserRoutines = (RecyclerView) view.findViewById(R.id.recyclerUserRoutines);
    }
}
