package com.example.whiteelephantgiftexchange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.whiteelephantgiftexchange.ui.theme.WhiteElephantGiftExchangeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhiteElephantGiftExchangeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WhiteElephantApp()
                }
            }
        }
    }
}

@Composable
fun WhiteElephantApp(modifier: Modifier = Modifier) {
    GiftImagesGrid()
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GiftImagesGrid(modifier: Modifier = Modifier) {
    val itemsList = listOf("2", "3", "4", "5", "6", "7", "8", "1", "2", "3", "4", "A", "5", "6", "7", "8", "1", "2", "3", "4", "A", "B", "C","1", "2", "B", "C", "1", "2", "3", "3", "4", "A")

    Column(
        modifier = modifier.verticalScroll(rememberScrollState()).padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
            Header() // Turn this a real component

            // Image Grid
                FlowRow(
                    modifier = modifier,
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    maxItemsInEachRow = 3
                ) {
                    itemsList.forEach { item ->
                        Card(
                            modifier = modifier
                                .size(100.dp)
                                .padding(horizontal = 4.dp, vertical = 4.dp)
                                .weight(1f)
                        ) {
                            Text(
                                text = "Gift #$item",
                                textAlign = TextAlign.Center,
                                modifier = modifier.fillMaxWidth()
                            )
                        }
                    }
                }
        }


}

@Composable
fun Header(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.app_name),
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxWidth()
    )
    Row(modifier = modifier.padding(vertical = 16.dp)) {
        Text(
            text = "Unwrap Todo",
            textAlign = TextAlign.Center,
            modifier = modifier.weight(1f)
        )
        Text(
            text = "Steal Todo",
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth().weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WhiteElephantGiftExchangeTheme {
        WhiteElephantApp()
    }
}