package dsg.tests.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.*

class CartPage {

    fun verifyItemInCart() {
        onView(anyOf(
            withText("Cart"),
            withText("CHECKOUT"),
            withTagValue(equalTo("ProgressIndicatorButton enabled")),
            withTagValue(equalTo("productImage"))
        )).check(matches(isDisplayed()))
    }

    fun removeItem() {
        onView(withTagValue(equalTo("remove_button"))).perform(click())
    }

    fun verifyCartEmpty() {
        onView(withTagValue(equalTo("order_summary_list"))).check(matches(not(isDisplayed())))
    }
}