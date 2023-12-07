package com.example.whiteelephantgiftexchange.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.whiteelephantgiftexchange.R
import com.example.whiteelephantgiftexchange.model.Gift
import com.example.whiteelephantgiftexchange.model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel: ViewModel() {
    // GAME STATE
    var allPlayersReady: Boolean = false // true when every player has uploaded a gift
    private var isFinalRound: Boolean = false // is true when all players have gone once, but the first player still needs to choose whether to keep or steal their gift.

    // Use a backing property to preserve private state from external modifications
    private val _uiState = MutableStateFlow(GameUiState()) // Private State
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow() // State the UI can safely consume

    private val playerCount = _uiState.value.players.size

    // GAME UTILITIES
    private fun resetGame() {
        // shuffle and wrap all gifts
        _uiState.value = GameUiState(round = 0)
        shufflePlayers()
    }
    fun shufflePlayers() {
        val shuffledPlayers = _uiState.value.players.shuffled()
        _uiState.update { currentState ->
            currentState.copy(players = shuffledPlayers, currentPlayer = if (currentState.round == 0) shuffledPlayers[0] else currentState.currentPlayer)
        }
    }

    // Game Util TODOs
    // fun onTakeOrUploadImage() {}
    private fun onStartRound() {
        _uiState.update { currentState ->
            currentState.copy(round = currentState.round + 1)
        }
    }

    // check if photos uploaded, set allPlayersReady to 'true'
    // there should be as many photos of gifts as there are players
    // each player should only bring 1 gift
    // get all the Players' then filter out their gifts, then filter out any null gifts
    fun onGameStart() {
        val notReadyPlayers = _uiState.value.players.filter { it.gift == null }

        if (notReadyPlayers.isEmpty()) {
            allPlayersReady = true
            onStartNewRound()
        } else if (notReadyPlayers.isNotEmpty()) {
            Log.d("ERROR", "All Players Must Upload a Gift to Play!")
        } else {
            Log.d("ERROR", "Unknown Error Occurred when attempting to start the game")
        }
    }
    private fun onStartNewRound() {
            // Clear player records of gifts received during the previous round
            _uiState.value.gifts.forEach {
                if (it.giftReceiver != null) {
                    val player = it.giftReceiver
                    player?.giftsReceivedThisRound =  mutableListOf(it)
                }
            }

            // Update the current round int whether or not its the final round
            _uiState.update { currentState ->
                val nextPlayerIndex = currentState.round

                // if it is NOT the final round update to the next player from the shuffled list of players
                if (currentState.round <= playerCount && currentState.round != 0) {
                    currentState.copy(round = currentState.round + 1, currentPlayer = currentState.players[nextPlayerIndex])
                } else {
                    // otherwise just set the current player to P1 and set finalRound to 'true'
                    if (currentState.round !== 0) isFinalRound = true
                    currentState.copy(round = currentState.round + 1, currentPlayer = currentState.players[0])
                }
            }
        }

    // Round-Level TODOs
        // fun onChooseGift() {}

        fun onStealGift(player: Player) {
            if (
                (player.gift?.giftReceiver != null) &&
                (player.gift.giftReceiver != _uiState.value.currentPlayer) &&
                (!player.gift.isWrapped)
            ) {
                if (_uiState.value.currentPlayer.giftsReceivedThisRound.contains(player.gift)) {
                    Log.d("ERROR", "You can't steal a gift that you've already claimed during this round.")
                    "You can't steal a gift that you've already claimed during this round."
                } else {
                    val oldGiftReceiver: Player = player.gift.giftReceiver!!
                    _uiState.value.currentPlayer.giftsReceivedThisRound.add(player.gift)
                    player.gift.giftReceiver = _uiState.value.currentPlayer

                    // ❌ TODO: Some kind of alert to let players know the currentPlayer/round has changed
                    _uiState.update { currentState ->
                        currentState.copy(currentPlayer = oldGiftReceiver)
                    }
                }

                Log.d("ERROR", _uiState.value.currentPlayer.giftsReceivedThisRound.toString())
            } else if ((player.gift?.giftReceiver != null) && (player.gift.giftReceiver == _uiState.value.currentPlayer)) {
                Log.d("ERROR", "You can't steal a gift that you've already claimed.")
            } else {
                Log.d("ERROR", "You can't steal an unopened gift.")
            }
        }

        fun onUnwrapGift(player: Player) {
            if (player.gift != null) {
                player.gift.isWrapped = false
                player.gift?.giftReceiver = _uiState.value.currentPlayer
                _uiState.value.currentPlayer.giftsReceivedThisRound.add(player.gift)
                onStartNewRound()
            } else {
                "You cant open a missing gift"
            }
        }

    init {
        resetGame()
    }
}