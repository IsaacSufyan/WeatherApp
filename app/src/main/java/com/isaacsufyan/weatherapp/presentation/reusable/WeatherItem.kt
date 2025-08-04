package com.isaacsufyan.weatherapp.presentation.reusable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.isaacsufyan.coreui.theme.MyCustomMediumPoppinStyle
import com.isaacsufyan.weatherapp.R

@Composable
fun WeatherItem(
    modifier: Modifier = Modifier,
    title: String? = "19℃",
    desc: String? = "Mon",
    imageUrl: String? = null,
    @DrawableRes icon: Int? = R.drawable.sunny_rainy,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = title ?: "", style = MyCustomMediumPoppinStyle)
        if (imageUrl == null)
            Image(
                painter = painterResource(id = icon ?: R.drawable.sunny_rainy),
                modifier = Modifier
                    .padding(8.dp)
                    .width(36.dp)
                    .height(36.dp),
                contentDescription = null
            )
        else
            AsyncImage(
                "https:$imageUrl",
                contentDescription = "",
                modifier = Modifier
                    .padding(8.dp)
                    .width(36.dp)
                    .height(36.dp),
                contentScale = ContentScale.Fit,
                placeholder = icon?.let { painterResource(it) }
            )
        Text(text = desc ?: "", style = MyCustomMediumPoppinStyle, textAlign = TextAlign.Center)

    }
}