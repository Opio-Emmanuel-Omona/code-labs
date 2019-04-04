package com.example.codelabs.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
    String url;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initDetail();
        extras();

        GithubProfilePresenter githubProfilePresenter = new GithubProfilePresenter(new GithubProfileView() {
            @Override
            public void readyProfile(GithubUsers result) {
                String org = result.getOrganisation();
                url = result.getProfileLink();

                if(org != null) {
                    organisation.setText(org);
                }

                profileLink.setText(url);
            }
        });

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
    }

    void extras() {
        String image = getIntent().getExtras().getString("ProfileImage");
        user = getIntent().getExtras().getString("Username");

        username.setText(user);
        Picasso.get().load(image).into(profileImage);
    }
}
