package com.example.codelabs.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.codelabs.MyAdapter;
import com.example.codelabs.OnListListener;
import com.example.codelabs.R;
import com.example.codelabs.model.GithubUsers;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements OnListListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<GithubUsers> githubUsersArray;
    private GithubUsers githubUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Integer [] images = {R.drawable.emmanuel, R.drawable.moses};

        githubUsersArray = new ArrayList<GithubUsers>();
        githubUsers = new GithubUsers("Emmanuel", "Andela", "http://github.com/opio-emmanuel-omona", images[0]);
        githubUsersArray.add(githubUsers);

        githubUsers = new GithubUsers("Moses", "Andela", "http://github.com/mosesk", images[1]);
        githubUsersArray.add(githubUsers);

        mAdapter = new MyAdapter(githubUsersArray, this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(int position) {
        githubUsersArray.get(position);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("Username", githubUsersArray.get(position).getUsername());
        intent.putExtra("ProfileImage", githubUsersArray.get(position).getProfileImage());
        startActivity(intent);
    }
}
