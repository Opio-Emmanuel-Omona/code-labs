package com.example.codelabs.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GithubService {

    private Retrofit retrofit = null;

    public GithubApi getAPI() {
        String BASE_URL = "https://api.github.com/";

        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(GithubApi.class);
    }
}
