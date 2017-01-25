package com.example.charlie.interviewtest.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Charlie on 20/01/2017.
 */

public class userdetails extends RealmObject {

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("avatarurl")
    @Expose
    private String avatarurl;

    public String getEmail() {
        return email;
    }

    public String getAvatarurl() {
        return avatarurl;
    }
}
