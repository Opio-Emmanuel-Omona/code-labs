package com.example.codelabs.view;

import com.example.codelabs.model.GithubUsers;

import java.util.ArrayList;

public interface GithubUsersView {
    void readyUsers(ArrayList<GithubUsers> githubUsers);
}
