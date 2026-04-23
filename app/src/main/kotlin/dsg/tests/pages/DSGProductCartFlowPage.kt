package dsg.tests.pages

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import android.util.Log

/**
 * Page Object class for DSG Product Cart Flow automation
 * Handles all cart-related interactions including adding products, updating quantities,
 * and proceeding through checkout flow
 * 
 * @property composeTestRule The ComposeTestRule instance for UI testing
 */
class DSGProductCartFlowPage(private val composeTestRule: ComposeTestRule) {

    private val TAG = "DSGProductCartFlowPage"
    private val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    private val defaultTimeout = 10000L

    /**
     * Navigates to the cart screen from the home page
     */
    fun navigateToCart() {
        Log.d(TAG, "Navigating to cart")
        composeTestRule.waitForIdle()
        
        // Click on cart icon in navigation bar
        composeTestRule.onNode(
            hasContentDescription("Cart") or hasText("Cart")
        ).performClick()
        
        composeTestRule.waitForIdle()
        Thread.sleep(2000) // Wait for cart to load
    }

    /**
     * Searches for a product by name
     * @param productName The name of the product to search for
     */
    fun searchProduct(productName: String) {
        Log.d(TAG, "Searching for product: $productName")
        composeTestRule.waitForIdle()
        
        // Click on search icon/field
        composeTestRule.onNode(
            hasContentDescription("Search") or hasText("Search")
        ).performClick()
        
        composeTestRule.waitForIdle()
        
        // Enter product name in search field
        composeTestRule.onNodeWithTag("searchTextField")
            .performTextInput(productName)
        
        composeTestRule.waitForIdle()
        Thread.sleep(2000) // Wait for search results
    }

    /**
     * Selects the first product from search results
     */
    fun selectFirstProduct() {
        Log.d(TAG, "Selecting first product from results")
        composeTestRule.waitForIdle()
        
        // Click on first product in the list
        device.wait(Until.hasObject(By.clickable(true)), defaultTimeout)
        val firstProduct = device.findObject(By.clickable(true))
        firstProduct?.click()
        
        composeTestRule.waitForIdle()
        Thread.sleep(2000) // Wait for product details to load
    }

    /**
     * Adds the current product to cart
     */
    fun addToCart() {
        Log.d(TAG, "Adding product to cart")
        composeTestRule.waitForIdle()
        
        // Scroll to Add to Cart button if needed
        composeTestRule.onNode(
            hasText("Add to Cart", ignoreCase = true) or 
            hasContentDescription("Add to Cart")
        ).performScrollTo().performClick()
        
        composeTestRule.waitForIdle()
        Thread.sleep(2000) // Wait for cart update
    }

    /**
     * Verifies that a product is present in the cart
     * @param productName The name of the product to verify
     */
    fun verifyProductInCart(productName: String) {
        Log.d(TAG, "Verifying product in cart: $productName")
        composeTestRule.waitForIdle()
        
        composeTestRule.onNodeWithText(
            productName,
            substring = true,
            ignoreCase = true
        ).assertIsDisplayed()
    }

    /**
     * Updates the quantity of a product in the cart
     * @param productName The name of the product
     * @param quantity The new quantity value
     */
    fun updateProductQuantity(productName: String, quantity: Int) {
        Log.d(TAG, "Updating quantity for $productName to $quantity")
        composeTestRule.waitForIdle()
        
        // Find quantity controls for the specific product
        val quantityField = device.findObject(
            By.res("quantity_input").text("1")
        )
        
        if (quantity > 1) {
            // Click increase button multiple times
            repeat(quantity - 1) {
                composeTestRule.onNode(
                    hasContentDescription("Increase quantity") or hasText("+")
                ).performClick()
                Thread.sleep(500)
            }
        }
        
        composeTestRule.waitForIdle()
        Thread.sleep(1000) // Wait for quantity update
    }

    /**
     * Removes a product from the cart
     * @param productName The name of the product to remove
     */
    fun removeProductFromCart(productName: String) {
        Log.d(TAG, "Removing product from cart: $productName")
        composeTestRule.waitForIdle()
        
        // Find and click remove button for the product
        composeTestRule.onNode(
            hasContentDescription("Remove $productName") or 
            hasText("Remove", ignoreCase = true)
        ).performClick()
        
        composeTestRule.waitForIdle()
        Thread.sleep(1000) // Wait for removal
    }

    /**
     * Proceeds to checkout from the cart
     */
    fun proceedToCheckout() {
        Log.d(TAG, "Proceeding to checkout")
        composeTestRule.waitForIdle()
        
        // Scroll to checkout button and click
        composeTestRule.onNode(
            hasText("Checkout", ignoreCase = true) or 
            hasText("Proceed to Checkout", ignoreCase = true) or
            hasContentDescription("Checkout")
        ).performScrollTo().performClick()
        
        composeTestRule.waitForIdle()
        Thread.sleep(2000) // Wait for checkout screen
    }

    /**
     * Verifies the cart total amount
     * @param expectedTotal The expected total amount as a string (e.g., "$99.99")
     */
    fun verifyCartTotal(expectedTotal: String) {
        Log.d(TAG, "Verifying cart total: $expectedTotal")
        composeTestRule.waitForIdle()
        
        composeTestRule.onNodeWithText(
            expectedTotal,
            substring = true
        ).assertIsDisplayed()
    }

    /**
     * Verifies that the cart is empty
     */
    fun verifyEmptyCart() {
        Log.d(TAG, "Verifying empty cart")
        composeTestRule.waitForIdle()
        
        composeTestRule.onNode(
            hasText("Your cart is empty", ignoreCase = true) or
            hasText("No items in cart", ignoreCase = true) or
            hasContentDescription("Empty cart")
        ).assertIsDisplayed()
    }

    /**
     * Applies a promo code to the cart
     * @param promoCode The promo code to apply
     */
    fun applyPromoCode(promoCode: String) {
        Log.d(TAG, "Applying promo code: $promoCode")
        composeTestRule.waitForIdle()
        
        // Find and click promo code field
        composeTestRule.onNode(
            hasText("Promo Code", ignoreCase = true) or
            hasContentDescription("Promo code input")
        ).performClick()
        
        // Enter promo code
        composeTestRule.onNodeWithTag("promoCodeInput")
            .performTextInput(promoCode)
        
        // Click apply button
        composeTestRule.onNode(
            hasText("Apply", ignoreCase = true) or
            hasContentDescription("Apply promo code")
        ).performClick()
        
        composeTestRule.waitForIdle()
        Thread.sleep(2000) // Wait for promo code application
    }

    /**
     * Verifies that a promo code was successfully applied
     * @param promoCode The promo code that was applied
     */
    fun verifyPromoCodeApplied(promoCode: String) {
        Log.d(TAG, "Verifying promo code applied: $promoCode")
        composeTestRule.waitForIdle()
        
        composeTestRule.onNode(
            hasText("Promo code applied", ignoreCase = true) or
            hasText(promoCode, substring = true)
        ).assertIsDisplayed()
    }

    /**
     * Saves cart for later
     */
    fun saveCartForLater() {
        Log.d(TAG, "Saving cart for later")
        composeTestRule.waitForIdle()
        
        composeTestRule.onNode(
            hasText("Save for Later", ignoreCase = true) or
            hasContentDescription("Save cart")
        ).performClick()
        
        composeTestRule.waitForIdle()
        Thread.sleep(1000)
    }

    /**
     * Continues shopping from cart
     */
    fun continueShopping() {
        Log.d(TAG, "Continuing shopping")
        composeTestRule.waitForIdle()
        
        composeTestRule.onNode(
            hasText("Continue Shopping", ignoreCase = true) or
            hasContentDescription("Continue shopping")
        ).performClick()
        
        composeTestRule.waitForIdle()
        Thread.sleep(1000)
    }

    /**
     * Verifies product quantity in cart
     * @param productName The name of the product
     * @param expectedQuantity The expected quantity
     */
    fun verifyProductQuantity(productName: String, expectedQuantity: Int) {
        Log.d(TAG, "Verifying quantity for $productName: $expectedQuantity")
        composeTestRule.waitForIdle()
        
        composeTestRule.onNodeWithText(
            expectedQuantity.toString()
        ).assertIsDisplayed()
    }

    /**
     * Waits for cart to load completely
     */
    fun waitForCartToLoad() {
        Log.d(TAG, "Waiting for cart to load")
        composeTestRule.waitForIdle()
        Thread.sleep(3000)
        
        device.wait(Until.hasObject(By.text("Cart")), defaultTimeout)
    }

    /**
     * Helper function to wait for idle state
     */
    private fun ComposeTestRule.waitForIdle() {
        this.waitForIdle()
    }
}