package com.edisiswanto.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.edisiswanto.moviecatalogue.R
import org.junit.Rule
import org.junit.Test
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.rule.ActivityTestRule
import com.edisiswanto.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before

class HomeActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.detail_content_movie)).check(matches(isDisplayed()))

        onView(withId(R.id.img_poster)).perform(ViewActions.swipeUp()).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).perform(ViewActions.swipeUp()).check(matches(isDisplayed()))

        onView(withId(R.id.tv_overview)).perform(ViewActions.swipeUp()).check(matches(isDisplayed()))
        onView(withId(R.id.release)).perform(ViewActions.swipeUp()).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_date)).perform(ViewActions.swipeUp()).check(matches(isDisplayed()))
        onView(withId(R.id.rating)).perform(ViewActions.swipeUp()).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_score)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTv() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
    }

    @Test
    fun loadDetailTv() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.detail_content_tv)).check(matches(isDisplayed()))

        onView(withId(R.id.img_poster_tv)).perform(ViewActions.swipeUp()).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_tv)).perform(ViewActions.swipeUp()).check(matches(isDisplayed()))

        onView(withId(R.id.tv_overview_tv)).perform(ViewActions.swipeUp()).check(matches(isDisplayed()))
        onView(withId(R.id.release_tv)).perform(ViewActions.swipeUp()).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_date_tv)).perform(ViewActions.swipeUp()).check(matches(isDisplayed()))
        onView(withId(R.id.rating_tv)).perform(ViewActions.swipeUp()).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_score_tv)).check(matches(isDisplayed()))
    }
}