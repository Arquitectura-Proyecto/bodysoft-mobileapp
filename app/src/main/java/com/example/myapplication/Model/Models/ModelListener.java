package com.example.myapplication.Model.Models;

public interface ModelListener<T> {
    public void onSuccess(T data);
    public void onError(Exception e);
}
