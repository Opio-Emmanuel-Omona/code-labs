package com.example.codelabs.view;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.wifi.WifiManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import android.support.test.runner.AndroidJUnit4;

import com.example.codelabs.R;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    static  WifiManager wifi;

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @BeforeClass
    public static void turnOffData() {
        wifi = (WifiManager) InstrumentationRegistry.getInstrumentation().getTargetContext().getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(false);
    }

    @Test
    public void aSnackbarIsDisplayedWhenWifiIsOff() {
        turnOffData();
        onView(withId(R.id.my_recycler_view)).check(matches(isDisplayed()));
    }

    @Test
    public void recyclerView_isRendered() {
        registerIdlingResource();
        onView(withId(R.id.my_recycler_view)).check(matches(isDisplayed()));
    }

    @Test
    public void screenSwipedDown_CreatesSwipeToRefreshIcon() {
        registerIdlingResource();
        onView(withId(R.id.my_recycler_view)).perform(swipeDown());
        onView(withId(R.id.swipeToRefresh)).check(matches(isDisplayed()));
    }

    @Test
    public void orientationChangedToLandscape() {
        registerIdlingResource();
        mainActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Test
    public void clickItem() {
        registerIdlingResource();
        onView(withId(R.id.my_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    public void registerIdlingResource(){
        IdlingRegistry.getInstance().register( mainActivityTestRule.getActivity().getCountingIdlingResource());
    }

    @After
    public void unregisterIdlingResource(){
        wifi.setWifiEnabled(true);
        IdlingRegistry.getInstance().unregister(mainActivityTestRule.getActivity().getCountingIdlingResource());
    }

}
