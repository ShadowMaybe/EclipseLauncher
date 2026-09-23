package me.shadow.eclipse

import org.junit.Assert.assertEquals
import org.junit.Test

class ApplicationIdentityTest {
    @Test
    fun debugVariantUsesEclipseApplicationId() {
        assertEquals("me.shadow.eclipse.debug", BuildConfig.APPLICATION_ID)
    }
}
