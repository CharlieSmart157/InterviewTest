package com.example.charlie.interviewtest.Content;

import android.app.Activity;
import android.util.Log;

import com.example.charlie.interviewtest.Models.Session;
import com.example.charlie.interviewtest.Models.loginRequest;
import com.example.charlie.interviewtest.Models.userdetails;
import com.example.charlie.interviewtest.Observables.FarePilotTestAPI;
import com.example.charlie.interviewtest.Utility.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Charlie on 20/01/2017.
 */

public class Content_Presenter implements Content_Contract.Presenter{

    private final Content_Contract.View mContentView;

    FarePilotTestAPI farePilotTestAPI;
    Activity mActivity;
    Retrofit restapi;
    OkHttpClient client;

    public Content_Presenter (Content_Contract.View v){

        mContentView = v;
        mContentView.setPresenter(this);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

    }

    @Override
    public void returnUser(String id) {
        restapi = new Retrofit.Builder()
                .baseUrl(Constants.BaseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        farePilotTestAPI = restapi.create(FarePilotTestAPI.class);

        Call<userdetails> call = farePilotTestAPI.getUser(id);

        call.enqueue(new Callback<userdetails>() {
            @Override
            public void onResponse(Call<userdetails> call, Response<userdetails> response) {

                mContentView.receiveUser(response.body());

            }

            @Override
            public void onFailure(Call<userdetails> call, Throwable t) {

            }
        });
    }

    @Override
    public void createSession(String email, String password) {



        restapi = new Retrofit.Builder()
                .baseUrl(Constants.BaseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        farePilotTestAPI = restapi.create(FarePilotTestAPI.class);

        loginRequest loginRequest = new loginRequest(email, password);
        Call<Session> call = farePilotTestAPI.createSession(loginRequest);
        call.enqueue(new Callback<Session>() {

            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {

                Log.i("IntTest", "Session: "+ response.body());
                mContentView.startSession(response.body());
            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {
                Log.i("IntTest", "Session: "+ t.getMessage());
            }
        });
    }

    @Override
    public void returnAvatar(String avatarData, String userid) {
        restapi = new Retrofit.Builder()
                .baseUrl(Constants.BaseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        farePilotTestAPI = restapi.create(FarePilotTestAPI.class);

        Call<userdetails> call = farePilotTestAPI.getAvatar(avatarData, userid);

        call.enqueue(new Callback<userdetails>() {
            @Override
            public void onResponse(Call<userdetails> call, Response<userdetails> response) {

                Log.i("IntTest", "AvatarURL: "+ response.body());

            }

            @Override
            public void onFailure(Call<userdetails> call, Throwable t) {

            }
        });
    }
}
