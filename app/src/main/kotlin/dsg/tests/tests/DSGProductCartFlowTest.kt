package dsg.tests.tests

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import dsg.tests.pages.DSGProductCartFlowPage
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.util.Log

/**
 * Test class for DSG Product Cart Flow
 * Contains comprehensive test scenarios for cart functionality including:
 * - Adding products to cart
 * - Updating product quantities
 * - Removing products from cart
 * - Applying promo codes
 * - Proceeding to checkout
 * - Verifying cart totals and empty cart states
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class DSGProductCartFlowTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var cartFlowPage: DSGProductCartFlowPage
    private val TAG = "DSGProductCartFlowTest"

    @Before
    fun setup() {
        Log.d(TAG, "Setting up test environment")
        cartFlowPage = DSGProductCartFlowPage(composeTestRule)
        Thread.sleep(2000) // Wait for app to initialize
    }

    /**
     * Test Case: TC_CART_001
     * Verify user can add a product to cart successfully
     */
    @Test
    fun testAddProductToCart() {
        Log.d(TAG, "Starting testAddProductToCart")
        
        // Search for a product
        cartFlowPage.searchProduct("Basketball")
        
        // Select first product from results
        cartFlowPage.selectFirstProduct()
        
        // Add product to cart
        cartFlowPage.addToCart()
        
        // Navigate to cart
        cartFlowPage.navigateToCart()
        
        // Verify product is in cart
        cartFlowPage.verifyProductInCart("Basketball")
        
        Log.d(TAG, "testAddProductToCart completed successfully")
    }

    /**
     * Test Case: TC_CART_002
     * Verify user can update product quantity in cart
     */
    @Test
    fun testUpdateProductQuantity() {
        Log.d(TAG, "Starting testUpdateProductQuantity")
        
        // Add product to cart first
        cartFlowPage.searchProduct("Soccer Ball")
        cartFlowPage.selectFirstProduct()
        cartFlowPage.addToCart()
        
        // Navigate to cart
        cartFlowPage.navigateToCart()
        cartFlowPage.waitForCartToLoad()
        
        // Update quantity to 3
        cartFlowPage.updateProductQuantity("Soccer Ball", 3)
        
        // Verify updated quantity
        cartFlowPage.verifyProductQuantity("Soccer Ball", 3)
        
        Log.d(TAG, "testUpdateProductQuantity completed successfully")
    }

    /**
     * Test Case: TC_CART_003
     * Verify user can remove a product from cart
     */
    @Test
    fun testRemoveProductFromCart() {
        Log.d(TAG, "Starting testRemoveProductFromCart")
        
        // Add product to cart
        cartFlowPage.searchProduct("Tennis Racket")
        cartFlowPage.selectFirstProduct()
        cartFlowPage.addToCart()
        
        // Navigate to cart
        cartFlowPage.navigateToCart()
        cartFlowPage.waitForCartToLoad()
        
        // Remove product from cart
        cartFlowPage.removeProductFromCart("Tennis Racket")
        
        // Verify cart is empty
        cartFlowPage.verifyEmptyCart()
        
        Log.d(TAG, "testRemoveProductFromCart completed successfully")
    }

    /**
     * Test Case: TC_CART_004
     * Verify user can apply promo code to cart
     */
    @Test
    fun testApplyPromoCode() {
        Log.d(TAG, "Starting testApplyPromoCode")
        
        // Add product to cart
        cartFlowPage.searchProduct("Running Shoes")
        cartFlowPage.selectFirstProduct()
        cartFlowPage.addToCart()
        
        // Navigate to cart
        cartFlowPage.navigateToCart()
        cartFlowPage.waitForCartToLoad()
        
        // Apply promo code
        val promoCode = "SAVE20"
        cartFlowPage.applyPromoCode(promoCode)
        
        // Verify promo code applied
        cartFlowPage.verifyPromoCodeApplied(promoCode)
        
        Log.d(TAG, "testApplyPromoCode completed successfully")
    }

    /**
     * Test Case: TC_CART_005
     * Verify user can proceed to checkout from cart
     */
    @Test
    fun testProceedToCheckout() {
        Log.d(TAG, "Starting testProceedToCheckout")
        
        // Add product to cart
        cartFlowPage.searchProduct("Baseball Glove")
        cartFlowPage.selectFirstProduct()
        cartFlowPage.addToCart()
        
        // Navigate to cart
        cartFlowPage.navigateToCart()
        cartFlowPage.waitForCartToLoad()
        
        // Proceed to checkout
        cartFlowPage.proceedToCheckout()
        
        Log.d(TAG, "testProceedToCheckout completed successfully")
    }

    /**
     * Test Case: TC_CART_006
     * Verify cart total is calculated correctly
     */
    @Test
    fun testVerifyCartTotal() {
        Log.d(TAG, "Starting testVerifyCartTotal")
        
        // Add product to cart
        cartFlowPage.searchProduct("Golf Clubs")
        cartFlowPage.selectFirstProduct()
        cartFlowPage.addToCart()
        
        // Navigate to cart
        cartFlowPage.navigateToCart()
        cartFlowPage.waitForCartToLoad()
        
        // Verify cart total (this would need to be dynamic based on actual product price)
        // For demo purposes, using a placeholder
        cartFlowPage.verifyCartTotal("$")
        
        Log.d(TAG, "testVerifyCartTotal completed successfully")
    }

    /**
     * Test Case: TC_CART_007
     * Verify user can save cart for later
     */
    @Test
    fun testSaveCartForLater() {
        Log.d(TAG, "Starting testSaveCartForLater")
        
        // Add product to cart
        cartFlowPage.searchProduct("Yoga Mat")
        cartFlowPage.selectFirstProduct()
        cartFlowPage.addToCart()
        
        // Navigate to cart
        cartFlowPage.navigateToCart()
        cartFlowPage.waitForCartToLoad()
        
        // Save cart for later
        cartFlowPage.saveCartForLater()
        
        Log.d(TAG, "testSaveCartForLater completed successfully")
    }

    /**
     * Test Case: TC_CART_008
     * Verify user can continue shopping from cart
     */
    @Test
    fun testContinueShopping() {
        Log.d(TAG, "Starting testContinueShopping")
        
        // Navigate to cart
        cartFlowPage.navigateToCart()
        cartFlowPage.waitForCartToLoad()
        
        // Continue shopping
        cartFlowPage.continueShopping()
        
        Log.d(TAG, "testContinueShopping completed successfully")
    }

    /**
     * Test Case: TC_CART_009
     * Verify empty cart state is displayed correctly
     */
    @Test
    fun testEmptyCartState() {
        Log.d(TAG, "Starting testEmptyCartState")
        
        // Navigate to cart
        cartFlowPage.navigateToCart()
        cartFlowPage.waitForCartToLoad()
        
        // Verify empty cart message
        cartFlowPage.verifyEmptyCart()
        
        Log.d(TAG, "testEmptyCartState completed successfully")
    }

    /**
     * Test Case: TC_CART_010
     * End-to-end cart flow test
     */
    @Test
    fun testCompleteCartFlow() {
        Log.d(TAG, "Starting testCompleteCartFlow")
        
        // Search and add first product
        cartFlowPage.searchProduct("Basketball Shoes")
        cartFlowPage.selectFirstProduct()
        cartFlowPage.addToCart()
        
        // Continue shopping and add second product
        cartFlowPage.continueShopping()
        cartFlowPage.searchProduct("Water Bottle")
        cartFlowPage.selectFirstProduct()
        cartFlowPage.addToCart()
        
        // Navigate to cart
        cartFlowPage.navigateToCart()
        cartFlowPage.waitForCartToLoad()
        
        // Verify both products in cart
        cartFlowPage.verifyProductInCart("Basketball Shoes")
        cartFlowPage.verifyProductInCart("Water Bottle")
        
        // Update quantity of first product
        cartFlowPage.updateProductQuantity("Basketball Shoes", 2)
        
        // Apply promo code
        cartFlowPage.applyPromoCode("SPORTS10")
        cartFlowPage.verifyPromoCodeApplied("SPORTS10")
        
        // Proceed to checkout
        cartFlowPage.proceedToCheckout()
        
        Log.d(TAG, "testCompleteCartFlow completed successfully")
    }
}