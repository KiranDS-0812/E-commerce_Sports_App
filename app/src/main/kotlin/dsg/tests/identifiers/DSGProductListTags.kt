package dsg.tests.identifiers

/**
 * Semantic tags for Product Listing Page (PLP) UI elements.
 * All values are unique and referenced by Page Objects and test specs.
 * Grouped by feature area for maintainability.
 */
object DSGProductListTags {
    // Product List & Cards
    const val PRODUCT_LIST = "productList"
    const val PRODUCT_CARD = "standardProduct"
    const val PRODUCT_IMAGE = "productImage"
    const val PRODUCT_TITLE = "productTitleText"
    const val PRODUCT_PRICE = "listPriceText"
    const val WAS_PRICE = "wasPriceText"
    const val PRODUCT_RATING = "productRating"
    const val PRODUCT_BRAND = "productBrandText"

    // Swatches & Wishlist
    const val COLOR_SWATCH = "swatchItem"
    const val PRODUCT_FAVORITE_ICON = "productFavoriteIcon"
    const val FAVORITE_INTRO_BUTTON = "introButton"
    const val GUEST_FAVORITE_SIGN_IN_BUTTON = "sign in now"

    // Promotions & Editorial
    const val PROMOTION_MESSAGE = "promotionMessageText"
    const val PINNED_PRODUCT = "pinnedProduct"
    const val SPONSORED_PRODUCT = "sponsoredProduct"
    const val SKU_PRODUCT = "skuProduct"
    const val PINNED_CONTENT_TITLE = "pinnedContentTitle"
    const val PINNED_CONTENT_SUBTITLE = "pinnedContentSubTitle"
    const val PINNED_CONTENT_IMAGE = "pinnedContentImage"

    // Compare Feature
    const val COMPARE_CHECKBOX = "compareCheckbox"
    const val COMPARE_SHEET = "compareProductsSheet"
    const val COMPARE_PRODUCT_SLOT = "compareProductSlot"
    const val COMPARE_PRIMARY_BUTTON = "compareProductsSheetPrimaryButton"
    const val COMPARE_CLOSE_BUTTON = "compareProductsSheetCloseButton"
    const val COMPARE_REMOVE_BUTTON = "compareProductRemoveButton"
    const val COMPARE_ALERT_DIALOG = "showComparisonAlertDialog"
}

// Cross-page tags (shared)
object ProductTags {
    const val ADD_TO_CART_BUTTON = "addToCartButton"
    const val ATTRIBUTE_VALUE_PREFIX = "attributeValue_"
}