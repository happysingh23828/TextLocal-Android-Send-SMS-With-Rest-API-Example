package com.androchef.kisansms.sendmessage

import android.arch.lifecycle.ViewModel
import com.androchef.kisansms.pojo.post.PostTextLocalSMSData
import com.androchef.kisansms.pojo.response.TextLocalSMSRespose
import com.androchef.kisansms.retrofit.APIUtils
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SendMessageDialogViewModel : ViewModel() {

    var stateEmitter: ObservableEmitter<SendMessageDialogState>? = null
    var state: SendMessageDialogState = SendMessageDialogState()
        set(value) {
            field = value
            if (stateEmitter != null)
                publishState(field)
        }
    val stateObservable: Observable<SendMessageDialogState> by lazy {
        Observable.create<SendMessageDialogState> { emitter ->
            stateEmitter = emitter
        }.startWith(state).share()
    }

    fun sendMessage(postTextLocalSMSData: PostTextLocalSMSData) {
        state = SendMessageDialogState(loading = true, message = "Sending..")
        APIUtils.textLocalApi().sendMessage(getHashMapFromPojo(postTextLocalSMSData))
            .enqueue(object : Callback<TextLocalSMSRespose> {
                override fun onFailure(call: Call<TextLocalSMSRespose>, t: Throwable) {
                    state = SendMessageDialogState(loading = false, sendFailure = true, message = t.message!!)
                }

                override fun onResponse(call: Call<TextLocalSMSRespose>, response: Response<TextLocalSMSRespose>) {
                    state = SendMessageDialogState(
                        loading = false,
                        sendSuccess = true,
                        message = "Sms sent Successfully.."
                    )
                }
            })
    }

    private fun getHashMapFromPojo(postTextLocalSMSData: PostTextLocalSMSData): HashMap<String, Any> {
        val map = HashMap<String, Any>()
        map["apiKey"] = postTextLocalSMSData.apiKey!!
        map["numbers"] = postTextLocalSMSData.numbers!!
        map["test"] = postTextLocalSMSData.test!!
        map["message"] = postTextLocalSMSData.message!!
        return map
    }

    private fun publishState(state: SendMessageDialogState) {
        if (stateEmitter != null) {
            stateEmitter!!.onNext(state)
        } else {
            throw NullPointerException("stateEmitter is null")
        }
    }


}