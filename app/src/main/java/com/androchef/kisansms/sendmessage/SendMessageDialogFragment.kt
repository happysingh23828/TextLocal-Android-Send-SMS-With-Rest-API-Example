package com.androchef.kisansms.sendmessage

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.androchef.kisansms.R
import com.androchef.kisansms.databinding.LayoutFragmentSendMessageDialogBinding
import com.androchef.kisansms.pojo.Contact
import kotlin.random.Random

class SendMessageDialogFragment : DialogFragment() {
    companion object {
    const val CONTACT_KEY = "contact_key"
    }

    lateinit var dataBinding: LayoutFragmentSendMessageDialogBinding
    var contact: Contact? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.layout_fragment_send_message_dialog, container, false)
        handleArguments()
        onClicks()
        return dataBinding.root
    }

    private fun onClicks() {

        dataBinding.btnOtpSend.setOnClickListener {
            Toast.makeText(activity!!,"Send Clicked",Toast.LENGTH_SHORT).show()
            dismiss()
        }

        dataBinding.btnOtpSendCancel.setOnClickListener {
            Toast.makeText(activity!!,"Cancel Clicked",Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }

    private fun handleArguments() {
        arguments?.let {
            contact = it.getSerializable(CONTACT_KEY) as Contact
            setDetails()
        }
    }

    private fun setDetails(){
        dataBinding.tvPhone.text = contact?.phone
        dataBinding.tvOtp.text = Random.nextLong(100000,999999).toString()
    }

    private fun init() {

    }

}