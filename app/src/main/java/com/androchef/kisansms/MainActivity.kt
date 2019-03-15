package com.androchef.kisansms

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import com.androchef.kisansms.contactlist.ContactListFragment
import com.androchef.kisansms.databinding.ActivityMainBinding
import com.androchef.kisansms.sentmessageslist.SentMessagesListFragment

class MainActivity : AppCompatActivity() {

    lateinit var databinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setViewPager()
    }


    private fun init() {
        databinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
    }

    private fun setViewPager() {
        databinding.viewpager.adapter = CustomPagerAdapter()
        databinding.tabLayout.setupWithViewPager(databinding.viewpager)

    }


    inner class CustomPagerAdapter : FragmentStatePagerAdapter(supportFragmentManager){
        override fun getItem(p0: Int): Fragment {
           return when(p0){
                0 -> ContactListFragment()
                1 -> SentMessagesListFragment()
               else -> ContactListFragment()
           }
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when(position){
                0 -> "Contacts"
                1 -> "Messages"
                else -> "Contacts"
            }
        }
    }
}
