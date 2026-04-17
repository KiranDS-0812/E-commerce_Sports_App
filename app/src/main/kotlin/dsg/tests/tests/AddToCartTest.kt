package dsg.tests.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import dsg.tests.pages.*

@RunWith(AndroidJUnit4::class)
class AddToCartTest {

    private val onboardingPage = OnboardingPage()
    private val shopPage = ShopPage()
    private val searchPage = SearchPage()
    private val productListPage = ProductListPage()
    private val productPage = ProductPage()
    private val cartPage = CartPage()

    @Test
    fun testAddProductToCartFromSearch() {
        onboardingPage.clickContinueAsGuest()
        onboardingPage.handleLocationScreen(allow = false)
        onboardingPage.handleNotificationScreen(allow = false)

        shopPage.waitForFullHomePageLoad()
        shopPage.clickSearchBar()

        searchPage.enterSearchText("nike")
        searchPage.selectFirstSuggestion()

        productListPage.addFirstProductToCart()
        // Optionally: productPage.selectSizeIfPresent("M")

        productPage.clickAddToCart()
        productPage.clickViewCart()

        cartPage.verifyItemInCart()
    }
}