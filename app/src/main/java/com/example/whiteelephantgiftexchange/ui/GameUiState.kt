package com.example.whiteelephantgiftexchange.ui

import com.example.whiteelephantgiftexchange.data.PlayerData
import com.example.whiteelephantgiftexchange.model.Gift
import com.example.whiteelephantgiftexchange.model.Player

/**
 * Data class that represents the game UI state
 */

data class GameUiState(
    val round: Int = 0,
    var players: List<Player> = PlayerData().players,
    val currentPlayer: Player = players[0],
    val gifts: List<Gift> = PlayerData().players.map { it.gift!! }
)