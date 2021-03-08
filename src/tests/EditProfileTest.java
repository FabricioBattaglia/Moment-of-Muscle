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

public class EditProfileTest
{
    @Rule
    public ActivityScenarioRule mActivityTestRule = new ActivityScenarioRule(EditProfile.class);
    private String iden = "RiverSticks";
    private String mailer = "rivertsticks@gmail.com";
    private String phoneNo = "(407)-664-1220";


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void profileChange()
    {
        Espresso.onView(withId(R.id.editTextTextPersonName2)).perform(typeText(iden));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.editTextTextEmailAddress2)).perform(typeText(mailer));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.editTextPhone2)).perform(typeText(phoneNo));
    }

    @Test
    public void profileCancel()
    {
       Espresso.onView(withId(R.id.cancelChangesButton)).perform(click());
    }


    @After
    public void tearDown() throws Exception {
    }
}