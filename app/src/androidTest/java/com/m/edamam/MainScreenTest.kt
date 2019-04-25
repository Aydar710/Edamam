package com.m.edamam


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.m.edamam.ui.MainActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainScreenTest {

    @Rule
    @JvmField
    public val rule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testActivityLaunched() {
        onView(withId(R.id.btn_search_recipes)).check(matches(allOf(
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                isFocusable(),
                isClickable(),
                withText("Search"))))

        onView(withId(R.id.action_pagination_size)).check(matches(allOf(
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                isFocusable(),
                isClickable(),
                withText("Pagination Size"))))
    }
}
