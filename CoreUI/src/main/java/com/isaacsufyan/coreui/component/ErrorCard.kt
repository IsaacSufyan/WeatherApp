package com.isaacsufyan.coreui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.isaacsufyan.coreui.R

@Composable
fun ErrorCard(
    modifier: Modifier = Modifier,
    errorTitle: String,
    errorDescription: String,
    errorButtonText: String,
    cardModifier: Modifier,
    onClick: () -> Unit = {}
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Card(modifier = cardModifier) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(gradientBackgroundBrush()),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        Image(
                            modifier = Modifier.size(64.dp),
                            painter = painterResource(id = R.drawable.ic_error),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                        Text(text = errorTitle, style = MaterialTheme.typography.h3)
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = errorDescription,
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.primary
                        )
                        Button(
                            onClick = onClick,
                            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.error),
                            shape = RoundedCornerShape(50)
                        ) {
                            Text(
                                text = errorButtonText.uppercase(),
                                style = MaterialTheme.typography.button
                            )
                        }
                    }
                }
            }
        }
    }
}