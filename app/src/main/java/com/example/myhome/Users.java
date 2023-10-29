package com.example.myhome;

public class Users {
    private long userId;
    private String userName;
    private String userEmail;

    private String userImage;

    private CurrencyType userCurrencyPreference;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public CurrencyType getUserCurrencyPreference() {
        return userCurrencyPreference;
    }

    public void setUserCurrencyPreference(CurrencyType userCurrencyPreference) {
        this.userCurrencyPreference = userCurrencyPreference;
    }

    public RoleType getUserRole() {
        return userRole;
    }

    public void setUserRole(RoleType userRole) {
        this.userRole = userRole;
    }

    private RoleType userRole;

}
