package com.example.codelabs.model;


import com.google.gson.annotations.SerializedName;

public class GithubUsers {

    @SerializedName("login")
    final String username;

    @SerializedName("company")
    final String organisation;

    @SerializedName("html_url")
    final String profileLink;

    @SerializedName("avatar_url")
    final String profileImage;

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
