package com.douglas2990.aulas.desafiotestedeempregomodulo18.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZF91c3VhcmlvIjo0LCJpYXQiOjE3MDQ5ODIxNDUsImV4cCI6MTcwNTk4MjE0NH0.OYYVH8FpppTRHjQu_OVDff8Y1VsDyQlYDO5mSr5rVv4"

        val construtorRequisicao = chain.request().newBuilder()
        val requestBuilder = chain.request().newBuilder()

        val request = requestBuilder.addHeader(
            "Authorization", "Client-ID 1ceddedc03a5d71"
        ).build()

        val requisicao = construtorRequisicao.addHeader(
            "Authorization","Bearer ${TOKEN}"
        ).build()

        return chain.proceed( request )
    }
}