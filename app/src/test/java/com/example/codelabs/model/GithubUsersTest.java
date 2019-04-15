package com.example.codelabs.model;

import android.os.Parcel;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class GithubUsersTest {

    @Test
    public void test_github_users_is_parcelable() {
        GithubUsers githubUsers = new GithubUsers("username", "organisation", "profileLink", "image");
        Parcel parcel = Parcel.obtain();
        githubUsers.writeToParcel(parcel, githubUsers.describeContents());
        parcel.setDataPosition(0);

        GithubUsers createdFromParcel = GithubUsers.CREATOR.createFromParcel(parcel);
        assertThat(createdFromParcel.getUsername(), is("username"));
        assertThat(createdFromParcel.getOrganisation(), is("organisation"));
        assertThat(createdFromParcel.getProfileLink(), is("profileLink"));
        assertThat(createdFromParcel.getProfileImage(), is("image"));
    }

}
