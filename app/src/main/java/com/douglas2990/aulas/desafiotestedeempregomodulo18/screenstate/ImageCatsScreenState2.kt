package com.douglas2990.aulas.desafiotestedeempregomodulo18.screenstate

import androidx.annotation.StringRes
import com.douglas2990.aulas.desafiotestedeempregomodulo18.model.Data
import com.douglas2990.aulas.desafiotestedeempregomodulo18.model.Image

sealed class ImageCatsScreenState2 {
    data class Success(val data: ArrayList<Image>) : ImageCatsScreenState2()
    //data class Success(val data: List<Data>) : ImageCatsScreenState2()
    data class Error(@StringRes val messageId: Int): ImageCatsScreenState2()
    object Loading : ImageCatsScreenState2()
}