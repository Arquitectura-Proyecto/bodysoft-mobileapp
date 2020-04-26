package com.example.myapplication.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GlobalState extends ViewModel {

    private MutableLiveData<String>  sharedString;
    public GlobalState(){
        this.sharedString=new MutableLiveData<>();

    }
    public void setString(String s){
        this.sharedString.setValue(s);
    }
    public LiveData<String> getSharedString(){
        return this.sharedString;
    }
}
