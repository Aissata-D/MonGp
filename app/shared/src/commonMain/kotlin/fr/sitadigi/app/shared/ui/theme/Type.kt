package fr.sitadigi.app.shared.ui.theme


import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import mongp.app.shared.generated.resources.Res
import mongp.app.shared.generated.resources.plus_jakarta_sans_medium
import mongp.app.shared.generated.resources.plus_jakarta_sans_regular
import mongp.app.shared.generated.resources.plus_jakarta_sans_semibold
import mongp.app.shared.generated.resources.sora_bold
import mongp.app.shared.generated.resources.sora_extrabold
import mongp.app.shared.generated.resources.ibm_plex_mono_medium
import org.jetbrains.compose.resources.Font

@Composable
fun soraFamily(): FontFamily = FontFamily(
    Font(Res.font.sora_extrabold, FontWeight.ExtraBold),
    Font(Res.font.sora_bold, FontWeight.Bold),
)

@Composable
fun jakartaFamily(): FontFamily = FontFamily(
    Font(Res.font.plus_jakarta_sans_medium, FontWeight.Medium),
    Font(Res.font.plus_jakarta_sans_regular, FontWeight.Normal),
    Font(Res.font.plus_jakarta_sans_semibold, FontWeight.SemiBold),
)

@Composable
fun plexMonoFamily(): FontFamily = FontFamily(
    Font(Res.font.ibm_plex_mono_medium, FontWeight.Medium)
)

@Composable
fun monGpTypography(): Typography {
    val sora = soraFamily()
    val jakarta = jakartaFamily()
    return Typography(
        headlineMedium = TextStyle(fontFamily = sora, fontWeight = FontWeight.ExtraBold, fontSize = 28.sp),
        titleLarge = TextStyle(fontFamily = sora, fontWeight = FontWeight.Bold, fontSize = 18.sp),
        bodyMedium = TextStyle(fontFamily = jakarta, fontWeight = FontWeight.Normal, fontSize = 14.sp),
        bodyLarge = TextStyle(fontFamily = jakarta, fontWeight = FontWeight.Normal, fontSize = 14.sp),
        labelMedium = TextStyle(fontFamily = jakarta, fontWeight = FontWeight.Medium, fontSize = 12.sp),
        labelLarge = TextStyle(fontFamily = jakarta, fontWeight = FontWeight.SemiBold, fontSize = 14.sp),

    )
}