package com.example.whiteelephantgiftexchange.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.whiteelephantgiftexchange.R
import com.example.whiteelephantgiftexchange.WhiteElephantGiftExchangeApp
import com.example.whiteelephantgiftexchange.model.Player
import com.example.whiteelephantgiftexchange.ui.GameUiState
import com.example.whiteelephantgiftexchange.ui.GameViewModel
import com.example.whiteelephantgiftexchange.ui.theme.WhiteElephantGiftExchangeTheme

@Composable
fun GameScreen(
    onRulesButtonClicked: () -> Unit,
    onPlayerButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = viewModel(),
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_small))
    ) {
        val gameUiState by gameViewModel.uiState.collectAsState()

        Header(gameUiState, gameViewModel, onRulesButtonClicked, onPlayerButtonClicked)
        ImageGrid(players = gameUiState.players)
    }
}
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ImageGrid(
    players: List<Player>,
    modifier: Modifier = Modifier) {
    // Image Grid with two columns
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        maxItemsInEachRow = 2
    ) {
        players.forEach { player ->
            PlayerGiftCard(player = player)
        }
    }
}

@Composable
fun PlayerGiftCard(player: Player, modifier: Modifier = Modifier) {
    val openAlertDialog = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .size(dimensionResource(id = R.dimen.gift_card_size))
            .padding(dimensionResource(id = R.dimen.padding_xs))
            .clickable(
                onClick = {
                    openAlertDialog.value = !openAlertDialog.value
                }
            )
    ) {
        // On card click, An Alert Box should have the user confirm they want to unwrap the selected gift
        Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()) {

            val alertDialogText = when {
                player.gift == null -> stringResource(id = R.string.alertbox_missing_gift_error, player.name)
                (player.gift != null && player.gift.isWrapped) -> stringResource(id = R.string.alertbox_unwrap_gift_confirmation, player.name)
                else -> stringResource(R.string.alertbox_steal_gift_confirmation)
            }

            if (openAlertDialog.value) {
                AlertDialog(
                    text = { Text(text = alertDialogText) },
                    onDismissRequest = { openAlertDialog.value = false },
                    confirmButton = {
                        if (player.gift != null) {
                            TextButton(
                                onClick = {
                                    openAlertDialog.value = false

                                    if (player.gift.isWrapped) {
                                        if (player.gift != null) player.gift.isWrapped = false
                                        // TODO: Set the gift receiver to the player who clicked 'unwrap'
                                    } else {
                                        // Steal gift logic
                                        // player.gift.giftReceiver = currentPlayer
                                    }

                            }) {
                                Text("Confirm")
                            }
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            openAlertDialog.value = false
                        }) { Text("Exit") }
                    }
                )
            }

            if (player.gift?.image == null)  {
                Text(
                    text = stringResource(id = R.string.missing_gift, player.name),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
                )
            } else {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                ) {
                    Text(
                        text = stringResource(id = R.string.gift_from_message, player.name),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_small))
                    )

                    Image(
                        painter = if (player.gift.isWrapped) painterResource(id = R.drawable.gift) else painterResource(
                            id = player.gift.image
                        ),
                        contentDescription = if (player.gift.isWrapped) stringResource(id = R.string.wrapped_gift) else stringResource(
                            id = R.string.unwrapped_gift
                        ),
                        modifier = modifier
                            .size(dimensionResource(id = R.dimen.image_size))
                            // .align(Alignment.Center)
                            .padding(dimensionResource(id = R.dimen.padding_xs))
                    )
                }
            }
        }
    }
}

@Composable
fun Header(
    gameUiState: GameUiState,
    gameViewModel: GameViewModel,
    onRulesButtonClicked: () -> Unit,
    onPlayerButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val startDialog = remember { mutableStateOf(false) }

    Row(modifier = modifier.padding(vertical = 16.dp)) {
        Button(
            onClick = onRulesButtonClicked,
            modifier = modifier.weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.game_rules),
                textAlign = TextAlign.Center
            )
        }

        Button(
            onClick = onPlayerButtonClicked,
            modifier = modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.players),
                textAlign = TextAlign.Center
            )
        }

        Button(
            onClick = { startDialog.value = !startDialog.value },
            modifier = modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            Text(
                text = "Start",
                textAlign = TextAlign.Center
            )

            if (gameViewModel.allPlayersReady && startDialog.value) {
                AlertDialog(
                    onDismissRequest = { startDialog.value = false },
                    confirmButton = {
                        TextButton(onClick = { startDialog.value = false }) {
                            Text(text = "Yay!")
                        }
                    },
                    text = { Text(text = "Starting Game...") }
                )
            } else if (!gameViewModel.allPlayersReady && startDialog.value) {
                AlertDialog(
                    onDismissRequest = { startDialog.value = false },
                    confirmButton = {
                        TextButton(onClick = { startDialog.value = false }) {
                            Text(text = "Okay")
                        }
                    },
                    text = { Text(text = "All Players Must Bring a Gift to Play!") }
                )
            }
        }
    }
    Row {
        Text(
            text = "${stringResource(id = R.string.round_info)} ${gameUiState.round}",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Current Player: ${gameUiState.currentPlayer.name}",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(start = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WhiteElephantAppPreview() {
    WhiteElephantGiftExchangeTheme {
        WhiteElephantGiftExchangeApp()
    }
}