package com.example.whiteelephantgiftexchange.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.whiteelephantgiftexchange.R
import com.example.whiteelephantgiftexchange.data.RulesData

@Composable
fun RulesScreen(modifier: Modifier = Modifier) {
    RulesList()
}
@Composable
fun RulesList(modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = dimensionResource(id = R.dimen.padding_small))
    ) {
        item {
            Text(
                text = stringResource(id = R.string.game_rules_header),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_medium)),
            )
        }

        RulesData().rules.forEach {
            if (it.contains("Round")) {
                item {
                    Text(
                        text = "$it",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dimensionResource(id = R.dimen.padding_small))
                    )
                }
            } else if (it.startsWith("a)") || it.startsWith("b)") || it.startsWith("OR")) {
                item {
                    Text(
                        text = it,
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(id = R.dimen.padding_small)),
                    )
                }
            } else {

                item {
                    Text(
                        text = " ✦ $it",
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(id = R.dimen.padding_small)),
                    )
                }

            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun RulesScreenPreview() {
    RulesScreen()
}