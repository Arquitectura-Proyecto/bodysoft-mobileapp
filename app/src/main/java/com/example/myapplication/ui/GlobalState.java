package com.example.myapplication.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GlobalState extends ViewModel {

    private static  String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJJRCI6MiwiUHJvZmlsZSI6ZmFsc2UsIlR5cGVJRCI6MiwiZXhwIjoxNTg4NDY0NzQzfQ.CQUNz13a_e6WLW-0qN2fECQObvUowyz9lJXAJsNZb2Y";

    private static  String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJJRCI6MSwiUHJvZmlsZSI6ZmFsc2UsIlR5cGVJRCI6MiwiZXhwIjoxNTg4NDcxMjg0fQ.Pgcyz3JG-i5W7gETPxBaDgaN2ySk-dP4KkUgD6ruHZA";

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
