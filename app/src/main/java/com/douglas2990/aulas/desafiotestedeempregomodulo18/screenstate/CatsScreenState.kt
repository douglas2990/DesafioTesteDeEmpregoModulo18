package com.douglas2990.aulas.desafiotestedeempregomodulo18.screenstate

import androidx.annotation.StringRes
import com.douglas2990.aulas.desafiotestedeempregomodulo18.model.Data

sealed class CatsScreenState {
    data class Success(val data: List<Data>) : CatsScreenState()
    data class Error(@StringRes val messageId: Int): CatsScreenState()
    object Loading : CatsScreenState()
}