package com.haypp.githubuser.fragment

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    var okee: String = ""
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowingFragment()
            1 -> fragment = FollowerFragment()
        }
        if (position == 0) {
            Log.e(this.toString(), "ara janji ga kosong : $okee")
            fragment?.arguments = Bundle().apply {
                putString(FollowingFragment.extra, okee)
            }
        } else {
            fragment?.arguments = Bundle().apply {
                putString(FollowerFragment.extra, okee)
            }
        }
        return fragment as Fragment
    }
}