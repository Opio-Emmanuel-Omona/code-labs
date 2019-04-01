package com.example.codelabs.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.codelabs.R;
import com.example.codelabs.model.GithubUsers;
import com.example.codelabs.presenter.GithubPresenter;
import com.example.codelabs.presenter.GithubProfilePresenter;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private TextView username;
    private TextView organisation;
    private TextView profileLink;
    private ImageView profileImage;
    private GithubProfilePresenter githubProfilePresenter;
    private String user;
    private String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        username = findViewById(R.id.activity_detail_username);
        organisation = findViewById(R.id.activity_detail_organisation);
        profileLink = findViewById(R.id.activity_detail_profile_link);
        profileImage = findViewById(R.id.activity_detail_profile_image);

        user = getIntent().getExtras().getString("Username");
        image = getIntent().getExtras().getString("ProfileImage");

        username.setText(user);
        Picasso.get().load(image).into(profileImage);

        githubProfilePresenter = new GithubProfilePresenter(new GithubProfileView() {
            @Override
            public void readyProfile(GithubUsers result) {
                String org = result.getOrganisation();

                if(org != null) {
                    organisation.setText(org);
                }

                profileLink.setText(result.getProfileLink());
            }
        });

        githubProfilePresenter.getUserProfile(user);
    }
}
