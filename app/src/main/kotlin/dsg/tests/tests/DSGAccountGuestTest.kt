// DSGAccountGuestTest.kt
package dsg.tests.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import dsg.tests.pages.DSGAccountGuestPage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Automated test for DSG Account Guest flows.
 * Includes audit logging and error handling.
 */
@RunWith(AndroidJUnit4::class)
class DSGAccountGuestTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    private val accountPage = DSGAccountGuestPage()

    @Test
    fun testAccountGuestFlows() {
        // My Favorites flow
        accountPage.tapMyFavorites()
        accountPage.validateMyFavoritesScreen()
        accountPage.tapBack()
        accountPage.validateAccountGuestView()

        // Track Orders flow
        accountPage.tapTrackOrders()
        accountPage.validateTrackOrdersScreen()
        accountPage.closeTrackOrdersWebView()
        accountPage.validateAccountGuestView()

        // My Store flow
        accountPage.tapMyStore()
        accountPage.validateStoreList()
        accountPage.selectStoreAtIndex(0)
        accountPage.tapSetStore()
        accountPage.validateSelectedStoreDisplayed()
        accountPage.validateAccountGuestView()

        // ScoreRewards Credit Card flow
        accountPage.tapScoreRewardsCard()
        accountPage.validateScoreRewardsScreen()
        accountPage.tapBack()
        accountPage.validateAccountGuestView()

        // Settings flow
        accountPage.tapSettings()
        accountPage.validateSettingsScreen()
        accountPage.tapBack()
        accountPage.validateAccountGuestView()

        // More flow
        accountPage.tapMore()
        accountPage.validateMoreScreen()
        accountPage.validateAccountGuestView()
    }
}