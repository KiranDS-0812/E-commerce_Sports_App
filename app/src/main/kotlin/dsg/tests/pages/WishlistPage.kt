package dsg.tests.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.contrib.RecyclerViewActions
import android.util.Log
import org.junit.Assert

class WishlistPage {

    companion object {
        const val WISHLIST_TITLE = "Wishlist"
        const val ADD_TO_WISHLIST_BUTTON = "addToWishlistButton"
        const val WISHLIST_ITEM_ROW = "wishlist_item_row"
        const val REMOVE_WISHLIST_ITEM = "removeWishlistItem"
        const val WISHLIST_NAV_ICON = "navigation_wishlist"
        const val EMPTY_WISHLIST_TEXT = "emptyWishlistText"
    }

    private fun safeAction(actionName: String, action: () -> Unit) {
        try {
            Log.d("WishlistPage", actionName)
            action()
        } catch (ex: Exception) {
            Log.e("WishlistPage", "$actionName failed: ${ex.message}")
            Assert.fail("Error in $actionName: ${ex.message}")
        }
    }

    fun clickAddToWishlist() = safeAction("clickAddToWishlist") {
        onView(withTagValue(equalTo(ADD_TO_WISHLIST_BUTTON))).perform(click())
    }

    fun clickWishlistNavIcon() = safeAction("clickWishlistNavIcon") {
        onView(withTagValue(equalTo(WISHLIST_NAV_ICON))).perform(click())
    }

    fun verifyProductInWishlist() = safeAction("verifyProductInWishlist") {
        onView(withTagValue(equalTo(WISHLIST_ITEM_ROW))).check(matches(isDisplayed()))
    }

    fun verifyWishlistIsEmpty() = safeAction("verifyWishlistIsEmpty") {
        onView(withTagValue(equalTo(EMPTY_WISHLIST_TEXT))).check(matches(isDisplayed()))
    }

    fun removeFirstWishlistItem() = safeAction("removeFirstWishlistItem") {
        onView(withTagValue(equalTo(WISHLIST_ITEM_ROW)))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<androidx.recyclerview.widget.RecyclerView.ViewHolder>(
                    0,
                    clickChildViewWithTag(REMOVE_WISHLIST_ITEM)
                )
            )
    }

    private fun clickChildViewWithTag(tag: String) = object : androidx.test.espresso.ViewAction {
        override fun getConstraints() = null
        override fun getDescription() = "Click on child view with tag: $tag"
        override fun perform(uiController: androidx.test.espresso.UiController?, view: android.view.View?) {
            val child = view?.findViewWithTag<android.view.View>(tag)
            child?.performClick() ?: throw AssertionError("Child view with tag $tag not found")
        }
    }
}