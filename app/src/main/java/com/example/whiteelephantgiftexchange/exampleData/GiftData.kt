package com.example.whiteelephantgiftexchange.exampleData

import com.example.whiteelephantgiftexchange.R
import com.example.whiteelephantgiftexchange.model.Gift

class GiftData {
    val gifts = listOf<Gift>(
        Gift(),
        Gift(),
        Gift(R.drawable.gloves, isWrapped = false),
        Gift(),
        Gift(),
        Gift(R.drawable.gloves, isWrapped = false),
        Gift(R.drawable.gift, isWrapped = false),
        Gift(),
        Gift(),
        Gift(),
        Gift(),
        Gift(),
        Gift(R.drawable.gloves, isWrapped = false),
        Gift(),
        Gift(),
    )
}