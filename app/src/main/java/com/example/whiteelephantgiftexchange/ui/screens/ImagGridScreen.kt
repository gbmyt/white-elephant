package com.example.whiteelephantgiftexchange.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.whiteelephantgiftexchange.R
import com.example.whiteelephantgiftexchange.exampleData.GiftsDataSource
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GiftImagesGrid(
    onPlayerButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderButtons(onPlayerButtonClicked)

        // Image Grid with two columns
        FlowRow(
            modifier = modifier,
            horizontalArrangement = Arrangement.Center,
            maxItemsInEachRow = 2
        ) {
            GiftsDataSource().gifts.forEach { item ->
                Card(
                    modifier = modifier
                        .size(150.dp)
                        .padding(4.dp)
                        .weight(1f)
                ) {
                    Box(modifier = modifier.fillMaxSize()) {
                        Text(
                            text = "#$item",
                            textAlign = TextAlign.Start,
                            color = Color.DarkGray,
                            modifier = modifier.padding(start = 8.dp)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.gift),
                            contentDescription = null,
                            modifier = modifier
                                .padding(16.dp)
                                .fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderButtons(onPlayerButtonClicked: () -> Unit, modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(vertical = 16.dp)) {
        Button(
            onClick = onPlayerButtonClicked,
            modifier = modifier.weight(1f)
        ) {
            Text(
                text = "Players",
                textAlign = TextAlign.Center,
                modifier = modifier.weight(1f)
            )
        }
    }
}
