package com.douglas2990.aulas.desafiotestedeempregomodulo18.api

import com.douglas2990.aulas.desafiotestedeempregomodulo18.model.ImgurModelCats
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

class RestManager{
    companion object {

        private fun createHttpClient(): OkHttpClient.Builder {

            val client = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)

                val logInterceptor = HttpLoggingInterceptor()
                logInterceptor.level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(logInterceptor)

            return client
        }

        fun getEndpoints(): IEndpoints {
            val gson = GsonBuilder().setLenient().create()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.imgur.com/3/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(
                    OkHttpClient.Builder()
                    //createHttpClient()
                        .addInterceptor( AuthInterceptor() )
                        .build()
                )
                .build()
            return retrofit.create(IEndpoints::class.java)
        }

        interface IEndpoints {
            @GET("gallery/search/")
            fun getCats(
                @Query("q") q: String
            ): Call<ImgurModelCats>
            //fun getCats(): Response<ImgurModelCats>

        }
    }
}