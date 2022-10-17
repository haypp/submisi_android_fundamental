package com.haypp.githubuser.repository

import android.app.Application
import com.haypp.githubuser.data.db.Favorite
import com.haypp.githubuser.data.db.FavoriteDao
import com.haypp.githubuser.data.db.FavoriteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private val mFavoriteDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    private val token = ""

    init {
        val db = FavoriteRoomDatabase.getDatabase(application)
        mFavoriteDao = db.favoriteDao()
    }

    fun getAllFavorites() = mFavoriteDao.getAllFavorites()
    fun checkUserFavorite(login: String) = mFavoriteDao.getFavoriteByLogin(login)

    fun insert(favorite: Favorite) {
        executorService.execute { mFavoriteDao.insert(favorite) }
    }
    fun delete(mfavorite: String) {
        executorService.execute { mFavoriteDao.delete(mfavorite) }
    }
}