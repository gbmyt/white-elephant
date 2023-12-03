package com.example.whiteelephantgiftexchange.data

import com.example.whiteelephantgiftexchange.R
import com.example.whiteelephantgiftexchange.model.Gift
import com.example.whiteelephantgiftexchange.model.Player

class PlayerData {
    val players = listOf<Player>(
        Player("Grandma", gift = Gift(R.drawable.gloves),  giftUploaded = true),
        Player("Grandpa", gift = Gift()),
        Player("Mom", gift = Gift()),
        Player("Joe", gift = Gift()),
        Player("Lindsey", gift = Gift(R.drawable.gloves), giftUploaded = true),
        Player("Georgia", gift = Gift()),
        Player("Uncle Roger", gift = Gift()),
        Player("Giselle", gift = Gift()),
        Player("Ronald", gift = Gift()),
        Player("Marie", gift = Gift(R.drawable.gloves), giftUploaded = true),
        Player("Linda", gift = Gift(R.drawable.gloves), giftUploaded = true),
        Player("Georgia", gift = Gift()),
        Player("Uncle Ted", gift = Gift()),
    )
}