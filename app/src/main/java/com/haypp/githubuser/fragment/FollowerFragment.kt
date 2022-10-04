package com.haypp.githubuser.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haypp.githubuser.R
import com.haypp.githubuser.adaptor.ListUserAdaptor
import com.haypp.githubuser.api.ApiConfig
import com.haypp.githubuser.api.Items
import com.haypp.githubuser.api.FollowData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerFragment : Fragment() {
    private val followerlist = ArrayList<Items>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_follower, container, false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(true)
        val username = arguments?.getString(extra).toString()
        val mconfig = ApiConfig.getApiService().getFollower(username)
        mconfig.enqueue(object : Callback<FollowData> {
            override fun onResponse(
                call: Call<FollowData>,
                response: Response<FollowData>
            ) {
                showLoading(false)
                if (response.isSuccessful){
                    val mresponse = response.body()
                    if (mresponse != null){
                        showfollower(mresponse)
                    }
                }
            }

            override fun onFailure(call: Call<FollowData>, t: Throwable) {
                Log.e(FollowerFragment.toString(), "ara ${t.message}")
                showLoading(false)
            }
        })
    }

    private fun showfollower(mresponse: FollowData) {
        followerlist.clear()
        for (item in mresponse){
            followerlist.add(Items(item.avatarUrl,item.login))
        }
        showRecyclerList()
    }

    private fun showRecyclerList() {
        val showw = view?.findViewById<RecyclerView>(R.id.rvfollower)
        showw?.layoutManager = LinearLayoutManager(context)
        val listUserAdaptor = ListUserAdaptor(followerlist)
        showw?.adapter = listUserAdaptor
    }
    private fun showLoading(state: Boolean) {
        val progressBar = view?.findViewById<ProgressBar>(R.id.progressBar2)
        progressBar?.visibility = if (state) View.VISIBLE else View.GONE
    }

    companion object{
        const val extra = "extra"
    }
}