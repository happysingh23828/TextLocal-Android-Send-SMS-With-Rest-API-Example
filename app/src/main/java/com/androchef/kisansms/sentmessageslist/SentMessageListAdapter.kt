package com.androchef.kisansms.sentmessageslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androchef.kisansms.R
import com.androchef.kisansms.pojo.Message
import kotlinx.android.synthetic.main.layout_item_message.view.*
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class SentMessageListAdapter(var listOfMessages : List<Message>) : RecyclerView.Adapter<SentMessageListAdapter.SentMessageListViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SentMessageListViewHolder {
        return SentMessageListViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.layout_item_message,p0,false))
    }

    override fun getItemCount(): Int {
        return listOfMessages.size
    }

    override fun onBindViewHolder(p0: SentMessageListViewHolder, p1: Int) {
        p0.bind(listOfMessages[p1])
    }

    inner class SentMessageListViewHolder(var view : View) : RecyclerView.ViewHolder(view){

        fun bind(message: Message) {
            view.tv_name.text = message.name
            view.tv_sent_otp.text = message.otp
            val stamp = Timestamp(message.time!!)
            val date = Date(stamp.time)
            val sdf = SimpleDateFormat("dd/MM/yyyy :hh :mm")
            view.tv_message_date.text = sdf.format(date)
        }
    }
}