package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

public class JokeTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void getJoke(){
        MainActivity.JokeBackendAsyncTask jokeBackendAsyncTask =
                activityTestRule.getActivity().new JokeBackendAsyncTask();
        jokeBackendAsyncTask.execute(activityTestRule.getActivity());
    }
}
