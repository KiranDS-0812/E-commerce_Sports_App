package dsg.tests.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.equalTo

class ShopPage {

    fun waitForFullHomePageLoad() {
        onView(isRoot()).perform(waitUntilVisible(withTagValue(equalTo("navigation_cart")), 10000))
    }

    fun clickSearchBar() {
        onView(withTagValue(equalTo("search_bar"))).perform(click())
    }
}