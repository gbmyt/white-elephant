package com.example.whiteelephantgiftexchange.ui

class GameUiState(
    val round: Int = 0,
    val allPlayersReady: Boolean = false, // true when every player has uploaded a gift
)