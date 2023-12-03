package com.example.whiteelephantgiftexchange.data

class RulesData {
    val rules = listOf<String>(
        "Each person who is participating in the White Elephant gift exchange must upload a photo of an UNWRAPPED gift in order to participate.",
        "Each player will be assigned a random number at the start of each game. This number will determine the player's turn during the game.",

        "Round One",
        "The player who has number 1 will select a wrapped gift from the list of gifts, and press the \"unwrap\" button. This will reveal the unwrapped gift image, showing the item to the other participants.",

        "Round Two",
        "The player who has number 2 then has the option to either:",
        "a) Select a new gift from the list, unwrap it and show it to the other participants.",
        "OR",
        "b) Take the unwrapped gift from the first player by pressing the \"steal gift\" button. If this occurs, the first player selects another gift and unwraps it, revealing it to the group.",

        "Subsequent Rounds",
        "Each following round, the player with the next highest number has the option to open a new gift, or take an already opened gift from a previous participant.",
        "Players who have a gift taken from them have the choice to either open a new gift, or take a gift from another player.",
        "A player cannot have the same gift more than once in a round, though the same gift may travel through many hands in a single round.",
        "A player can take a gift they've already had in a later round, if the opportunity arises.",
        "The round ends when each player who has already had a turn has a gift.",

        "Penultimate Round",
        "The player with the last number has the choice to take the last unwrapped gift, or to take any of the opened gifts.",
        "The round ends when a player selects and opens the last unwrapped gift.",

        "Final Round and Wrap Up",
        "The final round goes back to the player with number 1. This player has the option to either keep the gift they ended up with, or force a trade for any other gift.",
        "Once this occurs, the game is over, and each player keeps the gift they have."
    )
}