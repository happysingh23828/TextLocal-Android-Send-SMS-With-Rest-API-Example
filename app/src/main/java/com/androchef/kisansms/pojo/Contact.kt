package com.androchef.kisansms.pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Contact(
    @SerializedName("about")
    val about: String?,
    @SerializedName("company")
    val company: String?,
    @SerializedName("dob")
    val dob: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("guid")
    val guid: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String?
) : Serializable