package com.example.espressosample;

import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.registerIdlingResources;
import static android.support.test.espresso.Espresso.unregisterIdlingResources;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Андрей on 21.06.2017.
 */

public class EspressoTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    private IdlingResource mIdlingResource;

    @Before
    public void setUp() {
        mIdlingResource = new IdlingResource() {
            private static final String IDLING_RESOURSE_NAME = "ChangeTextIdleResource";
            private ResourceCallback resourceCallBack;

            @Override
            public String getName() {
                return IDLING_RESOURSE_NAME;
            }

            @Override
            public boolean isIdleNow() {
                boolean isIdle = isLoaderRunning();
                if (isIdle && resourceCallBack != null) {
                    resourceCallBack.onTransitionToIdle();
                }
                return isIdle;
            }

            @Override
            public void registerIdleTransitionCallback(ResourceCallback callback) {
                this.resourceCallBack = callback;

            }

            private boolean isLoaderRunning() {
                return mActivityTestRule.getActivity().mIsLoaded;
            }
        };

    }


    @Test
    public void testChangingText() {
        onView(withId(R.id.textView)).check(matches(withText("Hello World!")));
        onView(withId(R.id.button)).perform(click());
        registerIdlingResources(mIdlingResource);
        onView(withId(R.id.textView)).check(matches(withText("Test String")));

    }

    @After
    public void unregister() {
        unregisterIdlingResources(mIdlingResource);
    }
}
