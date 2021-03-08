package com.example.mom;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
//import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class EmailVerificationTest {

    @Rule
    public ActivityScenarioRule mActivityTestRule = new ActivityScenarioRule(EmailVerification.class);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void pressingSend()
    {

        Espresso.onView(withId(R.id.verificationBtn)).perform(click());
    }



    @Test
    public void pressingLogout()
    {
        Espresso.onView(withId(R.id.alreadyVerified)).perform(click());
    }



    @After
    public void tearDown() throws Exception {
    }
}