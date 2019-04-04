package com.example.codelabs.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.codelabs.adapter.GithubAdapter;
import com.example.codelabs.R;
import com.example.codelabs.model.GithubUsers;
import com.example.codelabs.presenter.GithubPresenter;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        GithubPresenter githubPresenter = new GithubPresenter(new GithubUsersView() {
            @Override
            public void readyUsers(List<GithubUsers> githubUsers) {
                recyclerView.setAdapter(new GithubAdapter(githubUsers, new OnListListener() {
                    @Override
                    public void onItemClick(int position) {
                        clicked(position);
                    }
                }));
            }
        });
        githubPresenter.getUsers();
    }

    public void initRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void clicked(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("Username", GithubPresenter.result.get(position).getUsername());
        intent.putExtra("ProfileImage", GithubPresenter.result.get(position).getProfileImage());
        startActivity(intent);
    }

}
