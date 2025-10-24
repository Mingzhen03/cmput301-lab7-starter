package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.hasToString;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> rule =
            new ActivityScenarioRule<>(MainActivity.class);


    private void addCity(String name) {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText(name), closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());
    }


    @Test
    public void clickingListItem_opensDetailScreen() {
        addCity("Edmonton");
        onData(hasToString("Edmonton"))
                .inAdapterView(withId(R.id.city_list))
                .perform(click());

        onView(withId(R.id.text_city)).check(matches(isDisplayed()));
    }


    @Test
    public void selectedCityName_isConsistent() {
        addCity("Calgary");
        onData(hasToString("Calgary"))
                .inAdapterView(withId(R.id.city_list))
                .perform(click());

        onView(withId(R.id.text_city)).check(matches(withText("Calgary")));
    }


    @Test
    public void backButton_returnsToMain() {
        addCity("Vancouver");
        onData(hasToString("Vancouver"))
                .inAdapterView(withId(R.id.city_list))
                .perform(click());

        onView(withId(R.id.button_back)).perform(click());
        onView(withId(R.id.city_list)).check(matches(isDisplayed()));
    }
}

