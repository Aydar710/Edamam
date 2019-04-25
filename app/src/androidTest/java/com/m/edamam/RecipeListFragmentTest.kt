package com.m.edamam

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.ui.MainActivity
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

@RunWith(AndroidJUnit4::class)
class RecipeListFragmentTest {

    @Mock
    private lateinit var mockRepository: RecipeRepository

    @Rule
    @JvmField
    public val rule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        onView(withId(R.id.btn_search_recipes)).perform(click())
    }

    @Test
    fun testRecipeListFragment() {


        onView(withId(R.id.recycler_recipes)).check(matches(not(isDisplayed())))

        onView(withId(R.id.action_search))
                .check(matches(isDisplayed()))

        onView(withId(R.id.action_search))
                .perform(click())

        onView(withId(R.id.action_search))
                .perform(typeText("ch"))


    }
}
