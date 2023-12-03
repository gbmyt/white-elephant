package com.example.whiteelephantgiftexchange.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.whiteelephantgiftexchange.data.PlayerData
import com.example.whiteelephantgiftexchange.model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel: ViewModel() {
    // GAME STATE
    val allPlayersReady: Boolean = false // true when every player has uploaded a gift

    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow(GameUiState()) // Private State
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow() // State the UI can safely consume

    // GAME UTILITIES
    private fun resetGame() {
        // shuffle and wrap all gifts
        val players = PlayerData().players.shuffled()
        _uiState.value = GameUiState()
    }
    fun shufflePlayers() {
        val shuffledPlayers = _uiState.value.players.shuffled()
        _uiState.update { currentState ->
            currentState.copy(players = shuffledPlayers, currentPlayer = shuffledPlayers[0])
        }
    }

    // Game Util TODOs
        // fun onTakeOrUploadImage() {} // Not sure this should be defined here
        // fun onGameStart() {} ??
        // fun onStartRound() {}
        // fun onEndRound() {}

    // Round-Level TODOs
        // fun onSteal() {}
        // fun onUnwrap() {}
        // fun onChooseGift() {}

    init {
        resetGame()
    }
}