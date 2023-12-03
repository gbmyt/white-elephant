package com.example.whiteelephantgiftexchange.model

import androidx.annotation.DrawableRes
import com.example.whiteelephantgiftexchange.R

data class Gift(
    @DrawableRes var image: Int = R.drawable.gift,
    val isWrapped: Boolean = true,
)