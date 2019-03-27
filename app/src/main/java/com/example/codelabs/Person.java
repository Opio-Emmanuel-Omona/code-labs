package com.example.codelabs;


public class Person {
    private String username;
    private String organisation;
    private String profileLink;
    private Integer profileImage;


    public void setUsername(String username) {  this.username = username;  }


    public void setOrganisation(String organisation) {  this.organisation = organisation;  }

    public void setProfileLink(String profileLink) {  this.profileLink = profileLink;  }

    public void setProfileImage(Integer profileImage) {  this.profileImage = profileImage;  }

    public String getUsername() {  return this.username;  }

    public String getOrganisation() {  return this.organisation;  }

    public String getProfileLink() {  return this.profileLink;  }

    public Integer getProfileImage() {  return this.profileImage;  }
}
