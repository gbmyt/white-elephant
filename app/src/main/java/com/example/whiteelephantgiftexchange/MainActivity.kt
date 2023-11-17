package com.example.whiteelephantgiftexchange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
    val itemsList = listOf("1", "2", "3", "4", "A", "B", "C","1", "2", "3", "4", "A", "B", "C", "1", "2", "3", "4", "A", "B", "C", "1", "2", "3", "3", "4", "A", "B", "C", "1", "2", "3", "3", "4", "A", "B", "C", "1", "2", "3", "3", "4", "A", "B", "C", "1", "2", "3", "3", "4", "A", "B", "C", "1", "2", "3", "3", "4", "A", "B", "C", "1", "2", "3", "3", "4", "A", "B", "C", "1", "2", "3", "3", "4", "A", "B", "4", "A", "B", "C", "1", "2", "3", "3", "4", "A", "B", "C", "1", "2", "3", "3", "4", "A", "B", "C", "1", "2", "3", "3", "4", "A", "B", "C", "1", "2", "3", "3", "4", "A", "B", "C", "1", "2", "3", "3", "4", "A", "B", "C", "1", "2", "3", "3", "4", "A", "B", "C", "1", "2", "3", "4", "A", "B", "C", "1", "2", "3", "4", "A", "B", "This should be on screen")
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
            // Header
            Text(
                text = stringResource(R.string.app_name),
                modifier = modifier.fillMaxWidth()
            )

            // Image Grid
                FlowRow(
                    modifier = modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    maxItemsInEachRow = 3
                ) {
                    itemsList.forEach { item ->
                        Card(modifier = modifier.weight(1f)) {
                            Text(
                                text = "Gift #$item",
                                modifier = modifier
                            )
                        }
                    }
                }
        }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WhiteElephantGiftExchangeTheme {
        WhiteElephantApp()
    }
}