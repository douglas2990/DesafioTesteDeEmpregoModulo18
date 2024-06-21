package com.douglas2990.aulas.desafiotestedeempregomodulo18.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.douglas2990.aulas.desafiotestedeempregomodulo18.R
import com.douglas2990.aulas.desafiotestedeempregomodulo18.api.RestManager
import com.douglas2990.aulas.desafiotestedeempregomodulo18.model.Image
import com.douglas2990.aulas.desafiotestedeempregomodulo18.model.ImgurModelCats
import com.douglas2990.aulas.desafiotestedeempregomodulo18.screenstate.ImageCatsScreenState2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImagesCatsViewModel2 : ViewModel() {
    //val zero = 0
    val state: MutableLiveData<ImageCatsScreenState2> by lazy{
        MutableLiveData<ImageCatsScreenState2>()
    }
    init {
        state.value = ImageCatsScreenState2.Loading
        RestManager.getEndpoints().getCats("cats").enqueue(object : Callback<ImgurModelCats> {
            override fun onResponse(
                call: Call<ImgurModelCats>,
                response: Response<ImgurModelCats>
            ) {
                response.body()?.let { body ->
                    //state.value = CatsScreenState.Success(body.data)
                    val listaUrlImagens2 = ArrayList<Image>()
                    //val listaUrlImagens2 = body.data
                    for (i in 0 ..< body.data.size) {
                        for (y in 0 ..< body.data[i].images.size){
                            listaUrlImagens2.add(body.data[i].images[y])
                            state.value = ImageCatsScreenState2.Success(
                                listaUrlImagens2
                            )

                        }

                    }

                } ?: run {state.value = ImageCatsScreenState2.Error(R.string.app_name)}
            }

            override fun onFailure(call: Call<ImgurModelCats>, t: Throwable) {
                state.value = ImageCatsScreenState2.Error(R.string.app_name)
            }
        })
    }
}