package com.example.codelabs.presenter;

import android.util.Log;

import com.example.codelabs.model.GithubUsers;
import com.example.codelabs.model.GithubUsersResponse;
import com.example.codelabs.service.GithubService;
import com.example.codelabs.view.GithubUsersView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubPresenter {

    private GithubService githubService;
    private GithubUsersView githubUsersView;

    public GithubPresenter(GithubUsersView githubUsersView) {
        this.githubUsersView = githubUsersView;

        if(this.githubService == null) {
            this.githubService = new GithubService();
        }
    }

    public void getUsers() {
        githubService
                .getAPI()
                .listUsers()
                .enqueue(new Callback<GithubUsersResponse>() {
                    @Override
                    public void onResponse(Call<GithubUsersResponse> call, Response<GithubUsersResponse> response) {
                        GithubUsersResponse githubUsersResponse = response.body();

                        if(githubUsersResponse != null && githubUsersResponse.getGithubUsersResponse() != null) {
                            ArrayList<GithubUsers> result = githubUsersResponse.getGithubUsersResponse();
                            githubUsersView.readyUsers(result);
                        }
                    }

                    @Override
                    public void onFailure(Call<GithubUsersResponse> call, Throwable t) {
                        Log.d("TAG", "onFailure() returned: " + t);
                    }
                });
    }
}
