package com.example.myapplication.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AuthGlobalState extends ViewModel {

    private MutableLiveData<String> token;
    private MutableLiveData<Integer> typeID;

    public AuthGlobalState() {
        this.token =  new MutableLiveData<>();
        this.typeID = new MutableLiveData<>();
    }

    public void setToken(String s){
        this.token.setValue(s);
    }
    public LiveData<String> getToken(){
        return this.token;
    }

    public void setTypeID(Integer s){
        this.typeID.setValue(s);
    }
    public LiveData<Integer> getTypeId(){
        return this.typeID;
    }
}
