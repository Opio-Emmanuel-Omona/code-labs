package com.example.codelabs.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GithubUsersResponse {

    @SerializedName("items")
    ArrayList<GithubUsers> users;

    public GithubUsersResponse() {
        users = new ArrayList<GithubUsers>();
    }

    public ArrayList<GithubUsers> getGithubUsersResponse() {
        return this.users;
    }

}
