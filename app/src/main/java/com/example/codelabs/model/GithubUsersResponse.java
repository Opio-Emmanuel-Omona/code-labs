package com.example.codelabs.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GithubUsersResponse {

    @SerializedName("items")
    List<GithubUsers> users;

    public List<GithubUsers> getGithubUsersResponse() {
        return this.users;
    }

}
