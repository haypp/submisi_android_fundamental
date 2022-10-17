package com.haypp.githubuser.activity

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.haypp.githubuser.R
import com.haypp.githubuser.data.api.ApiConfig
import com.haypp.githubuser.data.api.DetilUserData
import com.haypp.githubuser.databinding.ActivityDetilUserBinding
import com.haypp.githubuser.fragment.SectionsPagerAdapter
import com.haypp.githubuser.viewmodels.FavoriteViewModel
import com.haypp.githubuser.viewmodels.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.viewpager2.widget.ViewPager2 as vp2

class DetilUserActivity : AppCompatActivity() {
    private lateinit var bindingg: ActivityDetilUserBinding
    private lateinit var usernamee: String
    var _fav = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detil_user)
        bindingg=ActivityDetilUserBinding.inflate(layoutInflater)
        setContentView(bindingg.root)
        getData()

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        sectionsPagerAdapter.okee = usernamee
        val viewPager: vp2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    private fun getData() {
        usernamee = intent.getStringExtra(extradataa).toString()
        val client = ApiConfig.getApiService().getDataUser(usernamee)
        client.enqueue(object : Callback<DetilUserData> {
            override fun onResponse(
                call: Call<DetilUserData>,
                response: Response<DetilUserData>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setDatalist(responseBody)
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DetilUserData>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })

    }

    private fun getFavoGak(login: String, avatar: String) {
        val mFavViewModel = obtainViewModel(this@DetilUserActivity)
        CoroutineScope(Dispatchers.IO).launch {
            val vCheckUser = mFavViewModel.checkUserFavorite(usernamee)
            withContext(Dispatchers.Main) {
                if(vCheckUser.isNotEmpty()){
                    _fav = true
                    bindingg.toggleButtonFavorite.isChecked = true
                } else {
                    _fav = false
                    bindingg.toggleButtonFavorite.isChecked = false
                }
            }
        }
        bindingg.toggleButtonFavorite.setOnClickListener {
            if(_fav){
                mFavViewModel.delete(login)
                _fav = false
                bindingg.toggleButtonFavorite.isChecked = false
            } else {
                mFavViewModel.insert(login, avatar)
                _fav = true
                bindingg.toggleButtonFavorite.isChecked = true
            }
        }
    }

    private fun obtainViewModel(detilUserActivity: DetilUserActivity): FavoriteViewModel {
        val factory = ViewModelFactory.getInstance(detilUserActivity.application)
        return ViewModelProvider(detilUserActivity, factory)[FavoriteViewModel::class.java]
    }

    private fun setDatalist(responseBody: DetilUserData) {
        bindingg.apply {
            tvNama.text = responseBody.name
            tvCompany.text = StringBuilder(getString(R.string.company)).append(responseBody.company.toString())
            tvUsername.text = StringBuilder(getString(R.string.att)).append(responseBody.login)
            tvFollowing.text = StringBuilder(getString(R.string.follwoing)).append(responseBody.following.toString())
            tvFollower.text = StringBuilder(getString(R.string.follower)).append(responseBody.followers.toString())
            tvRepo.text = StringBuilder(getString(R.string.repos)).append(responseBody.publicRepos.toString())
            tvLocation.text = responseBody.location
            Glide.with(this@DetilUserActivity)
                .load(responseBody.avatarUrl)
                .into(bindingg.ImgAvatar)
        }
        getFavoGak(responseBody.login.toString(),responseBody.avatarUrl.toString())
    }



    companion object {
        const val extradataa = "extra_data"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1, R.string.tab_text_2
        )
    }
}
