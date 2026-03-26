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

val display_title = TextStyle(
    fontFamily = markaziRegular,
    fontSize = 64.sp,
    fontWeight = FontWeight.Medium,
)

val sub_title = TextStyle(
    fontFamily = markaziRegular,
    fontWeight = FontWeight.Normal,
    fontSize = 40.sp
)

val lead_text = TextStyle(
    fontFamily = karlaRegular,
    fontSize = 18.sp,
    fontWeight = FontWeight.Medium
)

val section_title = TextStyle(
    fontFamily = karlaRegular,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 20.sp,
)

val card_title = TextStyle(
    fontFamily = karlaRegular,
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp
)

val para_text = TextStyle(
    fontFamily = karlaRegular,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 1.5.em,
    color = green
)

val highlight_text = TextStyle(
    fontFamily = karlaRegular,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    color = green
)