package com.example.mom;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule(MainActivity.class);
    private String mFullname = "Howard Tomas";
    private String mEmail = "homwardytomas@gmail.com";
    private String mpassword = "SuperCool09#";
    private String mPhone = "4073103210";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testInput()
    {
        //Enter in name
        Espresso.onView(withId(R.id.fullName)).perform(ViewActions.typeText(mFullname));
        //Close keyboard
        Espresso.closeSoftKeyboard();
        //Enter in email
        Espresso.onView(withId(R.id.Email)).perform(ViewActions.typeText(mEmail));
        //Close keyboard
        Espresso.closeSoftKeyboard();
        //Enter in password
        Espresso.onView(withId(R.id.password)).perform(ViewActions.typeText(mpassword));
        //close keyboard
        Espresso.closeSoftKeyboard();
        //Enter in phone number
        Espresso.onView(withId(R.id.phone)).perform(ViewActions.typeText(mPhone));
        //Close keyboard
        Espresso.closeSoftKeyboard();
        //Click Register button
        Espresso.onView(withId(R.id.registerBtn)).perform(click());

    }




    @After
    public void tearDown() throws Exception {
    }
}