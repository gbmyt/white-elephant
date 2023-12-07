package com.example.whiteelephantgiftexchange.model

import androidx.compose.ui.res.stringResource
import com.example.whiteelephantgiftexchange.R

class Player (
    val name: String = "",
    val gift: Gift? = null,
    var giftsReceivedThisRound: MutableList<Gift> = mutableListOf()
)