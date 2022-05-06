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
        color = Black,
        fontSize = extraLargeFont
    ),
    h2 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Bold,
        color = Black,
        fontSize = 21.sp
    ),
    h3 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.SemiBold,
        color = Black,
        fontSize = 18.sp
    ),
    h4 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Medium,
        color = Black,
        fontSize = largeFont
    ),
    h5 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Medium,
        color = Black,
        fontSize = 18.sp
    ),
    h6 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Bold,
        color = Black,
        fontSize = largeFont
    ),
    body1 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Normal,
        color = Black,
        fontSize = mediumFont
    ),
    body2 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Bold,
        color = Black,
        fontSize = mediumFont
    ),
    subtitle1 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Medium,
        color = Black,
        fontSize = mediumFont
    ),
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        color = Black,
        fontSize = mediumFont
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        color = Black,
        fontSize = extraSmallFont
    )
)