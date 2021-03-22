package com.example.mom;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class JobAdderTest {

    @Rule
    public ActivityScenarioRule mJobDescriptionScreen = new ActivityScenarioRule(HomeScreen.class);
    private String Jerb = "Take care of my Dog";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void jobDisplay()
    {
        Espresso.onView(withId(R.id.toJobBoardButton)).perform(click());
        Espresso.onView(withId(R.id.JobPostingEntry)).perform(typeText(Jerb));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.JobTypeSpinner)).perform(click());
        Espresso.onData(allOf(is(instanceOf(String.class)), is("Pet Sitting"))).perform(click());
        Espresso.onView(withId(R.id.JobRadiusSpinner)).perform(click());
        Espresso.onData(allOf(is(instanceOf(String.class)), is("Less than 30 miles"))).perform(click());
        Espresso.onView(withId(R.id.AddJobButton)).perform(click());
        Espresso.pressBack();
        Espresso.onView(withId(R.id.toPostedButton)).perform(click());
    }


    @After
    public void tearDown() throws Exception {
    }
}