package dsg.tests.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.*

class ProductPage {

    fun selectSizeIfPresent(size: String) {
        try {
            onView(withTagValue(equalTo("size_selector"))).perform(click())
            onView(withText(size)).perform(click())
        } catch (e: NoMatchingViewException) {
            // Size selection not present, continue
        }
    }

    fun clickAddToCart() {
        onView(anyOf(withText("ADD TO CART"), withTagValue(equalTo("add_to_cart_button")))).perform(click())
    }

    fun clickViewCart() {
        onView(withTagValue(equalTo("viewCartButton"))).perform(click())
    }
}