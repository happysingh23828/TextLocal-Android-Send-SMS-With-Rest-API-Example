package com.androchef.kisansms.ui.sendmessage

import android.arch.lifecycle.ViewModel
import com.androchef.kisansms.pojo.request.PostTextLocalSMSData
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

        if(checkItIsNotDummyNumber(postTextLocalSMSData.numbers)){
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
        } else{
            state = SendMessageDialogState(loading = false, sendFailure = true, message = "This is Dummy number \n Please select KISSAN Testing Phone to check Cases from List\n")
        }

    }

    private fun checkItIsNotDummyNumber(number: String?): Boolean {
        return number!!.contains("+91-9971792703")
    }

    private fun getHashMapFromPojo(postTextLocalSMSData: PostTextLocalSMSData): HashMap<String, Any> {
        val map = HashMap<String, Any>()
        map[API_KEY] = postTextLocalSMSData.apiKey!!
        map[NUMBERS] = postTextLocalSMSData.numbers!!.replace("+91-","91")
        map[TEST] = postTextLocalSMSData.test!!
        map[MESSAGE] = postTextLocalSMSData.message!!
        return map
    }

    private fun publishState(state: SendMessageDialogState) {
        if (stateEmitter != null) {
            stateEmitter!!.onNext(state)
        } else {
            throw NullPointerException("stateEmitter is null")
        }
    }


    companion object {
        private const val API_KEY = "apiKey"
        private const val NUMBERS = "numbers"
        private const val TEST = "test"
        private const val MESSAGE = "message"
    }


}