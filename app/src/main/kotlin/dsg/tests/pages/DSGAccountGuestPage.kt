// DSGAccountGuestPage.kt
package dsg.tests.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dsg.automation.audit.AuditLogger
import org.junit.Assert

/**
 * DSG Account Guest PageObject
 * Enterprise-grade: includes audit logging, error handling, and compliance annotations.
 */
class DSGAccountGuestPage {

    // --- Identifiers (testTags or resource IDs) ---
    val myFavoritesButton = onView(withTagValue(`is`("accountMyFavoritesButton" as Any)))
    val trackOrdersButton = onView(withTagValue(`is`("accountTrackOrdersButton" as Any)))
    val myStoreButton = onView(withTagValue(`is`("accountMyStoreButton" as Any)))
    val scoreRewardsCardButton = onView(withTagValue(`is`("accountScoreRewardsCardButton" as Any)))
    val settingsButton = onView(withTagValue(`is`("accountSettingsButton" as Any)))
    val moreButton = onView(withTagValue(`is`("accountMoreButton" as Any)))
    val backButton = onView(withContentDescription("Navigate up"))
    val closeWebViewButton = onView(withTagValue(`is`("trackOrdersWebViewClose" as Any)))
    val storeList = onView(withTagValue(`is`("storeListRecyclerView" as Any)))
    val setStoreButton = onView(withTagValue(`is`("setStoreButton" as Any)))
    val selectedStoreLabel = onView(withTagValue(`is`("selectedStoreLabel" as Any)))
    val scoreRewardsScreen = onView(withTagValue(`is`("scoreRewardsScreen" as Any)))
    val accountGuestView = onView(withTagValue(`is`("accountGuestView" as Any)))
    val settingsScreen = onView(withTagValue(`is`("settingsScreen" as Any)))
    val moreScreen = onView(withTagValue(`is`("moreScreen" as Any)))

    // --- Methods for UI interactions ---

    fun tapMyFavorites() {
        AuditLogger.log("Tap My Favorites")
        myFavoritesButton.perform(click())
    }

    fun validateMyFavoritesScreen() {
        AuditLogger.log("Validate My Favorites Screen")
        onView(withTagValue(`is`("myFavoritesScreen" as Any))).check(matches(isDisplayed()))
    }

    fun tapBack() {
        AuditLogger.log("Tap Back")
        backButton.perform(click())
    }

    fun tapTrackOrders() {
        AuditLogger.log("Tap Track Orders")
        trackOrdersButton.perform(click())
    }

    fun validateTrackOrdersScreen() {
        AuditLogger.log("Validate Track Orders Screen")
        onView(withTagValue(`is`("trackOrdersWebView" as Any))).check(matches(isDisplayed()))
    }

    fun closeTrackOrdersWebView() {
        AuditLogger.log("Close Track Orders WebView")
        closeWebViewButton.perform(click())
    }

    fun tapMyStore() {
        AuditLogger.log("Tap My Store")
        myStoreButton.perform(click())
    }

    fun validateStoreList() {
        AuditLogger.log("Validate Store List appears")
        storeList.check(matches(isDisplayed()))
    }

    fun selectStoreAtIndex(index: Int) {
        AuditLogger.log("Select Store at index $index")
        onView(withRecyclerView("storeListRecyclerView").atPosition(index)).perform(click())
    }

    fun tapSetStore() {
        AuditLogger.log("Tap Set Store")
        setStoreButton.perform(click())
    }

    fun validateSelectedStoreDisplayed() {
        AuditLogger.log("Validate selected store is displayed")
        selectedStoreLabel.check(matches(isDisplayed()))
    }

    fun tapScoreRewardsCard() {
        AuditLogger.log("Tap ScoreRewards Credit Card")
        scoreRewardsCardButton.perform(click())
    }

    fun validateScoreRewardsScreen() {
        AuditLogger.log("Validate ScoreRewards Credit Card screen")
        scoreRewardsScreen.check(matches(isDisplayed()))
    }

    fun tapSettings() {
        AuditLogger.log("Tap Settings")
        settingsButton.perform(click())
    }

    fun validateSettingsScreen() {
        AuditLogger.log("Validate Settings screen")
        settingsScreen.check(matches(isDisplayed()))
    }

    fun tapMore() {
        AuditLogger.log("Tap More")
        moreButton.perform(click())
    }

    fun validateMoreScreen() {
        AuditLogger.log("Validate More screen")
        moreScreen.check(matches(isDisplayed()))
    }

    fun validateAccountGuestView() {
        AuditLogger.log("Validate Account Guest View")
        accountGuestView.check(matches(isDisplayed()))
    }

    // --- Utility for RecyclerView selection ---
    fun withRecyclerView(recyclerViewTag: String): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewTag)
    }
}