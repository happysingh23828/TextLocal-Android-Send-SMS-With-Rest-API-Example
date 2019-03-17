package com.androchef.kisansms.pojo.response

import com.google.gson.annotations.SerializedName

data class TextLocalSMSRespose(
    @SerializedName("balance")
    val balance: Int?,
    @SerializedName("batch_id")
    val batchId: Int?,
    @SerializedName("cost")
    val cost: Int?,
    @SerializedName("custom")
    val custom: String?,
    @SerializedName("message")
    val message: MessageX?,
    @SerializedName("messages")
    val messages: List<Message?>?,
    @SerializedName("num_messages")
    val numMessages: Int?,
    @SerializedName("receipt_url")
    val receiptUrl: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("test_mode")
    val testMode: Boolean?
){
    data class MessageX(
        @SerializedName("content")
        val content: String?,
        @SerializedName("num_parts")
        val numParts: Int?,
        @SerializedName("sender")
        val sender: String?
    )

    data class Message(
        @SerializedName("id")
        val id: String?,
        @SerializedName("recipient")
        val recipient: Long?
    )
}