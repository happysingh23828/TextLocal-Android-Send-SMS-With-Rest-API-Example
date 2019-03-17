package com.androchef.kisansms.ui.sentmessageslist


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.androchef.kisansms.R
import com.androchef.kisansms.databinding.FragmentSentMessagesListBinding
import com.androchef.kisansms.db.SqlLiteHelper

class SentMessagesListFragment : Fragment() {

    lateinit var dataBinding : FragmentSentMessagesListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_sent_messages_list, container, false)
        init()
        setRefreshSwipeListener()
        return  dataBinding.root
    }

    private fun init() {
        dataBinding.messagesRecyclerView.layoutManager = LinearLayoutManager(activity)
        dataBinding.messagesRecyclerView.adapter = SentMessageListAdapter(SqlLiteHelper(activity!!).getAllMessageList()!!)
    }

    private fun setRefreshSwipeListener(){
        dataBinding.swipeRefreshMessages.setOnRefreshListener {
            init()
            dataBinding.swipeRefreshMessages.isRefreshing = false
        }
    }


}
