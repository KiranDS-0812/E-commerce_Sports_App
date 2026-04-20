package dsg.tests.tests

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import dsg.tests.pages.DSGProductListPage

class DSGProductListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Given PLP is loaded,
     * When the first product is visible,
     * Then product card details are correct.
     */
    @Test
    fun validateProductCardDetails() {
        DSGProductListPage(composeTestRule)
            .waitForPageLoad()
            .verifyProductCardDetails()
    }

    /**
     * Given PLP is loaded,
     * When user taps a color swatch,
     * Then product image updates.
     */
    @Test
    fun validateColorSwatchSwitching() {
        DSGProductListPage(composeTestRule)
            .waitForPageLoad()
            .tapOnColorSwatch()
            .verifyProductImageUpdates()
    }

    /**
     * Given PLP is loaded,
     * When user scrolls to find promo badge,
     * Then promotion message is displayed.
     */
    @Test
    fun validatePromotionalMessage() {
        DSGProductListPage(composeTestRule)
            .waitForPageLoad()
            .verifyPromotionalMessageDisplayed()
    }

    /**
     * Given PLP is loaded,
     * When user taps a product,
     * Then PDP is reached.
     */
    @Test
    fun validatePLPtoPDPNavigation() {
        DSGProductListPage(composeTestRule)
            .waitForPageLoad()
            .selectFirstProduct()
    }

    /**
     * Given PLP is loaded,
     * When the first product is visible,
     * Then all product card elements are correct.
     */
    @Test
    fun validateProductCardElements() {
        DSGProductListPage(composeTestRule)
            .waitForPageLoad()
            .validateProductCardElements()
    }

    /**
     * Given PLP is loaded,
     * When user scrolls to find pinned cards,
     * Then pinned content title and image are displayed.
     */
    @Test
    fun validatePinnedContentCards() {
        DSGProductListPage(composeTestRule)
            .waitForPageLoad()
            .validatePinnedContentCards()
    }

    /**
     * Given PLP is loaded,
     * When user clicks Add to Cart,
     * Then Quick View modal opens.
     */
    @Test
    fun validateQuickViewDisplay() {
        DSGProductListPage(composeTestRule)
            .waitForPageLoad()
            .clickAddToCartOnPLP()
            .verifyQuickViewDisplayed()
    }

    /**
     * Given Quick View is open,
     * When user selects all attributes and clicks Add to Cart,
     * Then product is added to cart.
     */
    @Test
    fun validateAddToCartFromQuickView() {
        DSGProductListPage(composeTestRule)
            .waitForPageLoad()
            .clickAddToCartOnPLP()
            .verifyQuickViewDisplayed()
            .selectAllAttributesInQuickView()
            .clickAddToCartInQuickView()
    }

    /**
     * Given PLP is loaded,
     * When product cards are visible,
     * Then compare checkboxes are visible.
     */
    @Test
    fun validateCompareCheckboxAvailability() {
        DSGProductListPage(composeTestRule)
            .waitForPageLoad()
            .validateCompareCheckboxesVisible()
    }

    /**
     * Given PLP is loaded,
     * When user selects 1 product for compare,
     * Then compare sheet shows 1 slot and button is disabled.
     */
    @Test
    fun validateSingleProductComparison() {
        DSGProductListPage(composeTestRule)
            .waitForPageLoad()
            .selectCompareCheckboxAtIndex(0)
            .verifyCompareSheetWithOneProduct()
            .verifyCompareButtonState("disabled")
    }

    /**
     * Given PLP is loaded,
     * When user selects 2 products for compare,
     * Then compare button is enabled.
     */
    @Test
    fun validateTwoProductComparison() {
        DSGProductListPage(composeTestRule)
            .waitForPageLoad()
            .selectCompareCheckboxAtIndex(0)
            .selectCompareCheckboxAtIndex(1)
            .verifyCompareButtonState("enabled")
    }

    /**
     * Given 2 products in compare,
     * When user removes 1,
     * Then sheet reflects change.
     */
    @Test
    fun validateRemoveOneFromComparison() {
        DSGProductListPage(composeTestRule)
            .waitForPageLoad()
            .selectCompareCheckboxAtIndex(0)
            .selectCompareCheckboxAtIndex(1)
            .removeProductFromCompareAtIndex(0)
            .verifyCompareSheetWithOneProduct()
    }

    /**
     * Given 2 products in compare,
     * When user removes both,
     * Then confirmation popup is shown.
     */
    @Test
    fun validateRemoveAllFromComparison() {
        DSGProductListPage(composeTestRule)
            .waitForPageLoad()
            .selectCompareCheckboxAtIndex(0)
            .selectCompareCheckboxAtIndex(1)
            .removeProductFromCompareAtIndex(0)
            .removeProductFromCompareAtIndex(0)
            .verifyRemovalConfirmationPopup()
    }

    /**
     * Given compare sheet is open,
     * When user closes it,
     * Then sheet is dismissed.
     */
    @Test
    fun validateDiscardCompareContainer() {
        DSGProductListPage(composeTestRule)
            .waitForPageLoad()
            .selectCompareCheckboxAtIndex(0)
            .closeCompareSheet()
    }

    /**
     * Given 2 products in compare,
     * When user taps compare button,
     * Then comparison screen is shown.
     */
    @Test
    fun validateNavigateToComparisonScreen() {
        DSGProductListPage(composeTestRule)
            .waitForPageLoad()
            .selectCompareCheckboxAtIndex(0)
            .selectCompareCheckboxAtIndex(1)
            .clickComparePrimaryButton()
            .verifyComparisonScreenDisplayed()
    }
}