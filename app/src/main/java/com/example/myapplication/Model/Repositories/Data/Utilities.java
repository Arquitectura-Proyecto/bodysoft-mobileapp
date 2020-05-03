package com.example.myapplication.Model.Repositories.Data;

public enum Utilities {


    LocalHost("http://192.168.0.12","3800","/graphql");




    private String host;
    private String puerto;
    private String baseUrl;
    private Utilities(String host,String puerto,String baseUrl){
    this.host=host;
    this.puerto=puerto;
    this.baseUrl=baseUrl;
    }
    public String UrlRequest(){
        return this.host+":"+this.puerto+this.baseUrl;
    }
}
