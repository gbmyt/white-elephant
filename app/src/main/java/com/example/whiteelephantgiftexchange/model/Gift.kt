package com.example.whiteelephantgiftexchange.model

import androidx.annotation.DrawableRes
import com.example.whiteelephantgiftexchange.R

data class Gift(
    @DrawableRes val image: Int = R.drawable.gift,
    val isWrapped: Boolean = true,
//    val gifter: Player,
//    val receiver: Player
)