package com.androchef.kisansms.ui.contactlist

import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androchef.kisansms.R
import com.androchef.kisansms.Utils
import com.androchef.kisansms.ui.contactinfo.ContactInfoActivity
import com.androchef.kisansms.pojo.response.Contact
import kotlinx.android.synthetic.main.layout_item_contact.view.*

class ContactListAdapter(var contactsList : List<Contact>) : RecyclerView.Adapter<ContactListAdapter.ContactListViewHolder>()  {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ContactListViewHolder {
        return ContactListViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.layout_item_contact,p0,false))
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    override fun onBindViewHolder(p0: ContactListViewHolder, p1: Int) {
        p0.bind(contactsList[p1])
    }

    inner class ContactListViewHolder(var view : View) : RecyclerView.ViewHolder(view){
        fun bind(contact: Contact) {
            view.tv_name.text = contact.name
            view.tv_phone_number.text = contact.phone
            setIcon(contact.name)
            onClicks(contact)
        }

        private fun setIcon(name: String?) {
            val splitNameArr = name?.split(" ")
            view.name_icon.text = splitNameArr!![0].substring(0,1)+ splitNameArr[1].substring(0,1)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.name_icon.backgroundTintList =  Utils.getMaterialColor(view.context)
            }
        }

        private fun onClicks(contact: Contact) {
            view.setOnClickListener {
                ContactInfoActivity.start(view.context,contact)
            }
        }

    }
}