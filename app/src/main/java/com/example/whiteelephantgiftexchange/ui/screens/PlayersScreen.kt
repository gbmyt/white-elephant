package com.example.whiteelephantgiftexchange.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.whiteelephantgiftexchange.R
import com.example.whiteelephantgiftexchange.data.PlayerData
import com.example.whiteelephantgiftexchange.ui.GameViewModel

@Composable
fun PlayersScreen(gameViewModel: GameViewModel, modifier: Modifier = Modifier) {
    PlayersList(gameViewModel)
}
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PlayersList(gameViewModel: GameViewModel, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        val gameUiState by gameViewModel.uiState.collectAsState()

        FlowRow(maxItemsInEachRow = 2, modifier = modifier.padding(16.dp)) {
            Button(
                content = { Text(text = stringResource(R.string.import_contacts)) },
                onClick = { /*TODO*/ },
                modifier = modifier.weight(1f)
            )
            Button(
                content = {
                    Icon(
                        painter = painterResource(R.drawable.upload_24px),
                        contentDescription = null,
                        modifier = modifier.padding(end = 4.dp))
                    Text(text = stringResource(R.string.add_gift))
                },
                onClick = { /* onTakePhotoOrUpload() TODO*/ },
                modifier = modifier
                    .weight(1f)
                    .padding(start = 4.dp)
            )
            Button(
                content = {
                    Icon(
                        painter = painterResource(R.drawable.shuffle_24px),
                        contentDescription = null,
                        modifier = modifier.padding(end = 4.dp))
                    Text(text = "Shuffle Player Turns")
                },
                onClick = { gameViewModel.shufflePlayers() },
                modifier = modifier.weight(1f)
            )
        }

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        ) {
            Text(
                text = "Ready",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = modifier.weight(1f)
            )

            Text(
                text = "Players",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = modifier.weight(2f)
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxSize()
        ) {
            gameUiState.players.forEachIndexed { index, player ->
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(start = 48.dp)
                    ) {

                        if (player.gift != null) {
                            Icon(
                                painter = painterResource(id = R.drawable.done_24px),
                                contentDescription = null,
                                modifier = modifier.padding(start = 16.dp, end = 24.dp)
                            )
                        }  else {
                            Spacer(modifier = modifier.width(66.dp))
                        }

                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(24.dp)
                                .wrapContentHeight(align = Alignment.CenterVertically)
                        ) {
                            Text(
                                text = "Player $index: ",
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.End
                            )
                            Text(text = "${player.name}", textAlign = TextAlign.End)
                        }
                    }
                    Divider()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlayersListPreview() {
    val gameViewModel: GameViewModel = viewModel()
    PlayersScreen(gameViewModel)
}
