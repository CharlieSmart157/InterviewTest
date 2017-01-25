package com.example.charlie.interviewtest.Utility;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by Charlie on 25/01/2017.
 */

public class myApp extends Application {



    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);


    }
}