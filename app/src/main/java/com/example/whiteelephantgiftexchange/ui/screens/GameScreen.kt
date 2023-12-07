package com.example.whiteelephantgiftexchange.ui.screens

import android.util.Log
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
import androidx.compose.material3.CardDefaults
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
    gameViewModel: GameViewModel,
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
        ImageGrid(players = gameUiState.players, gameViewModel = gameViewModel, gameUiState = gameUiState)
    }
}
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ImageGrid(
    players: List<Player>,
    modifier: Modifier = Modifier,
    gameUiState: GameUiState,
    gameViewModel: GameViewModel
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        maxItemsInEachRow = 2
    ) {
        players.forEach { playerFromList ->
            PlayerGiftCard(player = playerFromList, gameViewModel = gameViewModel, gameUiState = gameUiState)
        }
    }
}

@Composable
fun PlayerGiftCard(
    player: Player,
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel,
    gameUiState: GameUiState
) {
    val openAlertDialog = remember { mutableStateOf(false) }
    var showStealGiftErrorDialog = remember { mutableStateOf(false) }
    var showGameOverDialog = remember { mutableStateOf(false) }

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
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
                if (gameUiState.round <= 0) {

                    AlertDialog(
                        text = { Text(text = stringResource(R.string.start_a_game_msg)) },
                        onDismissRequest = { openAlertDialog.value = false },
                        confirmButton = {
                            TextButton(
                                onClick = { openAlertDialog.value = false }) {
                                Text(stringResource(id = R.string.okay_text))
                            }
                        }
                    )

                } else if (gameUiState.round > 0 && gameUiState.round <= gameUiState.players.size + 1) {
                    AlertDialog(
                        text = { Text(text = alertDialogText) },
                        onDismissRequest = { openAlertDialog.value = false },
                        confirmButton = {
                            if (player.gift != null) {
                                TextButton(
                                    onClick = {
                                        openAlertDialog.value = false

                                        if (player.gift.isWrapped) {
                                            gameViewModel.onUnwrapGift(player = player)
                                        } else {
                                            val stealResult = gameViewModel.onStealGift(player = player)

                                            if (stealResult == R.string.gift_claimed_error) {
                                                showStealGiftErrorDialog.value = true
                                            } else if (stealResult == R.string.game_over_msg) {
                                                showGameOverDialog.value = true
                                            }
                                        }

                                    }) {
                                    Text(stringResource(R.string.confirm_btn_text))
                                }
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = {
                                openAlertDialog.value = false
                            }) { Text(stringResource(R.string.exit_txt)) }
                        }
                    )
                }
            }

            if (showStealGiftErrorDialog.value) {
                AlertDialog(
                    text = { Text(text = stringResource(id = R.string.gift_claimed_error)) },
                    onDismissRequest = { showStealGiftErrorDialog.value = false },
                    confirmButton = {},
                    dismissButton = {
                        TextButton(onClick = {
                            showStealGiftErrorDialog.value = false
                        }) { Text(stringResource(R.string.try_again_text)) }
                    }
                )
            }

            if (showGameOverDialog.value) {
                AlertDialog(
                    text = { Text(text = stringResource(id = R.string.game_over_msg)) },
                    onDismissRequest = {
                        showGameOverDialog.value = false
                   },
                confirmButton = {},
                dismissButton = {
                    TextButton(onClick = {
                        showGameOverDialog.value = false
                        gameViewModel.endGame()
                    }) { Text(stringResource(R.string.play_again_text)) }
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
                    if (player.gift.giftReceiver?.name != null) {
                        Text(
                            text = stringResource(R.string.claimed_by_msg, player.gift.giftReceiver!!.name), // fix this to prevent players from 'receiving' more than one gift at a time
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_small))
                        )
                    }

                    Image(
                        painter = if (player.gift.isWrapped) painterResource(id = R.drawable.gift) else painterResource(
                            id = player.gift.image
                        ),
                        contentDescription = if (player.gift.isWrapped) stringResource(id = R.string.wrapped_gift) else stringResource(
                            id = R.string.unwrapped_gift
                        ),
                        modifier = modifier
                            .size(dimensionResource(id = R.dimen.image_size))
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
            onClick = {
                if (gameUiState.round == 0) {
                    startDialog.value = true
                    gameViewModel.onGameStart()
                }
            },
            modifier = modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.start_text),
                textAlign = TextAlign.Center
            )

            if (startDialog.value) {
                AlertDialog(
                    onDismissRequest = { startDialog.value = false },
                    confirmButton = {
                        TextButton(onClick = { startDialog.value = false }) {
                            Text(text = stringResource(R.string.okay_text))
                        }
                    },
                    text = { Text(text = if (gameViewModel.allPlayersReady) stringResource(R.string.starting_new_game_msg, gameUiState.currentPlayer.name) else stringResource(
                        R.string.start_new_game_error_msg
                    )
                    ) }
                )
            }
        }
    }

    if (gameUiState.round > 0) {
        Row {
            Text(
                text = "${stringResource(id = R.string.round_info)} ${gameUiState.round}",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(bottom = 16.dp)
            )

            Text(
                text = stringResource(R.string.current_player, gameUiState.currentPlayer.name),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(start = 16.dp)
            )

            Text(
                text = "Final Round: ${gameViewModel.isFinalRound}",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(start = 16.dp)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun WhiteElephantAppPreview() {
    WhiteElephantGiftExchangeTheme {
        WhiteElephantGiftExchangeApp()
    }
}