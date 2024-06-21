package com.douglas2990.aulas.desafiotestedeempregomodulo18.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.douglas2990.aulas.desafiotestedeempregomodulo18.R
import com.douglas2990.aulas.desafiotestedeempregomodulo18.api.RestManager
import com.douglas2990.aulas.desafiotestedeempregomodulo18.model.ImgurModelCats
import com.douglas2990.aulas.desafiotestedeempregomodulo18.screenstate.ImageCatsScreenState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageCatsViewModel: ViewModel() {
    //val zero = 0
    val state: MutableLiveData<ImageCatsScreenState> by lazy{
        MutableLiveData<ImageCatsScreenState>()
    }
    init {
        state.value = ImageCatsScreenState.Loading
        RestManager.getEndpoints().getCats("cats").enqueue(object : Callback<ImgurModelCats> {
            override fun onResponse(
                call: Call<ImgurModelCats>,
                response: Response<ImgurModelCats>
            ) {
                response.body()?.let { body ->
                    state.value = ImageCatsScreenState.Success(body.data[0].images)


                    /*
                    state.value = ImageCatsScreenState.Success(
                        body.data.forEach { dados ->
                            dados.images[0]
                    })

                     */

                } ?: run {state.value = ImageCatsScreenState.Error(R.string.app_name)}
            }

            override fun onFailure(call: Call<ImgurModelCats>, t: Throwable) {
                state.value = ImageCatsScreenState.Error(R.string.app_name)
            }
        })
    }
}

