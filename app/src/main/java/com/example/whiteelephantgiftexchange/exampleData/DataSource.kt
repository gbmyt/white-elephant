package com.example.whiteelephantgiftexchange.exampleData

import com.example.whiteelephantgiftexchange.model.Player

class DataSource {
    val players = listOf<Player>(
        Player("Grandma", giftUploaded = true),
        Player("Grandpa"),
        Player("Mom"),
        Player("Joe"),
        Player("Lindsey", giftUploaded = true),
        Player("Georgia"),
        Player("Uncle Roger"),
        Player("Marie", giftUploaded = true),
    )
}