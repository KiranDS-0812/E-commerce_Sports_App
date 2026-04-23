package dsg.tests.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import dsg.tests.pages.WishlistPage
import android.util.Log
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class WishlistFlowTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    private lateinit var wishlistPage: WishlistPage

    @Before
    fun setUp() {
        wishlistPage = WishlistPage()
        Log.d("WishlistFlowTest", "setup complete")
    }

    @Test
    fun WL_001_verifyProductSavedToWishlist() {
        Log.d("WishlistFlowTest", "Test WL_001 started")
        wishlistPage.clickWishlistNavIcon()
        wishlistPage.verifyProductInWishlist()
        onView(withText(WishlistPage.WISHLIST_TITLE)).check(matches(isDisplayed()))
        Log.d("WishlistFlowTest", "Test WL_001 completed successfully")
    }

    @Test
    fun WL_002_verifyWishlistEmptyAfterRemove() {
        Log.d("WishlistFlowTest", "Test WL_002 started")
        wishlistPage.clickWishlistNavIcon()
        wishlistPage.removeFirstWishlistItem()
        wishlistPage.verifyWishlistIsEmpty()
        Log.d("WishlistFlowTest", "Test WL_002 completed successfully")
    }
}