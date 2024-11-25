package jOS.Core.ThemeEngine

import android.content.Context
import androidx.annotation.RestrictTo
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import jOS.Core.ThemeEngine.ThemeEngine.themeClass
import jOS.Core.utils.VersionUtils

/**
 * Internal AndroidX Compose Extension for ThemeEngine
 */
@RestrictTo(RestrictTo.Scope.LIBRARY)
class ThemeEngineKT {
    companion object {

        @JvmStatic
        fun getLightColourScheme(context: Context): ColorScheme {
            if (themeClass != null && themeClass.LComposeColourScheme(context) != null) {
                return themeClass.LComposeColourScheme(context);
            } else {
                if (VersionUtils.Android.isAtLeastS()) {
                    return dynamicLightColorScheme(context)
                }
                return lightColorScheme()
            }
        }

        @JvmStatic
        fun getDarkColourScheme(context: Context): ColorScheme {
            if (themeClass != null && themeClass.DComposeColourScheme(context) != null) {
                return themeClass.DComposeColourScheme(context);
            } else {
                if (VersionUtils.Android.isAtLeastS()) {
                    return dynamicDarkColorScheme(context)
                }
                return darkColorScheme()
            }
        }
    }
}