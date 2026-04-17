package dsg.tests.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.equalTo

class ProductListPage {

    fun addFirstProductToCart() {
        onView(withTagValue(equalTo("product_card_0"))).perform(click())
        // Optionally handle size selection if present
    }
}