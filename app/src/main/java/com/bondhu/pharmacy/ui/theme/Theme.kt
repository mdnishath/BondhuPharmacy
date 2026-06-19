package com.bondhu.pharmacy.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val BondhuDarkColorScheme = darkColorScheme(
    primary              = LimeGreen,
    onPrimary            = Color(0xFF0D1A00),
    primaryContainer     = Color(0xFF1A2E08),
    onPrimaryContainer   = LimeGreen,
    secondary            = SuccessGreen,
    onSecondary          = Color(0xFF003909),
    secondaryContainer   = Color(0xFF00520E),
    onSecondaryContainer = SuccessGreen,
    background           = BackgroundDark,
    onBackground         = TextPrimary,
    surface              = PanelDark,
    onSurface            = TextPrimary,
    surfaceVariant       = PanelDark2,
    onSurfaceVariant     = TextMuted,
    surfaceTint          = LimeGreen,
    outline              = BorderColor,
    outlineVariant       = BorderLight,
    error                = ErrorColor,
    onError              = Color(0xFF690005),
)

@Composable
fun BondhuPharmacyTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = BondhuDarkColorScheme,
        typography  = BondhuTypography,
        content     = content
    )
}
