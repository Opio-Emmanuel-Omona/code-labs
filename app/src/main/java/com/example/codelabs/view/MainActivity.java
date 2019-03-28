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

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<GithubUsers> githubUsersArray;
    private GithubPresenter githubPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        githubPresenter = new GithubPresenter(new GithubUsersView() {
            @Override
            public void readyUsers(ArrayList<GithubUsers> githubUsers) {
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
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void clicked(int position) {
        githubUsersArray.get(position);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("Username", githubUsersArray.get(position).getUsername());
        intent.putExtra("ProfileImage", githubUsersArray.get(position).getProfileImage());
        startActivity(intent);
    }

}
