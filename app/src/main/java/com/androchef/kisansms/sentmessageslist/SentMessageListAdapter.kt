package com.androchef.kisansms.sentmessageslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androchef.kisansms.R
import com.androchef.kisansms.pojo.Message

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

        }
    }
}