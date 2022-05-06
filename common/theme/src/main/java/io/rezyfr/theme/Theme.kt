import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable

@SuppressLint("ConflictingOnColor")
private val ColorPalette = darkColors(
    primary = Yellow,
    primaryVariant = YellowTransparent,
    onPrimary = White,
    onSecondary = Black,

    background = BackgroundDarker,
    onBackground = BackgroundDarker,

    surface = Background,
    onSurface = White
)

val MuviColors: Colors
    @Composable get() = MaterialTheme.colors

val MuviShapes: Shapes
    @Composable get() = MaterialTheme.shapes

val MuviTypography: Typography
    @Composable get() = MaterialTheme.typography

@Composable
fun MuviTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = ColorPalette,
        typography = MainTypography,
        content = content
    )
}