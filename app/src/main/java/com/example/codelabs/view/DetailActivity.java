package com.example.codelabs.view;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.codelabs.R;
import com.example.codelabs.model.GithubUsers;
import com.example.codelabs.presenter.GithubProfilePresenter;
import com.squareup.picasso.Picasso;


public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    TextView username;
    TextView organisation;
    TextView profileLink;
    ImageView profileImage;
    GithubUsers githubUsers;
    ProgressBar progressBar;
    LinearLayout detail;
    String url;
    String user;
    String image;
    String org;
    CountingIdlingResource countingIdlingResource = new CountingIdlingResource("Main");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initDetail();

        if(savedInstanceState != null) {
            githubUsers = savedInstanceState.getParcelable("user");
            loadProfile(githubUsers);
        } else {
            fetchProfile();
        }

    }

    private void fetchProfile() {
        GithubProfilePresenter githubProfilePresenter = new GithubProfilePresenter(new GithubProfileView() {
            @Override
            public void readyProfile(GithubUsers result) {
                githubUsers = result;
                loadProfile(githubUsers);

            }
        });
        extras();
        countingIdlingResource.increment();
        githubProfilePresenter.getUserProfile(user);
    }

    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String content = "Checkout this awesome developer @" + user + ", " + url + ".";
        intent.putExtra(Intent.EXTRA_TEXT, content);
        startActivity(Intent.createChooser(intent, "Share Using"));
    }

    void initDetail() {
        username = findViewById(R.id.activity_detail_username);
        organisation = findViewById(R.id.activity_detail_organisation);
        profileLink = findViewById(R.id.activity_detail_profile_link);
        profileImage = findViewById(R.id.activity_detail_profile_image);
        progressBar = findViewById(R.id.progress_bar2);
        detail = findViewById(R.id.detail);
        detail.setVisibility(View.GONE);
    }

    void extras() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            image = getIntent().getExtras().getString("ProfileImage");
            user = getIntent().getExtras().getString("Username");
        }
    }

    void loadProfile(GithubUsers githubUsers) {
        user = githubUsers.getUsername();
        url = githubUsers.getProfileLink();
        org = githubUsers.getOrganisation();
        image = githubUsers.getProfileImage();

        username.setText(user);
        profileLink.setText(url);
        Picasso.get().load(image).into(profileImage);
        if(org != null) {
            organisation.setText(org);
        }
        progressBar.setVisibility(View.GONE);
        detail.setVisibility(View.VISIBLE);
        countingIdlingResource.decrement();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("user", githubUsers);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstance) {
        githubUsers = savedInstance.getParcelable("user");
    }

    @VisibleForTesting
    public CountingIdlingResource getCountingIdlingResource(){
        return countingIdlingResource;
    }

}
