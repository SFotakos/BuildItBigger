package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.IsNot.not;

public class JokeTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void getJoke() throws InterruptedException {
        // Implemented CountDownLatch to wait for AsyncTask as shown in
        // https://stackoverflow.com/a/5722193  (09/05/2018)
        final CountDownLatch signal = new CountDownLatch(1);

        JokeBackendAsyncTask jokeBackendAsyncTask = new JokeBackendAsyncTask();
        jokeBackendAsyncTask.execute(new JokeBackendAsyncTask.IAPICallback() {
            @Override
            public void displayJoke(String joke) {
                assertThat(joke, not(isEmptyOrNullString()));
                signal.countDown();
            }
        });

        signal.await(30, TimeUnit.SECONDS);
    }
}
