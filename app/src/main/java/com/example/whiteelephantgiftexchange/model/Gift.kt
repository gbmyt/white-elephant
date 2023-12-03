package com.example.whiteelephantgiftexchange.model

import androidx.annotation.DrawableRes
import com.example.whiteelephantgiftexchange.R

data class Gift(
    @DrawableRes val image: Int,
    var isWrapped: Boolean = true,
    var giftReceiver: Player? = null
)