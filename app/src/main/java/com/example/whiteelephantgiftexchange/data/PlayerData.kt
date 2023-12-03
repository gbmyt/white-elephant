package com.example.whiteelephantgiftexchange.data

import com.example.whiteelephantgiftexchange.R
import com.example.whiteelephantgiftexchange.model.Gift
import com.example.whiteelephantgiftexchange.model.Player

class PlayerData {
    val players = listOf<Player>(
        Player("Grandma"),
        Player("Grandpa", gift = Gift(R.drawable.gloves, false)),
        Player("Mom"),
        Player("Joe", gift = Gift(R.drawable.gloves, false)),
        Player("Lindsey"),
        Player("Georgia", gift = Gift(R.drawable.gloves)),
        Player("Uncle Roger", gift = Gift(R.drawable.gloves)),
        Player("Giselle", gift = Gift(R.drawable.gloves)),
        Player("Ronald", gift = Gift(R.drawable.gloves, false)),
        Player("Marie", gift = Gift(R.drawable.gloves)),
        Player("Linda", gift = Gift(R.drawable.gloves)),
        Player("Georgia", gift = Gift(R.drawable.gloves, false)),
        Player("Uncle Ted"),
    )
}