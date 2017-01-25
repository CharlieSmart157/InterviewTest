package com.example.charlie.interviewtest.Observables;

import com.example.charlie.interviewtest.Models.Session;
import com.example.charlie.interviewtest.Models.loginRequest;
import com.example.charlie.interviewtest.Models.userdetails;
import com.example.charlie.interviewtest.Utility.Constants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Charlie on 20/01/2017.
 */

public interface FarePilotTestAPI {

    @POST(Constants.NewSessionURL)
    Call<Session>createSession(@Body loginRequest loginRequest);

    @GET(Constants.UserURL)
    Call<userdetails>getUser(@Path("userid") String userid);

    @POST(Constants.AvatarURL)
    Call<userdetails>getAvatar(@Body String avatarData, @Path("userid") String userid);


}
