package com.m.edamam

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.m.edamam.ui.MainActivity
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecipeListFragmentTest {

    @Rule
    @JvmField
    public val rule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        onView(withId(R.id.btn_search_recipes)).perform(click())
    }

    @Test
    fun whenRecipeListFragmentStarted() {


        onView(withId(R.id.recycler_recipes)).check(matches(not(isDisplayed())))

        onView(withId(R.id.action_search))
                .check(matches(isDisplayed()))
                .perform(click())
                .perform(typeText("chicken"), closeSoftKeyboard())
    }

    @Test
    fun testRecyclerView() {
        onView(withId(R.id.recycler_recipes)).check(matches(isDisplayed()))

        // :(
        onView(withId(R.id.recycler_recipes))
                //.perform(scrollToPosition(4))
    }

    @Test
    fun testRecyclerViewClicks() {
        // :-<
        onView(withId(R.id.recycler_recipes))
                //.perform(actionOnItemAtPosition(3, click()))
    }
}
