package com.example.myhome.Api;

public class GoogleCredentials {
    private String userId;
    private String userEmail;
    private String userImage;
    private String userName;

    public GoogleCredentials(String userId, String userEmail, String userImage, String userName) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userImage = userImage;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
