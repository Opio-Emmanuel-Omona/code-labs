package com.example.codelabs.view;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.codelabs.R;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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
    static  WifiManager wifi;

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

    @BeforeClass
    public static void turnOffData() {
        wifi = (WifiManager) InstrumentationRegistry.getInstrumentation().getTargetContext().getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(false);
    }

    @Test
    public void aSnackbarIsDisplayedWhenWifiIsOff() {
        turnOffData();
        onView(withId(R.id.detail)).check(matches(isDisplayed()));
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
        wifi.setWifiEnabled(true);
        IdlingRegistry.getInstance().unregister(detailActivityIntentsTestRule.getActivity().getCountingIdlingResource());
    }

}
