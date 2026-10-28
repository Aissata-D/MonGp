package fr.sitadigi.app.shared.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

val MonGpColorScheme = lightColorScheme(
    primary = Indigo,
    onPrimary = White,
    primaryContainer = IndigoLight,
    onPrimaryContainer = White,
    secondary = Ochre,
    onSecondary = Indigo,
    secondaryContainer = OchreLight,
    onSecondaryContainer = Anthracite,
    tertiary = Green,
    onTertiary = White,
    tertiaryContainer = GreenLight,
    onTertiaryContainer = Green,
    error = Red,
    onError = White,
    errorContainer = RedLight,
    onErrorContainer = Red,
    background = OffWhite,
    onBackground = Anthracite,
    surface = White,
    onSurface = Anthracite,
    surfaceVariant = LightGray,
    onSurfaceVariant = Gray,
    outline = Gray,
    outlineVariant = LightGray,
    surfaceContainerHighest = White,
)

val MonGpShapes = Shapes(
    medium = RoundedCornerShape(14.dp),
    small = RoundedCornerShape(12.dp),
)

@Composable
fun MonGpTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MonGpColorScheme,
        typography = monGpTypography(),
        shapes = MonGpShapes,
        content = content
    )
}