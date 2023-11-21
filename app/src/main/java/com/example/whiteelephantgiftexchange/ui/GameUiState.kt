package com.example.whiteelephantgiftexchange.ui

import com.example.whiteelephantgiftexchange.model.Player

class GameUiState(
    val round: Int = 0,
    val allPlayersReady: Boolean = false, // true when every player has uploaded a gift
)