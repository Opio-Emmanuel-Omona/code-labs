package com.example.codelabs.service;

import com.example.codelabs.model.GithubUsers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubApi {

    @GET("search/users?q=type:User+location:Nairobi+language:JAVA")
    Call<GithubUsers> listUsers();

    @GET("users/{username}")
    Call<GithubUsers> user(@Path("username") String username);
}
