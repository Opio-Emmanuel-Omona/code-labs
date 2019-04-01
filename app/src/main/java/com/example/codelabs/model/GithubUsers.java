package com.example.codelabs.model;


import com.google.gson.annotations.SerializedName;

public class GithubUsers {

    @SerializedName("login")
    private String username;

    @SerializedName("company")
    private String organisation;

    @SerializedName("html_url")
    private String profileLink;

    @SerializedName("avatar_url")
    private String profileImage;

    public GithubUsers(String username, String organisation, String profileLink, String profileImage) {
        this.username = username;
        this.organisation = organisation;
        this.profileLink = profileLink;
        this.profileImage = profileImage;

    }

    public String getUsername() {  return this.username;  }

    public String getOrganisation() {  return this.organisation;  }

    public String getProfileLink() {  return this.profileLink;  }

    public String getProfileImage() {  return this.profileImage;  }

}
