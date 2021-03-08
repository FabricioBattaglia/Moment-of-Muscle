package com.example.mom;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
//import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class ResetPasswordTest {

    @Rule
    public ActivityScenarioRule mActivityTestRule = new ActivityScenarioRule(ResetPassword.class);

    private String oPass = "Something09";
    private String nPass = "NewerPass12";

    @Before
    public void setUp() throws Exception {
    }


    @Test

    public void passwordChange()
    {
        //Input old password
        Espresso.onView(withId(R.id.editTextTextPassword)).perform(typeText(oPass));
        //Close keyboard
        Espresso.closeSoftKeyboard();
        //Input new passowrd.
        Espresso.onView(withId(R.id.editTextTextPassword2)).perform(typeText(nPass));
        //Close keyboard
        Espresso.closeSoftKeyboard();
        //click the submit button
        Espresso.onView(withId(R.id.submitNewPassword)).perform(click());



    }


    @After
    public void tearDown() throws Exception {
    }
}