package com.example.pokemonapp2022.presenter.home

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.pokemonapp2022.R

import org.junit.Test

class HomeFragmentTest {
    @Test
    fun testIfHasText() {
        val scenario = launchFragmentInContainer<HomeFragment>()
        onView(withId(R.id.mtv_title1)).check(matches(withText("Pokedex")))
    }
}

