package com.example.myapplication.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationId extends ViewModel {


    private MutableLiveData<Integer> Id_notificacion;

    public NotificationId() {
        this.Id_notificacion =  new MutableLiveData<>();
        setID(0);
    }

    public void setID(Integer s){
        this.Id_notificacion.setValue(s);
    }
    public LiveData<Integer> getID(){
        return this.Id_notificacion;
    }

}
