package com.example.mom;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

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
    public ActivityTestRule<EditProfile> mActivityTestRule = new ActivityTestRule<EditProfile>(EditProfile.class);
    private String iden = "RiverSticks";
    private String phoneNo = "(407)-664-1220";


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void profileChange()
    {
        Espresso.onView(withId(R.id.newName)).perform(typeText(iden));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.newPhone)).perform(typeText(phoneNo));
        Espresso.onView(withId(R.id.saveChangesButton)).perform(click());
    }

    @Test
    public void midCancel()
    {
        Espresso.onView(withId(R.id.newName)).perform(typeText(iden));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.cancelChangesButton)).perform(click());
    }

    @Test
    public void filloutCancel()
    {
        Espresso.onView(withId(R.id.newName)).perform(typeText(iden));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.newPhone)).perform(typeText(phoneNo));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.cancelChangesButton)).perform(click());
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
