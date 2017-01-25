package com.example.charlie.interviewtest.Content;

import com.example.charlie.interviewtest.Models.Session;
import com.example.charlie.interviewtest.Models.userdetails;
import com.example.charlie.interviewtest.Templates.BasePresenter;
import com.example.charlie.interviewtest.Templates.BaseView;

/**
 * Created by Charlie on 20/01/2017.
 */

public interface Content_Contract {

    interface View extends BaseView<Presenter> {

        void startSession(Session session);
        void receiveUser(userdetails user);
        void receiveAvatar(String avatarUrl);
    }

    interface Presenter extends BasePresenter {

        void returnUser(String id);
        void createSession(String email, String password);
        void returnAvatar(String avatarUrl, String userid);

    }
}
