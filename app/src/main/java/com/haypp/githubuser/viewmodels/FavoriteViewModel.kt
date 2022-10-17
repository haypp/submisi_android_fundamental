package com.haypp.githubuser.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.haypp.githubuser.data.db.Favorite
import com.haypp.githubuser.repository.FavoriteRepository

class FavoriteViewModel(application: Application) : ViewModel() {
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)
    fun getAllFavorites(): LiveData<List<Favorite>> = mFavoriteRepository.getAllFavorites()

    fun checkUserFavorite(login: String) = mFavoriteRepository.checkUserFavorite(login)

    fun insert(mUser: String, mAvatar: String) {
        val mFavorite = Favorite(mAvatar, mUser)
        mFavoriteRepository.insert(mFavorite)
    }
    fun delete(mUser: String) {
        mFavoriteRepository.delete(mUser)
    }
}