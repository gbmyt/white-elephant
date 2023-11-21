package com.example.whiteelephantgiftexchange.ui

import androidx.lifecycle.ViewModel
import com.example.whiteelephantgiftexchange.exampleData.PlayerData
import com.example.whiteelephantgiftexchange.model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel: ViewModel() {
    // GAME STATE
    private val _uiState = MutableStateFlow(GameUiState()) // Private State
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow() // State the UI can safely consume

    // GAME UTILITIES
    fun resetGame() {
        // set round to zero
        // wrap all gifts
        shufflePlayers()
    }
    private fun shufflePlayers(): List<Player> {
        return PlayerData().players.shuffled()
    }

    // onSteal() {}
    // onUnwrap() {}
    // onChooseGift() {}
}