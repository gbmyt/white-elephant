package com.example.whiteelephantgiftexchange.ui.screens

import android.util.Log
import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.whiteelephantgiftexchange.R
import com.example.whiteelephantgiftexchange.WhiteElephantGiftExchangeApp
import com.example.whiteelephantgiftexchange.exampleData.GiftData
import com.example.whiteelephantgiftexchange.ui.theme.WhiteElephantGiftExchangeTheme

@Composable
fun GameScreen(
    onPlayerButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(onPlayerButtonClicked)
        ImageGrid()
    }
}
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ImageGrid(modifier: Modifier = Modifier) {
    // Image Grid with two columns
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        maxItemsInEachRow = 2
    ) {
        GiftData().gifts.forEach { item ->
            Card(
                modifier = modifier
                    .size(150.dp)
                    .padding(4.dp)
                    .weight(1f)
            ) {
                Box(modifier = modifier.fillMaxSize().clickable { Log.d("CLICK LOG", "Button Clicked!") }) {
                    Text(
                        text = "Gifter Name",
                        textAlign = TextAlign.Start,
                        color = Color.DarkGray,
                        modifier = modifier.padding(start = 8.dp)
                    )

                    Image(
                        painter = painterResource(id = item.image),
                        contentDescription = null,
                        modifier = modifier
                            .padding(24.dp)
                            .fillMaxSize()
                    )

//                    Text(
//                        text = if (item.isWrapped) "wrapped" else "unwrapped",
//                        fontWeight = if (item.isWrapped) FontWeight.Normal else FontWeight.Bold,
//                        color = Color.DarkGray,
//                        modifier = modifier
//                            .align(Alignment.BottomEnd)
//                            .padding(horizontal = 4.dp)
//                    )
                }
            }
        }
    }
}

@Composable
fun Header(onPlayerButtonClicked: () -> Unit, modifier: Modifier = Modifier) {
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
    Text(
        text = "Round: 0",
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        modifier = modifier.padding(bottom = 16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun WhiteElephantAppPreview() {
    WhiteElephantGiftExchangeTheme {
        WhiteElephantGiftExchangeApp()
    }
}