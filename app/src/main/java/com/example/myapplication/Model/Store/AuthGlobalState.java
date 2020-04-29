package com.example.myapplication.Model.Store;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AuthGlobalState extends ViewModel{

        private MutableLiveData<String> sharedString;
        public AuthGlobalState(){
            this.sharedString=new MutableLiveData<>();

        }
        public void setString(String s){
            this.sharedString.setValue(s);
        }
        public LiveData<String> getSharedString(){
            return this.sharedString;
        }
}
