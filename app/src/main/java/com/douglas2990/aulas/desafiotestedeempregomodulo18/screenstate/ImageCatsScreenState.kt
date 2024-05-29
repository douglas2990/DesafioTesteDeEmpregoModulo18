package com.douglas2990.aulas.desafiotestedeempregomodulo18.screenstate

import androidx.annotation.StringRes
import com.douglas2990.aulas.desafiotestedeempregomodulo18.model.Image

sealed class ImageCatsScreenState {
    data class Success(val data: List<Image>) : ImageCatsScreenState()
    data class Error(@StringRes val messageId: Int): ImageCatsScreenState()
    object Loading : ImageCatsScreenState()
}