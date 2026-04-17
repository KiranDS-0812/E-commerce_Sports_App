package dsg.tests.tests

import dsg.tests.pages.OnboardingPage
import dsg.tests.base.BaseTest
import org.junit.Test

/**
 * Enterprise-compliant onboarding UI flow test.
 */
class OnboardingTest : BaseTest() {

    @Test
    fun testOnboardingFlow_JoinNowNavigation() {
        val onboardingPage = OnboardingPage()
            .handleAgeVerification()
            .verifyOnboardingLogoVisible()
            .tapJoinNow()
        // RegistrationPage will be returned, verify registration UI
        onboardingPage.verifyRegistrationScreenVisible()
    }

    @Test
    fun testOnboardingFlow_ContinueAsGuest() {
        OnboardingPage()
            .handleAgeVerification()
            .verifyOnboardingLogoVisible()
            .tapContinueAsGuest()
        // HomePage will be returned, verify home UI
    }
}