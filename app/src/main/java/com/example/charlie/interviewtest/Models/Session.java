package com.example.charlie.interviewtest.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Charlie on 20/01/2017.
 */

public class Session extends RealmObject {

    @SerializedName("userid")
    @Expose
    private String userid;

    @SerializedName("token")
    @Expose
    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getToken() {
        return token;
    }

    public String getUserid() {
        return userid;
    }
}
