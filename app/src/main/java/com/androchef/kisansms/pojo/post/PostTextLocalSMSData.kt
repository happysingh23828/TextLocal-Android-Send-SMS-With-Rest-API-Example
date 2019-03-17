package com.androchef.kisansms.pojo.post

import com.google.gson.annotations.SerializedName

data class PostTextLocalSMSData(
    @SerializedName("apiKey")
    var apiKey: String? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("numbers")
    var numbers: String? = null,
    @SerializedName("test")
    var test: Boolean? = false
)