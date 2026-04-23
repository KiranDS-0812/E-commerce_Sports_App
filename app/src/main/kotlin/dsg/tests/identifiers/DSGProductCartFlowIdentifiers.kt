package dsg.tests.identifiers

import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matcher
import android.view.View

/**
 * Enterprise-grade Identifier Mapping Object for DSG Product Cart Flow
 * Contains all UI element identifiers with their corresponding Espresso matchers
 * @author DevOps Automation Team
 * @version 1.0.0
 * @compliance Centralized identifier management for maintainability
 */
object DSGProductCartFlowIdentifiers {

    /**
     * Cart Title identifier
     * Used to verify the cart page title
     */
    val CART_TITLE: Matcher<View> = allOf(
        withId(R.id.cart_title),
        withText("Shopping Cart"),
        isDisplayed()
    )

    /**
     * Order Number identifier
     * Used to verify order ID in cart
     */
    val ORDER_NUMBER: Matcher<View> = allOf(
        withId(R.id.order_number),
        isDisplayed()
    )

    /**
     * Close Button identifier
     * Used to close modal dialogs
     */
    val CLOSE_BUTTON: Matcher<View> = allOf(
        withId(R.id.close_button),
        withContentDescription("Close"),
        isDisplayed()
    )

    /**
     * View Cart Button identifier
     * Used to navigate to cart from modal
     */
    val VIEW_CART_BUTTON: Matcher<View> = allOf(
        withId(R.id.view_cart_button),
        withText("View Cart"),
        isDisplayed()
    )

    /**
     * Navigation Cart Icon identifier
     * Used to access cart from navigation bar
     */
    val NAVIGATION_CART: Matcher<View> = allOf(
        withId(R.id.navigation_cart),
        withContentDescription("Cart"),
        isDisplayed()
    )

    /**
     * Product Image identifier
     * Used to identify product in lists and cart
     */
    val PRODUCT_IMAGE: Matcher<View> = allOf(
        withId(R.id.product_image),
        isDisplayed()
    )

    /**
     * Add to Cart Button on PDP identifier
     * Used to add product to cart from Product Detail Page
     */
    val ADD_TO_CART_PDP: Matcher<View> = allOf(
        withId(R.id.add_to_cart_pdp),
        withText("Add to Cart"),
        isEnabled(),
        isDisplayed()
    )

    /**
     * Item Container identifier
     * Used to identify cart item containers
     */
    val ITEM_CONTAINER: Matcher<View> = allOf(
        withId(R.id.item_container),
        isDisplayed()
    )

    /**
     * Remove Button identifier
     * Used to remove items from cart
     */
    val REMOVE_BUTTON: Matcher<View> = allOf(
        withId(R.id.remove_button),
        withContentDescription("Remove"),
        isDisplayed()
    )

    /**
     * Add to Cart Button in Quick View identifier
     * Used to add product to cart from Quick View modal
     */
    val INFO_ADD_TO_CART_BUTTON: Matcher<View> = allOf(
        withId(R.id.info_add_to_cart_button),
        withText("Add to Cart"),
        isEnabled(),
        isDisplayed()
    )

    /**
     * Placeholder R class for resource IDs
     * In a real project, this would be auto-generated
     */
    object R {
        object id {
            const val cart_title = 0x7f0a0001
            const val order_number = 0x7f0a0002
            const val close_button = 0x7f0a0003
            const val view_cart_button = 0x7f0a0004
            const val navigation_cart = 0x7f0a0005
            const val product_image = 0x7f0a0006
            const val add_to_cart_pdp = 0x7f0a0007
            const val item_container = 0x7f0a0008
            const val remove_button = 0x7f0a0009
            const val info_add_to_cart_button = 0x7f0a000a
        }
    }
}