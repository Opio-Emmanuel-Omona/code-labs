package com.example.codelabs.model;


import com.google.gson.annotations.SerializedName;

public class GithubUsers {

    @SerializedName("login")
    private String username;

    @SerializedName("company")
    private String organisation;

    @SerializedName("url")
    private String profileLink;

    @SerializedName("avatar_url")
    private Integer profileImage;

    public GithubUsers(String username, String organisation, String profileLink, Integer profileImage) {
        this.username = username;
        this.organisation = organisation;
        this.profileLink = profileLink;
        this.profileImage = profileImage;

    }


    public String getUsername() {  return this.username;  }

    public String getOrganisation() {  return this.organisation;  }

    public String getProfileLink() {  return this.profileLink;  }

    public Integer getProfileImage() {  return this.profileImage;  }
}
