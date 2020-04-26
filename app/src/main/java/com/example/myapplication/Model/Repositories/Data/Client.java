package com.example.myapplication.Model.Repositories.Data;

import com.apollographql.apollo.ApolloClient;

import okhttp3.internal.Util;

public class Client {
    public static ApolloClient apolloClient=ApolloClient.builder().serverUrl(Utilities.LocalHost.UrlRequest()).build();
    public Client(){}

    public static ApolloClient getApolloClient() {
        return apolloClient;
    }
}
