package com.example.littlelemon.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R

val karlaRegular = FontFamily(Font(R.font.karla_regular))
val markaziRegular = FontFamily(Font(R.font.markazi_regular))



val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = markaziRegular,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        color = charcoal
    ),
    titleLarge = TextStyle(
        fontFamily = karlaRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = karlaRegular,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = markaziRegular,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        color = charcoal
    ),

    )

val  h1 = TextStyle(
    fontFamily = markaziRegular,
    fontSize = 40.sp,
    fontWeight = FontWeight.Bold,
    letterSpacing = 0.1.em,
    color = charcoal
)

val h2 = TextStyle(
    fontFamily = karlaRegular,
    color = charcoal,
    fontSize = 18.sp,
    fontWeight = FontWeight.Bold
)

val body1 = TextStyle(
    fontFamily = karlaRegular,
    color = green
)

val body2 = TextStyle(
    fontFamily = markaziRegular,
    fontWeight = FontWeight.Bold,
    color = green
)

val caption = TextStyle(
    fontFamily = markaziRegular,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp
)

val button = TextStyle(
    fontFamily = karlaRegular,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    letterSpacing = 0.1.em
)