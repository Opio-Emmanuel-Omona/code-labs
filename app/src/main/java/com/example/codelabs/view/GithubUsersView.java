package com.example.codelabs.view;

import com.example.codelabs.model.GithubUsers;

import java.util.List;

public interface GithubUsersView {
    void readyUsers(List<GithubUsers> githubUsers);
}
