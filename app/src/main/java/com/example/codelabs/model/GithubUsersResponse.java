package com.example.codelabs.model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GithubUsersResponse {

    ArrayList<GithubUsers> users;

    public GithubUsersResponse() {
        users = new ArrayList<GithubUsers>();
    }

    public static GithubUsersResponse parseJSON(String response) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        Gson gson = gsonBuilder.create();
        GithubUsersResponse githubUsersResponse = gson.fromJson(response, GithubUsersResponse.class);
        return githubUsersResponse;
    }

    public ArrayList<GithubUsers> getGithubUsersResponse() {
        return this.users;
    }

}
