package com.example.gvisapp

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Typeface
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

val regularFont = Font(R.font.suiteregular, FontWeight.Thin)
val lightFont = Font(R.font.suitelight, FontWeight.Light)
val mediumFont = Font(R.font.suitemedium, FontWeight.Medium)
val semiboldFont = Font(R.font.suitesemibold, FontWeight.SemiBold)
val boldFont = Font(R.font.suitebold, FontWeight.Bold)
val extraboldFont = Font(R.font.suiteextrabold, FontWeight.ExtraBold)

val suite_font = FontFamily(
    regularFont,
    lightFont,
    mediumFont,
    semiboldFont,
    boldFont,
    extraboldFont
)