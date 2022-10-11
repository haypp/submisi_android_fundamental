package com.haypp.githubuser.repository

import android.app.Application
import com.haypp.githubuser.db.Favorite
import com.haypp.githubuser.db.FavoriteDao
import com.haypp.githubuser.db.FavoriteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private val mFavoriteDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteRoomDatabase.getDatabase(application)
        mFavoriteDao = db.favoriteDao()
    }

    fun getAllFavorites() = mFavoriteDao.getAllFavorites()
    fun insert(favorite: Favorite) {
        executorService.execute { mFavoriteDao.insert(favorite) }
    }
    fun delete(favorite: Favorite) {
        executorService.execute { mFavoriteDao.delete(favorite) }
    }
    fun update(favorite: Favorite) {
        executorService.execute { mFavoriteDao.update(favorite) }
    }
}