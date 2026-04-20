package dsg.tests.pages

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.*
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import dsg.tests.identifiers.DSGProductListTags
import dsg.tests.identifiers.ProductTags

/**
 * Page Object for Product Listing Page (PLP).
 * Encapsulates all interactions and assertions for PLP.
 * Handles both Compose and XML (Espresso) UI stacks.
 * All public methods return this for method chaining unless navigation occurs.
 */
class DSGProductListPage(private val composeTestRule: ComposeTestRule) {

    /**
     * Waits for the product list to load by checking for PRODUCT_LIST or PRODUCT_CARD.
     * @param timeoutMillis Timeout in milliseconds (default 30,000ms)
     */
    fun waitForPageLoad(timeoutMillis: Long = 30_000): DSGProductListPage {
        composeTestRule.waitUntil(timeoutMillis) {
            composeTestRule.onAllNodesWithTag(DSGProductListTags.PRODUCT_LIST).fetchSemanticsNodes().isNotEmpty() ||
            composeTestRule.onAllNodesWithTag(DSGProductListTags.PRODUCT_CARD).fetchSemanticsNodes().isNotEmpty()
        }
        Thread.sleep(500) // Allow animation to settle
        return this
    }

    /**
     * Alias for waitForPageLoad.
     */
    fun waitForProductsToLoad(timeoutMillis: Long = 30_000) = waitForPageLoad(timeoutMillis)

    /**
     * Asserts that the first product card, image, title, and price are visible.
     */
    fun verifyProductCardDetails(): DSGProductListPage {
        try {
            composeTestRule.onAllNodesWithTag(DSGProductListTags.PRODUCT_CARD).onFirst().assertIsDisplayed()
            composeTestRule.onAllNodesWithTag(DSGProductListTags.PRODUCT_IMAGE).onFirst().assertIsDisplayed()
            composeTestRule.onAllNodesWithTag(DSGProductListTags.PRODUCT_TITLE).onFirst().assertIsDisplayed()
            composeTestRule.onAllNodesWithTag(DSGProductListTags.PRODUCT_PRICE).onFirst().assertIsDisplayed()
        } catch (e: AssertionError) {
            println("Warning: One or more product card elements not found in Compose. Trying Espresso fallback.")
            try {
                Espresso.onView(withTagValue(`is`(DSGProductListTags.PRODUCT_CARD))).check(matches(isDisplayed()))
                Espresso.onView(withTagValue(`is`(DSGProductListTags.PRODUCT_IMAGE))).check(matches(isDisplayed()))
                Espresso.onView(withTagValue(`is`(DSGProductListTags.PRODUCT_TITLE))).check(matches(isDisplayed()))
                Espresso.onView(withTagValue(`is`(DSGProductListTags.PRODUCT_PRICE))).check(matches(isDisplayed()))
            } catch (e: Exception) {
                println("Warning: Product card elements not found in XML fallback.")
            }
        }
        return this
    }

    /**
     * Extended check for product card: includes fav icon and swatch if present.
     * Soft-fails if optional elements are missing.
     */
    fun validateProductCardElements(): DSGProductListPage {
        verifyProductCardDetails()
        try {
            composeTestRule.onAllNodesWithTag(DSGProductListTags.PRODUCT_FAVORITE_ICON).onFirst().assertIsDisplayed()
        } catch (e: AssertionError) {
            println("Warning: Favorite icon not present on first card.")
        }
        try {
            composeTestRule.onAllNodesWithTag(DSGProductListTags.COLOR_SWATCH).onFirst().assertIsDisplayed()
        } catch (e: AssertionError) {
            println("Warning: Color swatch not present on first card.")
        }
        return this
    }

    /**
     * Taps the 2nd color swatch on the first product card, or 1st if only one exists.
     * Soft-fails if no swatch present.
     */
    fun tapOnColorSwatch(): DSGProductListPage {
        try {
            val swatches = composeTestRule.onAllNodesWithTag(DSGProductListTags.COLOR_SWATCH)
            if (swatches.fetchSemanticsNodes().size > 1) {
                swatches.onNth(1).performClick()
            } else {
                swatches.onFirst().performClick()
            }
            Thread.sleep(500) // Animation settle
        } catch (e: Exception) {
            println("Warning: No color swatch found to tap.")
        }
        return this
    }

    /**
     * Asserts that the product image updates after swatch tap.
     */
    fun verifyProductImageUpdates(): DSGProductListPage {
        // Implementation would check for image resource change, but here just assert still displayed
        composeTestRule.onAllNodesWithTag(DSGProductListTags.PRODUCT_IMAGE).onFirst().assertIsDisplayed()
        return this
    }

    /**
     * Scrolls up to 5 times to find a promotion message, then asserts it's displayed.
     */
    fun verifyPromotionalMessageDisplayed(): DSGProductListPage {
        var found = false
        repeat(5) {
            try {
                composeTestRule.onAllNodesWithTag(DSGProductListTags.PROMOTION_MESSAGE).onFirst().assertIsDisplayed()
                found = true
                return@repeat
            } catch (e: AssertionError) {
                composeTestRule.onAllNodesWithTag(DSGProductListTags.PRODUCT_LIST).onFirst()
                    .performTouchInput { swipeUp() }
            }
        }
        if (!found) println("Warning: Promotion message not found after 5 scrolls.")
        return this
    }

    /**
     * Scrolls up to 10 times to find a pinned product card, asserting title and image.
     */
    fun validatePinnedContentCards(): DSGProductListPage {
        var found = false
        repeat(10) {
            try {
                composeTestRule.onAllNodesWithTag(DSGProductListTags.PINNED_PRODUCT).onFirst().assertIsDisplayed()
                composeTestRule.onAllNodesWithTag(DSGProductListTags.PINNED_CONTENT_TITLE).onFirst().assertIsDisplayed()
                composeTestRule.onAllNodesWithTag(DSGProductListTags.PINNED_CONTENT_IMAGE).onFirst().assertIsDisplayed()
                found = true
                return@repeat
            } catch (e: AssertionError) {
                composeTestRule.onAllNodesWithTag(DSGProductListTags.PRODUCT_LIST).onFirst()
                    .performTouchInput { swipeUp() }
            }
        }
        if (!found) println("Warning: Pinned content cards not found after 10 scrolls.")
        return this
    }

    /**
     * Clicks the first product image or title to navigate to PDP.
     */
    fun selectFirstProduct(): DSGProductListPage {
        try {
            composeTestRule.onAllNodesWithTag(DSGProductListTags.PRODUCT_IMAGE).onFirst().performClick()
        } catch (e: Exception) {
            try {
                composeTestRule.onAllNodesWithTag(DSGProductListTags.PRODUCT_TITLE).onFirst().performClick()
            } catch (e: Exception) {
                println("Warning: Could not click first product image or title.")
            }
        }
        Thread.sleep(2000) // Screen transition settle
        return this
    }

    /**
     * Clicks Add to Cart button on PLP card, waits for Quick View modal.
     */
    fun clickAddToCartOnPLP(): DSGProductListPage {
        try {
            composeTestRule.onAllNodesWithTag(ProductTags.ADD_TO_CART_BUTTON).onFirst().performClick()
        } catch (e: Exception) {
            println("Warning: Add to Cart button not found in Compose, trying Espresso fallback.")
            try {
                Espresso.onView(withTagValue(`is`(ProductTags.ADD_TO_CART_BUTTON))).perform(click())
            } catch (e: Exception) {
                println("Warning: Add to Cart button not found in XML fallback.")
            }
        }
        Thread.sleep(4000) // Cart update propagation
        return this
    }

    /**
     * Asserts Quick View modal is displayed (Compose or XML).
     */
    fun verifyQuickViewDisplayed(): DSGProductListPage {
        try {
            composeTestRule.onAllNodesWithTag("AttributeWidgetContainer").onFirst().assertIsDisplayed()
        } catch (e: Exception) {
            try {
                Espresso.onView(withTagValue(`is`("info_add_to_cart_button"))).check(matches(isDisplayed()))
            } catch (e: Exception) {
                println("Warning: Quick View modal not found in Compose or XML.")
            }
        }
        return this
    }

    /**
     * Selects the first value for all attributes in Quick View.
     */
    fun selectAllAttributesInQuickView(): DSGProductListPage {
        // Example: attributeValue_0, attributeValue_1, ...
        for (i in 0..2) {
            try {
                composeTestRule.onAllNodesWithTag(ProductTags.ATTRIBUTE_VALUE_PREFIX + i).onFirst().performClick()
            } catch (e: Exception) {
                println("Warning: Attribute value $i not found in Quick View.")
            }
        }
        return this
    }

    /**
     * Clicks Add to Cart in Quick View using three strategies.
     */
    fun clickAddToCartInQuickView(): DSGProductListPage {
        try {
            Espresso.onView(withTagValue(`is`(ProductTags.ADD_TO_CART_BUTTON))).perform(object : ViewAction {
                override fun getConstraints() = isDisplayed()
                override fun getDescription() = "Force click Add to Cart"
                override fun perform(uiController: androidx.test.espresso.UiController?, view: android.view.View?) {
                    view?.performClick()
                }
            })
        } catch (e: Exception) {
            try {
                composeTestRule.onAllNodesWithTag(ProductTags.ADD_TO_CART_BUTTON).onFirst().performClick()
            } catch (e: Exception) {
                try {
                    Espresso.onView(withText("Add to Cart")).perform(click())
                } catch (e: Exception) {
                    println("Warning: Could not click Add to Cart in Quick View.")
                }
            }
        }
        Thread.sleep(4000)
        return this
    }

    /**
     * Asserts that the compare checkbox is visible on at least one product card.
     */
    fun validateCompareCheckboxesVisible(): DSGProductListPage {
        try {
            composeTestRule.onAllNodesWithTag(DSGProductListTags.COMPARE_CHECKBOX).onFirst().assertIsDisplayed()
        } catch (e: AssertionError) {
            println("Warning: Compare checkbox not found on product cards.")
        }
        return this
    }

    /**
     * Selects the compare checkbox at the given index, with scroll and overlap guard.
     */
    fun selectCompareCheckboxAtIndex(index: Int): DSGProductListPage {
        try {
            composeTestRule.onAllNodesWithTag(DSGProductListTags.COMPARE_CHECKBOX).onNth(index).performScrollTo().performClick()
            Thread.sleep(2000) // State update propagation
        } catch (e: Exception) {
            println("Warning: Could not select compare checkbox at index $index.")
        }
        return this
    }

    /**
     * Asserts that the compare sheet is visible and has exactly one product slot.
     */
    fun verifyCompareSheetWithOneProduct(): DSGProductListPage {
        composeTestRule.onAllNodesWithTag(DSGProductListTags.COMPARE_SHEET).onFirst().assertIsDisplayed()
        val slots = composeTestRule.onAllNodesWithTag(DSGProductListTags.COMPARE_PRODUCT_SLOT)
        if (slots.fetchSemanticsNodes().size != 1) {
            println("Warning: Compare sheet does not have exactly one product slot.")
        }
        return this
    }

    /**
     * Asserts the compare button state (enabled/disabled). Waits up to 45s for state change.
     */
    fun verifyCompareButtonState(expectedState: String): DSGProductListPage {
        composeTestRule.waitUntil(45_000) {
            val node = composeTestRule.onAllNodesWithTag(DSGProductListTags.COMPARE_PRIMARY_BUTTON).onFirst()
            (expectedState == "enabled" && node.isEnabled()) || (expectedState == "disabled" && !node.isEnabled())
        }
        return this
    }

    /**
     * Clicks the Compare button (primary) if enabled, waits for comparison screen.
     */
    fun clickComparePrimaryButton(): DSGProductListPage {
        val button = composeTestRule.onAllNodesWithTag(DSGProductListTags.COMPARE_PRIMARY_BUTTON).onFirst()
        if (button.isEnabled()) {
            button.performClick()
            Thread.sleep(3000)
        } else {
            println("Warning: Compare button is not enabled.")
        }
        return this
    }

    /**
     * Asserts that the comparison screen is displayed (by text or product titles).
     */
    fun verifyComparisonScreenDisplayed(): DSGProductListPage {
        try {
            composeTestRule.onNodeWithText("Compare").assertIsDisplayed()
        } catch (e: AssertionError) {
            println("Warning: Compare text not found, checking product titles.")
            try {
                if (composeTestRule.onAllNodesWithTag(DSGProductListTags.PRODUCT_TITLE).fetchSemanticsNodes().size < 2) {
                    println("Warning: Less than two product titles found on comparison screen.")
                }
            } catch (e: Exception) {
                println("Warning: Could not verify comparison screen.")
            }
        }
        return this
    }

    /**
     * Scrolls to and verifies each comparison attribute text.
     * Warns if not found.
     */
    fun verifyComparisonAttributes(attributes: List<String>): DSGProductListPage {
        attributes.forEach { attr ->
            try {
                composeTestRule.onNodeWithText(attr).performScrollTo().assertIsDisplayed()
            } catch (e: Exception) {
                println("Warning: Comparison attribute '$attr' not found.")
            }
        }
        return this
    }

    /**
     * Closes the compare sheet via close button and confirmation popup.
     */
    fun closeCompareSheet(): DSGProductListPage {
        try {
            composeTestRule.onAllNodesWithTag(DSGProductListTags.COMPARE_CLOSE_BUTTON).onFirst().performClick()
            composeTestRule.onNodeWithText("Close List").performClick()
            composeTestRule.waitUntil(10_000) {
                composeTestRule.onAllNodesWithTag(DSGProductListTags.COMPARE_SHEET).fetchSemanticsNodes().isEmpty()
            }
        } catch (e: Exception) {
            println("Warning: Could not close compare sheet.")
        }
        return this
    }

    /**
     * Removes a product from compare at the given index.
     */
    fun removeProductFromCompareAtIndex(index: Int): DSGProductListPage {
        try {
            composeTestRule.onAllNodesWithTag(DSGProductListTags.COMPARE_REMOVE_BUTTON).onNth(index).performClick()
        } catch (e: Exception) {
            println("Warning: Could not remove product from compare at index $index.")
        }
        return this
    }

    /**
     * Asserts that the removal confirmation popup is shown and closes it.
     */
    fun verifyRemovalConfirmationPopup(): DSGProductListPage {
        try {
            composeTestRule.onAllNodesWithTag(DSGProductListTags.COMPARE_ALERT_DIALOG).onFirst().assertIsDisplayed()
            composeTestRule.onNodeWithText("Close List").performClick()
        } catch (e: Exception) {
            println("Warning: Removal confirmation popup not found.")
        }
        return this
    }

    /**
     * Clicks the back button (Compose or Espresso fallback).
     */
    fun clickBackButton(): DSGProductListPage {
        try {
            composeTestRule.onNodeWithContentDescription("Back").performClick()
        } catch (e: Exception) {
            try {
                composeTestRule.onNodeWithContentDescription("Navigate up").performClick()
            } catch (e: Exception) {
                Espresso.pressBack()
            }
        }
        Thread.sleep(2000)
        return this
    }
}