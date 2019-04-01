package com.example.codelabs.presenter;

import android.util.Log;

import com.example.codelabs.model.GithubUsers;
import com.example.codelabs.service.GithubService;
import com.example.codelabs.view.GithubProfileView;
import com.example.codelabs.view.GithubUsersView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubProfilePresenter {

    GithubProfileView githubProfileView;
    GithubService githubService;
    public static GithubUsers result;

    public GithubProfilePresenter(GithubProfileView githubProfileView) {
        this.githubProfileView = githubProfileView;

        if(this.githubService == null) {
            this.githubService = new GithubService();
        }
    }

    public void getUserProfile(String username) {
        githubService
                .getAPI()
                .getUser(username)
                .enqueue(new Callback<GithubUsers>() {
                    @Override
                    public void onResponse(Call<GithubUsers> call, Response<GithubUsers> response) {
                        GithubUsers githubUsers = response.body();
                        githubProfileView.readyProfile(githubUsers);
                    }

                    @Override
                    public void onFailure(Call<GithubUsers> call, Throwable t) {
                        Log.d("TAG", "onFailure() returned: " + t);
                    }
                });
    }

}
