package com.example.codelabs.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class GithubUsers implements Parcelable {

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

    protected GithubUsers(Parcel in) {
        username = in.readString();
        organisation = in.readString();
        profileLink = in.readString();
        profileImage = in.readString();
    }

    public static final Creator<GithubUsers> CREATOR = new Creator<GithubUsers>() {
        @Override
        public GithubUsers createFromParcel(Parcel in) {
            return new GithubUsers(in);
        }

        @Override
        public GithubUsers[] newArray(int size) {
            return new GithubUsers[size];
        }
    };

    public String getUsername() {  return this.username;  }

    public String getOrganisation() {  return this.organisation;  }

    public String getProfileLink() {  return this.profileLink;  }

    public String getProfileImage() {  return this.profileImage;  }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(organisation);
        dest.writeString(profileLink);
        dest.writeString(profileImage);
    }
}
