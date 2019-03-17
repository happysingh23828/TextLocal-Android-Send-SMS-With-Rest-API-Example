package com.androchef.kisansms.sendmessage

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.androchef.kisansms.R
import com.androchef.kisansms.databinding.LayoutFragmentSendMessageDialogBinding
import com.androchef.kisansms.db.SqlLiteHelper
import com.androchef.kisansms.pojo.Contact
import com.androchef.kisansms.pojo.Message
import com.androchef.kisansms.pojo.post.PostTextLocalSMSData
import kotlinx.android.synthetic.main.layout_fragment_send_message_dialog.*
import kotlin.random.Random


class SendMessageDialogFragment : DialogFragment() {
    companion object {
        const val CONTACT_KEY = "contact_key"
    }

    lateinit var dataBinding: LayoutFragmentSendMessageDialogBinding
    lateinit var viewModel: SendMessageDialogViewModel
    var contact: Contact? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.layout_fragment_send_message_dialog, container, false)
        viewModel = ViewModelProviders.of(this).get(SendMessageDialogViewModel::class.java)
        handleArguments()
        onClicks()
        setStateObservable()
        return dataBinding.root
    }

    private fun handleArguments() {
        arguments?.let {
            contact = it.getSerializable(CONTACT_KEY) as Contact
            setDetails()
        }
    }

    private fun onClicks() {
        dataBinding.btnOtpSend.setOnClickListener {
            if (isNetworkConnected())
                sendMessage()
            else {
                showMessage(getString(R.string.no_internet))
            }
        }

        dataBinding.btnOtpSendCancel.setOnClickListener {
            dismiss()
        }
    }

    @SuppressLint("CheckResult")
    private fun setStateObservable() {
        viewModel.stateObservable.subscribe({
            updateView(it)
        }, {
            Log.getStackTraceString(it)
        })
    }


    private fun updateView(state: SendMessageDialogState?) {
        when {
            state!!.sendSuccess -> {
                saveMessageLocally()
                showMessage(state.message)
            }
            state.sendFailure -> showMessage(state.message)
        }
        dataBinding.state = state
    }

    private fun saveMessageLocally() {
        SqlLiteHelper(context!!).addSentMessage(
            Message(
                otp = tv_otp.text.toString(),
                name = contact?.name,
                phone = contact?.phone,
                time = System.currentTimeMillis()
            )
        )
        dismiss()
    }

    private fun showMessage(message: String) {
        Toast.makeText(activity!!, message, Toast.LENGTH_SHORT).show()
    }

    private fun sendMessage() {
        val messagePostData = PostTextLocalSMSData()
        messagePostData.apiKey = getString(R.string.text_local_api)
        messagePostData.message = getString(R.string.your_verification_code) + dataBinding.tvOtp.text
        //TODO test true for testing
        messagePostData.test = false
        messagePostData.numbers = dataBinding.tvPhone.text.toString().replace("+91-","91")
        viewModel.sendMessage(messagePostData)
    }

    private fun setDetails() {
        dataBinding.tvPhone.text = contact?.phone
        dataBinding.tvOtp.text = Random.nextLong(100000, 999999).toString()
    }

    private fun isNetworkConnected(): Boolean {
        val cm = activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return cm!!.activeNetworkInfo != null
    }

}