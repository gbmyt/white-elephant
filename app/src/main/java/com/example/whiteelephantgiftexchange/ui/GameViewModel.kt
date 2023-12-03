package com.example.whiteelephantgiftexchange.ui

import androidx.lifecycle.ViewModel
import com.example.whiteelephantgiftexchange.data.PlayerData
import com.example.whiteelephantgiftexchange.model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel: ViewModel() {
    // GAME STATE

    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow(GameUiState()) // Private State
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow() // State the UI can safely consume

    var players: List<Player> = PlayerData().players

    // GAME UTILITIES
    fun resetGame() {
        // shuffle and wrap all gifts
        _uiState.value = GameUiState(round = 0)

    }
    private fun shufflePlayers(): List<Player> {
        return players.shuffled()
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
}