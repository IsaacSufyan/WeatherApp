package com.isaacsufyan.weatherapp.presentation.splash

import android.app.Activity
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.isaacsufyan.coreui.reusable.IsaacSurface
import com.isaacsufyan.coreui.theme.Orange
import com.isaacsufyan.weatherapp.BuildConfig
import com.isaacsufyan.weatherapp.R
import com.isaacsufyan.weatherapp.presentation.navigation.AppScreen
import com.isaacsufyan.weatherapp.presentation.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    var isAdShown by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(true) {
        delay(3000L)

        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            context,
            BuildConfig.ADMOB_INTERSTITIAL_ID,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    if (!isAdShown) {
                        isAdShown = true
                        interstitialAd.fullScreenContentCallback =
                            object : FullScreenContentCallback() {
                                override fun onAdDismissedFullScreenContent() {
                                    navController.navigate(AppScreen.HomeScreen.route) {
                                        popUpTo(AppScreen.SplashScreen.route) { inclusive = true }
                                    }
                                }

                                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                                    navController.navigate(AppScreen.HomeScreen.route) {
                                        popUpTo(AppScreen.SplashScreen.route) { inclusive = true }
                                    }
                                }
                            }
                        interstitialAd.show(context as Activity)
                    }
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    navController.navigate(AppScreen.HomeScreen.route) {
                        popUpTo(AppScreen.SplashScreen.route) { inclusive = true }
                    }
                }
            }
        )
    }

    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(3000L)
        navController.navigate(Routes.homeScreen) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }

    IsaacSurface {
        val text = remember {
            AnnotatedString.Builder().apply {
                withStyle(style = SpanStyle(color = Color.White)) {
                    append("Weather ")
                }
                withStyle(style = SpanStyle(color = Orange)) {
                    append("Forecast")
                }
            }.toAnnotatedString()
        }

        Column(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash_logo),
                contentDescription = "splash",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 64.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .scale(scale.value)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )
        }
    }
}