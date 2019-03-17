package com.androchef.kisansms.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIUtils {

    fun textLocalApi() : TextLocalAPI{
        val baseUrl = "https://api.textlocal.in"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(TextLocalAPI::class.java)
    }
}