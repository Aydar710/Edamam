package com.m.edamam

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.m.edamam.ui.MainActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsFragmentTest {

    @Rule
    @JvmField
    public val rule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun DetailsFragmentIsReady(){
        onView(ViewMatchers.withId(R.id.txt_label)).check(ViewAssertions.matches(Matchers.allOf(
                ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                ViewMatchers.isFocusable(),
                ViewMatchers.isClickable())))

        onView(ViewMatchers.withId(R.id.txt_ingredients_rec)).check(ViewAssertions.matches(Matchers.allOf(
                ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                ViewMatchers.isFocusable(),
                ViewMatchers.isClickable())))

        onView(ViewMatchers.withId(R.id.img_recipe_details)).check(ViewAssertions.matches(Matchers.allOf(
                ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                ViewMatchers.isFocusable(),
                ViewMatchers.isClickable())))

        onView(ViewMatchers.withId(R.id.txt_ingredients)).check(ViewAssertions.matches(Matchers.allOf(
                ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                ViewMatchers.isFocusable(),
                ViewMatchers.isClickable())))
    }
}