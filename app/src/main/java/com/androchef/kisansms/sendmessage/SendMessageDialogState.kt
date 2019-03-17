package com.androchef.kisansms.sendmessage

data class SendMessageDialogState(
    var loading : Boolean = false,
    var sendSuccess : Boolean = false,
    var sendFailure : Boolean = false,
    var message : String = ""
)