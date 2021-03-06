package com.androchef.kisansms.ui.contactinfo

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.androchef.kisansms.R
import com.androchef.kisansms.Utils
import com.androchef.kisansms.databinding.ActivityContactInfoBinding
import com.androchef.kisansms.pojo.response.Contact
import com.androchef.kisansms.ui.sendmessage.SendMessageDialogFragment

class ContactInfoActivity : AppCompatActivity() {

    companion object {
        private const val CONTACT_KEY = "contact_key"
        fun start(context: Context,contact : Contact){
            val intent = Intent(context,ContactInfoActivity::class.java)
            intent.putExtra(CONTACT_KEY,contact)
            context.startActivity(intent)
        }
    }

    lateinit var dataBinding : ActivityContactInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        handleIntentData()
        onClicks()
    }


    private fun init() {
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_contact_info)
    }


    private fun handleIntentData() {
        intent?.let {
            setDataToDisplay(intent.getSerializableExtra(CONTACT_KEY) as Contact)
        }
    }

    private fun onClicks() {
        dataBinding.backImg.setOnClickListener {
            onBackPressed()
        }

        dataBinding.btnSendMessage.setOnClickListener {
            showSendMessageDialog()
        }
    }

    private fun showSendMessageDialog() {
        val sendMessageDialog = SendMessageDialogFragment()
        val bundle = Bundle()
        bundle.putSerializable(SendMessageDialogFragment.CONTACT_KEY,intent.getSerializableExtra(CONTACT_KEY) as Contact)
        sendMessageDialog.arguments = bundle
        sendMessageDialog.show(supportFragmentManager,null)
    }

    private fun setDataToDisplay(contact: Contact) {
        dataBinding.tvName.text = contact.name
        dataBinding.tvEmail.text = contact.email
        dataBinding.tvCompany.text = contact.company
        dataBinding.tvGender.text = contact.gender
        dataBinding.tvPhone.text = contact.phone
        setIcon(contact.name)
    }

    private fun setIcon(name: String?) {
        val splitNameArr = name?.split(" ")
        dataBinding.nameIcon.text = splitNameArr!![0].substring(0,1)+ splitNameArr[1].substring(0,1)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dataBinding.nameIcon.backgroundTintList =  Utils.getMaterialColor(this)
        }
    }

}
