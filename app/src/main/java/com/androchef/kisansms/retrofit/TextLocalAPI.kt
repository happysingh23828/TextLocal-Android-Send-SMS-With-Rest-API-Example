package com.androchef.kisansms.retrofit

import com.androchef.kisansms.pojo.response.TextLocalSMSRespose
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface TextLocalAPI {
    @POST("/send")
    fun sendMessage(@QueryMap map : HashMap<String,Any>?) : Call<TextLocalSMSRespose>
}