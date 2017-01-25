package com.example.charlie.interviewtest;

import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by Charlie on 25/01/2017.
 */

public class MainActivityTest {
    MainActivity mainActivity;
    @BeforeClass
    public static void setup(){

    }

    @Test
    public void loginValidatorTest() {
        mainActivity = new MainActivity();

        assertNotNull(mainActivity.mPresenter);
    }
}
