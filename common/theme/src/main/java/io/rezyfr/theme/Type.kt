package io.rezyfr.theme

import Black
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import extraLargeFont
import extraSmallFont
import largeFont
import mediumFont


val MainTypography = Typography(
    h1 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Bold,
        fontSize = extraLargeFont
    ),
    h2 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Bold,
        fontSize = 21.sp
    ),
    h3 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    ),
    h4 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Medium,
        fontSize = largeFont
    ),
    h5 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),
    h6 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Bold,
        fontSize = largeFont
    ),
    body1 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Normal,
        fontSize = mediumFont
    ),
    body2 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Bold,
        fontSize = mediumFont
    ),
    subtitle1 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Medium,
        fontSize = mediumFont
    ),
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = mediumFont
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = extraSmallFont
    )
)