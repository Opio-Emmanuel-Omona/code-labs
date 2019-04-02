package com.example.codelabs.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private Button shareButton;
    private GithubProfilePresenter githubProfilePresenter;
    private String user;
    private String image;
    private String org;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        username = findViewById(R.id.activity_detail_username);
        organisation = findViewById(R.id.activity_detail_organisation);
        profileLink = findViewById(R.id.activity_detail_profile_link);
        profileImage = findViewById(R.id.activity_detail_profile_image);
        shareButton = findViewById(R.id.share_button);

        user = getIntent().getExtras().getString("Username");
        image = getIntent().getExtras().getString("ProfileImage");

        username.setText(user);
        Picasso.get().load(image).into(profileImage);

        githubProfilePresenter = new GithubProfilePresenter(new GithubProfileView() {
            @Override
            public void readyProfile(GithubUsers result) {
                org = result.getOrganisation();
                url = result.getProfileLink();

                if(org != null) {
                    organisation.setText(org);
                }

                profileLink.setText(url);
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String content = "Checkout this awesome developer @" + user + ", " + url + ".";
                intent.putExtra(Intent.EXTRA_TEXT, content);
                startActivity(Intent.createChooser(intent, "Share Using"));
            }
        });

        githubProfilePresenter.getUserProfile(user);
    }
}
