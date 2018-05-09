package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.IsNot.not;

public class JokeTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void getJoke(){
        JokeBackendAsyncTask jokeBackendAsyncTask = new JokeBackendAsyncTask();
        jokeBackendAsyncTask.execute(new JokeBackendAsyncTask.IAPICallback() {
            @Override
            public void displayJoke(String joke) {
                assertThat(joke, not(isEmptyOrNullString()));
            }
        });
    }
}
