package com.isaacsufyan.coreui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.isaacsufyan.coreui.R

val fonts = FontFamily(
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_bold, FontWeight.Bold),
)

val inclusiveSansFontFamily = FontFamily(
    Font(R.font.inclusive_sans_regular, FontWeight.Normal),
    Font(R.font.inclusive_sans_regular, FontWeight.W400),
    Font(R.font.opensans_bold, FontWeight.Bold),
    Font(R.font.opensans_semibold,FontWeight.W600),
)

val inclusivePoppinFamily = FontFamily(
    Font(R.font.poppin_regular, FontWeight.W400),
    Font(R.font.poppin_regular, FontWeight.W500)
)


val Typography = Typography(
    h1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Light,
        color = Color.White,
        fontSize = 96.sp,
        letterSpacing = 0.sp,
    ),
    h2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        color = Color.White,
        fontSize = 36.sp,
        letterSpacing = 0.sp
    ),
    h3 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold,
        color = Color.White,
        fontSize = 20.sp,
        letterSpacing = 0.sp
    ),
    button = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold,
        color = Color.White,
        fontSize = 20.sp,
        letterSpacing = 0.sp
    ),
    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold,
        color = Color.White,
        fontSize = 18.sp,
        letterSpacing = 0.sp,
    ),

)

val MyCustomTextStyle = TextStyle(
    fontFamily = inclusiveSansFontFamily,
    fontSize = 16.sp,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight.Normal,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp,
    color = Color.White
)
val MyCustomLargePoppinStyle = TextStyle(
    fontFamily = inclusivePoppinFamily,
    fontSize = 20.sp,
    fontWeight = FontWeight.W400,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp,
    color = Color.White
)

val MyCustomMediumPoppinStyle = TextStyle(
    fontFamily = inclusivePoppinFamily,
    fontSize = 16.sp,
    fontWeight = FontWeight.W500,
    lineHeight = 20.sp,
    letterSpacing = 0.5.sp,
    color = Color.White
)

val MyCustomOpenSansBoldStyle = TextStyle(
    fontFamily = inclusiveSansFontFamily,
    fontSize = 20.sp,
    fontWeight = FontWeight.Bold,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp,
    color = Color.White
)

val MyCustomPoppinBoldStyle = TextStyle(
    fontFamily = inclusivePoppinFamily,
    fontSize = 40.sp,
    fontWeight = FontWeight.Bold,
    letterSpacing = 0.5.sp,
    color = Color.White
)

val MyCustomOpenSansBold26spStyle = TextStyle(
    fontFamily = inclusiveSansFontFamily,
    fontSize = 26.sp,
    fontWeight = FontWeight.W600,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp,
    color = Color.White
)

val MyCustomOpenSansSemoBold13spStyle = TextStyle(
    fontFamily = inclusiveSansFontFamily,
    fontSize = 13.sp,
    fontWeight = FontWeight.W600,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp,
    color = Color.White
)

val MyCustomOpenSansBold16spStyle = TextStyle(
    fontFamily = inclusiveSansFontFamily,
    fontSize = 16.sp,
    fontWeight = FontWeight.W400,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp,
    color = Color.White
)
