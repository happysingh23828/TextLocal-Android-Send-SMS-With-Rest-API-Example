package com.androchef.kisansms.contactlist


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.androchef.kisansms.R
import com.androchef.kisansms.Utils
import com.androchef.kisansms.databinding.FragmentContactListBinding


class ContactListFragment : Fragment() {

    lateinit var databinding : FragmentContactListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        databinding = DataBindingUtil.inflate(inflater,R.layout.fragment_contact_list, container, false)
        init()
        setRefreshSwipeListener()
        return databinding.root
    }

    private fun init() {
       databinding.contactRecyclerView.layoutManager = LinearLayoutManager(activity)
        databinding.contactRecyclerView.adapter = ContactListAdapter(Utils.getDummyContacts(activity!!))
    }


    private fun setRefreshSwipeListener(){
        databinding.swipeRefreshContacts.setOnRefreshListener {
            init()
            databinding.swipeRefreshContacts.isRefreshing = false
        }
    }


}
