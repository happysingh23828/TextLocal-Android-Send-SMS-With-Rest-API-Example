package com.androchef.kisansms.pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Message(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("phone")
    var phone: String? = null,
    @SerializedName("otp")
    var otp: String? = null,
    @SerializedName("time")
    var time: Long? = null
) : Serializable