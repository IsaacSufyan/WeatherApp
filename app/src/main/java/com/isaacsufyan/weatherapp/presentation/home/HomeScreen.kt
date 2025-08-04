package com.isaacsufyan.weatherapp.presentation.home

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.isaacsufyan.coreui.component.ErrorCard
import com.isaacsufyan.coreui.component.ProgressBar
import com.isaacsufyan.coreui.reusable.SearchViewAutoComplete
import com.isaacsufyan.coreui.reusable.IsaacSurface
import com.isaacsufyan.coreui.theme.MyCustomLargePoppinStyle
import com.isaacsufyan.coreui.theme.MyCustomMediumPoppinStyle
import com.isaacsufyan.coreui.theme.MyCustomPoppinBoldStyle
import com.isaacsufyan.coreui.theme.White
import com.isaacsufyan.coreui.theme.gradientColor3E2D8F
import com.isaacsufyan.coreui.theme.gradientColor8247a4
import com.isaacsufyan.weatherapp.R
import com.isaacsufyan.weatherapp.business.domain.model.ForeCastHours
import com.isaacsufyan.weatherapp.business.domain.model.Weather
import com.isaacsufyan.weatherapp.presentation.reusable.WeatherItem
import com.isaacsufyan.weatherapp.utils.ErrorCardConsts
import com.isaacsufyan.weatherapp.utils.ExceptionTitles
import com.isaacsufyan.weatherapp.utils.SetError
import com.isaacsufyan.weatherapp.utils.extractDateFromString
import com.isaacsufyan.weatherapp.utils.extractHourFromString
import com.isaacsufyan.weatherapp.utils.getCurrentTime
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onButtonClicked: (Weather) -> Unit
) {

    val weatherUIState by viewModel.weatherData.collectAsStateWithLifecycle()
    val cityList by viewModel.cities.collectAsStateWithLifecycle()

    when (weatherUIState) {
        is UiState.Loading -> {
            ProgressBar()
        }

        is UiState.Success -> {
            val apiResponse = (weatherUIState as UiState.Success).data
            HomeScreenUI(
                apiResponse = apiResponse,
                selectedCity = { viewModel.getLocationByCityName(it) },
                openWeatherDetail = { onButtonClicked(apiResponse) },
                cityList = cityList
            )
        }

        is UiState.Error -> {
            var showDialog by remember { mutableStateOf(true) }
            val context = LocalContext.current
            if (showDialog) {
                ErrorCard(
                    modifier = Modifier
                        .fillMaxSize(),
                    errorTitle = (weatherUIState as UiState.Error).message
                        ?: ExceptionTitles.UNKNOWN_ERROR,
                    errorDescription = SetError.setErrorCard(
                        (weatherUIState as UiState.Error).message
                            ?: ExceptionTitles.UNKNOWN_ERROR
                    ),
                    errorButtonText = ErrorCardConsts.BUTTON_TEXT,
                    cardModifier = Modifier
                        .fillMaxWidth()
                        .height(LocalConfiguration.current.screenHeightDp.dp / 4 + 48.dp)
                        .padding(horizontal = 64.dp),
                ) {
                    showDialog = false
                    viewModel.getLocationByCityName()
                    Toast.makeText(context, "Get Location By City Name", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}


@Composable
private fun HomeScreenUI(
    apiResponse: Weather,
    selectedCity: (String) -> Unit,
    openWeatherDetail: () -> Unit,
    cityList: ImmutableList<String>
) {
    IsaacSurface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            SearchSection(
                selectedCity = selectedCity,
                openWeatherDetail = openWeatherDetail,
                cityList = cityList
            )
            TopSection(apiResponse)
            BottomSection(apiResponse)
        }
    }
}

@Composable
private fun SearchSection(
    selectedCity: (String) -> Unit,
    openWeatherDetail: () -> Unit,
    cityList: ImmutableList<String>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
    ) {

        SearchViewAutoComplete(
            modifier = Modifier
                .weight(1f),
            items = cityList,
            selectedItem = { selectedCity(it) }
        )

        IconButton(
            modifier = Modifier.padding(start = 10.dp),
            onClick = { openWeatherDetail() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "Menu",
                tint = Color.White
            )
        }
    }
}

@Composable
private fun TopSection(apiResponse: Weather) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Text(
            text = apiResponse.location?.name ?: "",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp),
            style = MyCustomPoppinBoldStyle
        )

        if (apiResponse.currentWeather?.condition?.icon == null)
            Image(
                painter = painterResource(id = R.drawable.splash_logo),
                contentDescription = "home",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .width(200.dp)
            )
        else
            AsyncImage(
                "https:${apiResponse.currentWeather.condition.icon}",
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .width(100.dp),
                contentScale = ContentScale.Fit,
                placeholder =  painterResource(id = R.drawable.splash_logo)
            )
        Text(
            text = stringResource(R.string.temp_home).format(apiResponse.currentWeather?.tempC),
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            style = MyCustomPoppinBoldStyle
        )
        Text(
            text = stringResource(R.string.today_weather),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MyCustomLargePoppinStyle
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = stringResource(R.string.temp_min).format(apiResponse.forecast!!.foreCastList[0].day.mintemp_c),
                style = MyCustomLargePoppinStyle
            )
            Text(
                text = stringResource(R.string.temp_max).format(apiResponse.forecast.foreCastList[0].day.maxtemp_c),
                style = MyCustomLargePoppinStyle
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_house),
            contentDescription = "home",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(top = 40.dp)
                .fillMaxWidth()
                .height(200.dp)
                .width(300.dp)
        )
    }
}

@Composable
private fun BottomSection(apiResponse: Weather) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        elevation = 1.dp,
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
                ),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp, end = 40.dp, top = 24.dp, bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.today),
                    style = MyCustomLargePoppinStyle,
                )
                Text(
                    text = extractDateFromString(apiResponse.location?.localtime),
                    style = MyCustomLargePoppinStyle
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(White)
            )

            val hoursList = apiResponse.forecast!!.foreCastList[0].listOfHours

            val coroutineScope = rememberCoroutineScope()
            val lazyRowState = rememberLazyListState()

            LaunchedEffect(key1 = Unit){
                coroutineScope.launch {
                    lazyRowState.animateScrollToItem(getCurrentTime(hoursList))
                }
            }


            LazyRow(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(start = 4.dp, end = 8.dp),
                state = lazyRowState
            )
            {
                itemsIndexed(hoursList) { index: Int, item: ForeCastHours ->
                    WeatherItem(
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .width(82.dp)
                            .height(172.dp)
                            .padding(vertical = 8.dp),
                        stringResource(id = R.string.temp_degree).format(item.temperatureCelsius),
                        extractHourFromString(item.time),
                        item.condition.icon
                    )
                }
            }
        }
    }
}

@Composable
private fun ItemWeatherDetail(
    title: String? = "",
    desc: String? = "",
    @DrawableRes icon: Int? = R.drawable.sunny_rainy
) {
    Column(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = title ?: "", style = MyCustomMediumPoppinStyle)
        Image(
            painter = painterResource(id = icon ?: R.drawable.sunny_rainy),
            modifier = Modifier
                .width(50.dp)
                .height(50.dp), contentDescription = null
        )
        Text(text = desc ?: "", style = MyCustomMediumPoppinStyle)

    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreenUI(
        apiResponse = Weather(),
        selectedCity = {},
        openWeatherDetail = { },
        cityList = persistentListOf("").toImmutableList()
    )
}