package com.haypp.githubuser.FavoriteActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.haypp.githubuser.adaptor.ListUserAdaptor
import com.haypp.githubuser.data.api.Items
import com.haypp.githubuser.databinding.ActivityFavoriteBinding
import com.haypp.githubuser.data.db.Favorite
import com.haypp.githubuser.viewmodels.ViewModelFactory
import com.haypp.githubuser.viewmodels.FavoriteViewModel

class FavoriteActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityFavoriteBinding
    private var list = ArrayList<Items>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        getFav()
    }

    private fun getFav() {
        val mViewModel = obtainViewModel(this@FavoriteActivity)
        mViewModel.getAllFavorites().observe(this) { mData ->
            if (mData != null) {
                mBinding.rvFav.visibility = View.VISIBLE
                setDataFav(mData)
            }
        }
    }

    private fun setDataFav(mData: List<Favorite>) {
        list.clear()
        for (data in mData) {
            val mFoll = Items(
                data.avatar_url,
                data.login
            )
            list.add(mFoll)
        }
        showRecyclerList()
    }

    private fun showRecyclerList() {
        mBinding.apply {
            rvFav.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            val listFavAdapter = ListUserAdaptor(list)
            rvFav.adapter = listFavAdapter
        }
    }

    private fun obtainViewModel(favoriteActivity: FavoriteActivity): FavoriteViewModel {
        val factory = ViewModelFactory.getInstance(favoriteActivity.application)
        return ViewModelProvider(favoriteActivity, factory)[FavoriteViewModel::class.java]
    }
}
