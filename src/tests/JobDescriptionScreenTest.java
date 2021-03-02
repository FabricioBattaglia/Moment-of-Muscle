package com.example.mom;

import org.junit.After;
import org.junit.Before;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class JobDescriptionScreenTest {

    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule(JobDescriptionScreen.class);
    private String job = "builder - $30.00";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void jobPost()
    {
        Espresso.onView(withId(R.id.JobPostingEntry)).perform(ViewActions.typeText(job));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.AddJobButton)).perform(click());
        Espresso.onView(withId(R.id.JobBoard)).check(matches(withText(job)));

    }

    @After
    public void tearDown() throws Exception {
    }
}
