package com.example.myapplication.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GlobalState extends ViewModel {
    private static  String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJJRCI6MiwiUHJvZmlsZSI6ZmFsc2UsIlR5cGVJRCI6MSwiZXhwIjoxNTg4MzkzNDYwfQ.gVUNim1wj9i8VkojnBu0D5liYcZI3CF0UuuJmfPUnaE";
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
    public static String getToken(){
        return token;
    }
}
