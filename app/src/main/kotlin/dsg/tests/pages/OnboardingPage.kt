package dsg.tests.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.equalTo

class OnboardingPage {

    fun clickContinueAsGuest() {
        onView(withTagValue(equalTo("onboardingContinueButton"))).perform(click())
    }

    fun handleLocationScreen(allow: Boolean = false) {
        if (allow) {
            onView(withTagValue(equalTo("onboardingContinueButton"))).perform(click())
        } else {
            onView(withTagValue(equalTo("onboardingNotNowButton"))).perform(click())
        }
    }

    fun handleNotificationScreen(allow: Boolean = false) {
        if (allow) {
            onView(withText("Allow")).perform(click())
        } else {
            onView(withText("Don't Allow")).perform(click())
        }
    }
}