package com.haypp.githubuser

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.haypp.githubuser.api.ApiConfig
import com.haypp.githubuser.api.DetilUserData
import com.haypp.githubuser.databinding.ActivityDetilUserBinding
import com.haypp.githubuser.fragment.SectionsPagerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.viewpager2.widget.ViewPager2 as vp2

class DetilUserActivity : AppCompatActivity() {
    private lateinit var bindingg: ActivityDetilUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detil_user)
        bindingg=ActivityDetilUserBinding.inflate(layoutInflater)
        setContentView(bindingg.root)

        val usernamee = intent.getStringExtra(extradataa).toString()
        val client = ApiConfig.getApiService().getDataUser(usernamee)
        client.enqueue(object : Callback<DetilUserData> {
            override fun onResponse(
                call: Call<DetilUserData>,
                response: Response<DetilUserData>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        bindingg.apply {
                            tvNama.setText(responseBody.name)
                            tvCompany.setText(StringBuilder(getString(R.string.company)).append(responseBody.company.toString()))
                            tvUsername.setText(StringBuilder(getString(R.string.att)).append(responseBody.login))
                            tvFollowing.setText(StringBuilder(getString(R.string.follwoing)).append(responseBody.following.toString()))
                            tvFollower.setText(StringBuilder(getString(R.string.follower)).append(responseBody.followers.toString()))
                            tvRepo.setText(StringBuilder(getString(R.string.repos)).append(responseBody.publicRepos.toString()))
                            tvLocation.setText(responseBody.location)
                            Glide.with(this@DetilUserActivity)
                                .load(responseBody.avatarUrl)
                                .into(bindingg.ImgAvatar)
                        }
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetilUserData>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })

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

    companion object {
        const val extradataa = "extra_data"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
}
