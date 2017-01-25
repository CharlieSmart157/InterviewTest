package com.example.charlie.interviewtest;

import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by Charlie on 25/01/2017.
 */
public class LoginTest {

    LoginActivity loginActivity;
    @BeforeClass
    public static void setup(){


    }
    @Test
    public void loginValidatorTest() {
       loginActivity = new LoginActivity();

        assertNotNull(loginActivity.mPresenter);
    }
}
