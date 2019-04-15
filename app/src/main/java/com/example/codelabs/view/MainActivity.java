package com.example.codelabs.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.Snackbar;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.codelabs.adapter.GithubAdapter;
import com.example.codelabs.R;
import com.example.codelabs.model.GithubUsers;
import com.example.codelabs.presenter.GithubPresenter;
import com.example.codelabs.util.NetworkUtility;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar;
    private List<? extends GithubUsers> githubUsersList;
    Parcelable state;
    NetworkUtility githubApplication;
    CountingIdlingResource countingIdlingResource = new CountingIdlingResource("Main");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null) {
            githubUsersList = savedInstanceState.getParcelableArrayList("users");
            state = savedInstanceState.getParcelable("listState");
        } else {
            fetchData();
        }
        initRecyclerView();
        initSwipe();
    }

    private void fetchData() {
        GithubPresenter githubPresenter = new GithubPresenter(new GithubUsersView() {
            @Override
            public void readyUsers(List<GithubUsers> githubUsers) {
                githubUsersList = githubUsers;
                loadData(githubUsers);
            }
        });

        if(githubApplication.isNetworkAvailable(this)) {
            countingIdlingResource.increment();
            githubPresenter.getUsers();
            countingIdlingResource.decrement();
        }else {
            Snackbar.make(findViewById(R.id.cordinator), "No internet connection", Snackbar.LENGTH_INDEFINITE).setDuration(60000).show();
        }
    }

    private void loadData(List<GithubUsers> githubUsers) {
        recyclerView.setAdapter(new GithubAdapter(githubUsers, new OnListListener() {
            @Override
            public void onItemClick(int position) {
                clicked(position);
            }
        }));
        swipeRefreshLayout.setRefreshing(false);

        progressBar.setVisibility(View.GONE);
    }

    public void initRecyclerView() {
        recyclerView = findViewById(R.id.my_recycler_view);
        progressBar = findViewById(R.id.progress_bar1);
        githubApplication = new NetworkUtility();
        recyclerView.setHasFixedSize(true);
        configChanged();
    }

    private void initSwipe() {
        swipeRefreshLayout = findViewById(R.id.swipeToRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchData();
            }
        });
    }

    public void clicked(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("Username", GithubPresenter.result.get(position).getUsername());
        intent.putExtra("ProfileImage", GithubPresenter.result.get(position).getProfileImage());
        startActivity(intent);
    }

    public void configChanged() {
        int orientation = this.getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }else if(orientation== Configuration.ORIENTATION_LANDSCAPE){
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("githubUsers", (ArrayList<? extends Parcelable>) githubUsersList);
        outState.putParcelable("state", recyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstance) {
        githubUsersList = savedInstance.getParcelableArrayList("githubUsers");
        state = savedInstance.getParcelable("state");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (state != null) {
            recyclerView.getLayoutManager().onRestoreInstanceState(state);
            loadData((List<GithubUsers>) githubUsersList);
        }
    }

    @VisibleForTesting
    public CountingIdlingResource getCountingIdlingResource(){
        return countingIdlingResource;
    }

}
