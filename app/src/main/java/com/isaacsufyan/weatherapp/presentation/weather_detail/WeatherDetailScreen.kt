package com.isaacsufyan.weatherapp.presentation.weather_detail


import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.isaacsufyan.coreui.reusable.IsaacSurface
import com.isaacsufyan.coreui.theme.MyCustomLargePoppinStyle
import com.isaacsufyan.coreui.theme.MyCustomOpenSansBold16spStyle
import com.isaacsufyan.coreui.theme.MyCustomOpenSansBold26spStyle
import com.isaacsufyan.coreui.theme.MyCustomOpenSansBoldStyle
import com.isaacsufyan.coreui.theme.MyCustomOpenSansSemoBold13spStyle
import com.isaacsufyan.coreui.theme.MyCustomTextStyle
import com.isaacsufyan.coreui.theme.colorF7CBFD
import com.isaacsufyan.coreui.theme.gradientColor362A84
import com.isaacsufyan.coreui.theme.gradientColor3E2D8F
import com.isaacsufyan.coreui.theme.gradientColor805BCAD1
import com.isaacsufyan.coreui.theme.gradientColor8247a4
import com.isaacsufyan.coreui.theme.gradientColor9D52AC
import com.isaacsufyan.coreui.theme.gradientColorBD08FC
import com.isaacsufyan.weatherapp.R
import com.isaacsufyan.weatherapp.business.domain.model.ForeCastData
import com.isaacsufyan.weatherapp.business.domain.model.Weather
import com.isaacsufyan.weatherapp.presentation.reusable.WeatherItem
import com.isaacsufyan.weatherapp.utils.WeatherDataUtils.getDayOfMonth
import com.isaacsufyan.weatherapp.utils.WeatherDataUtils.getForecastItemBg
import com.isaacsufyan.weatherapp.utils.WeatherDataUtils.getUiIndexValue
import com.isaacsufyan.weatherapp.utils.WeatherDataUtils.isDay
import com.isaacsufyan.weatherapp.utils.WeatherDataUtils.sunnyOrClearBgColors


@Preview
@Composable
private fun WeatherDetailScreenPreview() {
    WeatherDetailScreen(weather = Weather())
}

@Composable
fun WeatherDetailScreen(weather: Weather) {
    IsaacSurface {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            LocationItem(weather = weather)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Color.Transparent
                    )
                    .padding(bottom = 60.dp)


            ) {
                WeatherForCastFor7Days(weather = weather)
                WeatherInfoItem(weather = weather)
                AirQualityItem(weather = weather)
                SunriseAndUv(weather = weather)
            }
        }
    }
}


@Composable
private fun LocationItem(modifier: Modifier = Modifier, weather: Weather? = null) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier
            .fillMaxWidth()
            .padding(top = 54.dp, start = 16.dp, end = 16.dp)
    ) {
        Text(text = weather?.location?.name ?: "", style = MyCustomLargePoppinStyle)
        Text(
            text = "${
                stringResource(R.string.temp_in_degree).format(
                    weather?.currentWeather?.tempC
                )
            }  ${
                stringResource(R.string.feels_like_in_degree).format(
                    weather?.currentWeather?.feelsLikeC
                )
            }", style = MyCustomLargePoppinStyle
        )
    }
}


@Composable
private fun WeatherInfoItem(weather: Weather?) {
    Text(
        text = stringResource(R.string.weather_information),
        modifier = Modifier.padding(start = 32.dp, top = 24.dp),
        style = MyCustomOpenSansBoldStyle
    )

    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        WeatherItem(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
                .width(82.dp)
                .height(172.dp)
                .padding(top = 8.dp, bottom = 8.dp)
                .clip(AbsoluteRoundedCornerShape(50.dp))
                .background(
                    Brush.verticalGradient(sunnyOrClearBgColors)
                ),
            weather?.currentWeather?.visKm?.toString(),
            desc = stringResource(id = R.string.vis_kph),
            icon = R.drawable.weather_visibility
        )
        Spacer(modifier = Modifier.width(8.dp))
        WeatherItem(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
                .width(82.dp)
                .height(172.dp)
                .padding(top = 8.dp, bottom = 8.dp)
                .clip(AbsoluteRoundedCornerShape(50.dp))
                .background(
                    Brush.verticalGradient(sunnyOrClearBgColors)
                ),
            weather?.currentWeather?.gustKph?.toString(),
            desc = stringResource(id = R.string.gust_kph),
            icon = R.drawable.wind_gust
        )
        Spacer(modifier = Modifier.width(8.dp))

        WeatherItem(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
                .width(82.dp)
                .height(172.dp)
                .padding(top = 8.dp, bottom = 8.dp)
                .clip(AbsoluteRoundedCornerShape(50.dp))
                .background(
                    Brush.verticalGradient(sunnyOrClearBgColors)
                ),
            weather?.currentWeather?.windDir,
            desc = stringResource(id = R.string.wind_direction),
            icon = R.drawable.wind_direction
        )
        Spacer(modifier = Modifier.width(8.dp))
        WeatherItem(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
                .width(82.dp)
                .height(172.dp)
                .padding(top = 8.dp, bottom = 8.dp)
                .clip(AbsoluteRoundedCornerShape(50.dp))
                .background(
                    Brush.verticalGradient(sunnyOrClearBgColors)
                ),
            weather?.currentWeather?.cloud?.toString(),
            desc = stringResource(id = R.string.cloud),
            icon = R.drawable.cloud_icon
        )
    }
}

@Composable
private fun WeatherForCastFor7Days(weather: Weather?) {
    Text(
        text = stringResource(R.string._7_days_forecasts),
        modifier = Modifier.padding(start = 32.dp, top = 32.dp),
        style = MyCustomOpenSansBoldStyle
    )

    weather?.let {
        it.forecast?.foreCastList?.let {
            LazyRow(
                contentPadding = PaddingValues(start = 8.dp, end = 16.dp),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                itemsIndexed(it) { index: Int, item: ForeCastData ->
                    WeatherItem(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .width(82.dp)
                            .height(172.dp)
                            .padding(top = 8.dp, bottom = 8.dp)
                            .clip(AbsoluteRoundedCornerShape(50.dp))
                            .background(
                                Brush.verticalGradient(getForecastItemBg(item.day.condition.code))
                            ),
                        stringResource(id = R.string.temp_degree).format(item.day.maxtemp_c),
                        getDayOfMonth(index),
                        item.day.condition.icon
                    )
                }
            }

        }

    }

}


@Composable
private fun AirQualityItem(modifier: Modifier = Modifier, weather: Weather? = null) {
    Card(
        modifier = modifier
            .padding(start = 24.dp, top = 32.dp, end = 24.dp)
            .fillMaxWidth()
            .height(175.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = 0.dp
    ) {

        Column(
            modifier = Modifier
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            gradientColor8247a4,
                            gradientColor3E2D8F
                        )
                    )
                )
                .padding(start = 18.dp, end = 18.dp), verticalArrangement = Arrangement.Center
        ) {
            Row(modifier = Modifier.padding(start = 4.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.weather_condition),
                    tint = Color.White,
                    contentDescription = null,
                    modifier = Modifier
                        .width(28.dp)
                        .height(28.dp)
                )
                Text(
                    text = stringResource(R.string.weather_condition),
                    style = MyCustomTextStyle,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Text(
                text = weather?.currentWeather?.condition?.text ?: "",
                style = MyCustomOpenSansBold26spStyle,
                modifier = Modifier.padding(top = 8.dp)
            )
            Divider(
                modifier = Modifier
                    .padding(top = 16.dp, start = 8.dp, end = 8.dp)
                    .fillMaxWidth()
                    .height(3.dp)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                gradientColor805BCAD1,
                                gradientColorBD08FC,
                                gradientColor362A84
                            )
                        )
                    )
            )

            Text(
                text = stringResource(R.string.wind_speed_in_kph).format(weather?.currentWeather?.windKph),
                style = MyCustomTextStyle,
                modifier = Modifier.padding(top = 16.dp)
            )
        }

    }
}

@Composable
private fun SunriseAndUv(weather: Weather? = null) {
    Row(
        modifier = Modifier.padding(start = 24.dp, top = 32.dp, end = 24.dp),
        horizontalArrangement = Arrangement.Center
    ) {
//        SunriseItem(modifier = Modifier.weight(1f))
        SunriseItem(
            modifier = Modifier
                .weight(1f),
            stringResource(R.string.humidity),
            weather?.currentWeather?.humidity?.toString() ?: "1",
            isDay(weather?.currentWeather?.isDay ?: 1, LocalContext.current),
            MyCustomOpenSansSemoBold13spStyle,
            R.drawable.humidity
        )
        //Due to weight if use padding one view remain small
        Spacer(modifier = Modifier.width(16.dp))
        SunriseItem(
            modifier = Modifier
                .weight(1f),
            stringResource(R.string.uv_index),
            weather?.currentWeather?.uv?.toInt()?.toString() ?: "1",
            getUiIndexValue(weather?.currentWeather?.uv?.toInt() ?: 1, LocalContext.current),
            MyCustomOpenSansBold26spStyle
        )
    }
}

@Composable
private fun SunriseItem(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    description: String,
    text3Style: TextStyle,
    @DrawableRes icon: Int = R.drawable.sun_rise
) {
    Column(
        modifier
            .height(150.dp)
            .border(width = 1.dp, color = colorF7CBFD, shape = RoundedCornerShape(20.dp))
            .padding(1.dp)
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        gradientColor3E2D8F,
                        gradientColor3E2D8F,
                        gradientColor9D52AC,
                    ), start = Offset(Float.POSITIVE_INFINITY, 0f), end = Offset(0f, 270f)
                ), shape = RoundedCornerShape(20.dp)
            )
            .padding(start = 12.dp, end = 12.dp),
        verticalArrangement = Arrangement.SpaceEvenly

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Image(
                painter = painterResource(id = icon), contentDescription = null,
                modifier = Modifier
                    .width(36.dp)
                    .height(36.dp)
            )
            Text(
                text = title,
                style = MyCustomOpenSansBold16spStyle,
                modifier = Modifier.padding(start = 4.dp, end = 4.dp)
            )

        }
        Text(
            text = value,
            style = MyCustomOpenSansBold26spStyle,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = description, style = text3Style, modifier = Modifier
                .padding(top = 4.dp, bottom = 8.dp)
                .fillMaxWidth(), textAlign = TextAlign.Center
        )

    }
}




