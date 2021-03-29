package com.example.mom;

import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

public class AddJobTest
{

    @Rule
    ActivityScenarioRule<HomeScreen> mActivityScenarioRule = new ActivityScenarioRule(HomeScreen.class);
    private String jerb = "Take care of my dog";
    private String money = "$70.00";
    private String dog = "I'm going on vacation, and I need someone to look after my dog for a couple of days" +
            "Need some help, please";


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void jobAdd()
    {
        Espresso.onView(withId(R.id.toJobButton)).perform(click());
        Espresso.onView(withId(R.id.JobPostingEntry)).perform(typeText(jerb));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.JobTypeSpinner)).perform(click());
        Espresso.onData(allOf(is(instanceOf(String.class)), is("Pet Sitting"))).perform(click());
        Espresso.onView(withId(R.id.jobPrice)).perform(typeText(money));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.jobDescription)).perform(typeText(dog));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.AddJobButton)).perform(click());
        Espresso.pressBack();
    }



    @After
    public void tearDown() throws Exception {
    }
}