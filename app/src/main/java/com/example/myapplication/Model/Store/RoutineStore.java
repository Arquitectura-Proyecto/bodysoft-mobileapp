package com.example.myapplication.Model.Store;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.Model.Entities.RoutineEntity;

import java.util.List;

public class RoutineStore extends ViewModel {

    private MutableLiveData<List<RoutineEntity>>listRoutine;

}
