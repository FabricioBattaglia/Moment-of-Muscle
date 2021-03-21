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

public class ResetPasswordCancelTest {

    @Rule
    public ActivityScenarioRule mActivityTestRule = new ActivityScenarioRule(ResetPassword.class);

    private String sPass = "OldPasser45";
    private String uPass = "NewPasser22";

    @Before
    public void setUp() throws Exception {
    }

    @Test

    public void ChangeCancelMidway()
    {
        Espresso.onView(withId(R.id.editTextTextPassword)).perform(typeText(sPass));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.cancelNewPassword)).perform(click());
    }

    @Test
    public void ChangeCancelEnd()
    {
        Espresso.onView(withId(R.id.editTextTextPassword)).perform(typeText(sPass));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.editTextTextPassword2)).perform(typeText(uPass));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.cancelNewPassword)).perform(click());

    }



    @After
    public void tearDown() throws Exception {
    }
}