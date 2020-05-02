package com.example.myapplication.ui.Routine.User;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserRoutinePreviewFragment extends Fragment {
//
//
    private Button btnRequestRoutine;

    public UserRoutinePreviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_routine_preview, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        this.btnRequestRoutine = (Button)view.findViewById(R.id.btnGotoMyRoutinesList);

    }
}
