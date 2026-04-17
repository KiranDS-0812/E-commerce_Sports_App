package dsg.tests.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.equalTo

class SearchPage {

    fun enterSearchText(query: String) {
        onView(withTagValue(equalTo("search_input"))).perform(typeText(query), closeSoftKeyboard())
    }

    fun selectFirstSuggestion() {
        onView(withTagValue(equalTo("search_suggestion_0"))).perform(click())
    }
}