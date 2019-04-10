package com.example.codelabs.view;

import android.content.Intent;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.codelabs.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {
    private String username = "TheDancerCodes";
    private String url = "https://avatars0.githubusercontent.com/u/6739804?v=4";

    @Rule
    public IntentsTestRule<DetailActivity> detailActivityIntentsTestRule = new IntentsTestRule<>(DetailActivity.class,
            true, false);

    @Before
    public void startActivity() {
        Intent intent = new Intent();
        intent.putExtra("Username", username);
        intent.putExtra("ProfileImage", url);
        detailActivityIntentsTestRule.launchActivity(intent);
    }

    @Test
    public void detailActivity_isRendered() {
        registerIdlingResource();
        onView(withId(R.id.detail)).check(matches(isDisplayed()));
    }

    public void registerIdlingResource(){
        IdlingRegistry.getInstance().register( detailActivityIntentsTestRule.getActivity().getCountingIdlingResource());
    }

    @After
    public void unregisterIdlingResource(){
        IdlingRegistry.getInstance().unregister(detailActivityIntentsTestRule.getActivity().getCountingIdlingResource());
    }

}
