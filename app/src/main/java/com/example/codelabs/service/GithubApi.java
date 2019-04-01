package com.example.codelabs.service;

import com.example.codelabs.model.GithubUsers;
import com.example.codelabs.model.GithubUsersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubApi {

    @GET("search/users?q=type:User+location:Nairobi+language:JAVA")
    Call<GithubUsersResponse> listUsers();

    @GET("users/{username}")
    Call<GithubUsers> getUser(@Path("username") String username);

}
