package com.haypp.githubuser.db

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favorite: Favorite)

    @Update
    fun update(favorite: Favorite)

    @Delete
    fun delete(favorite: Favorite)

    @Query("SELECT * FROM favorite")
    fun getAllFavorites(): LiveData<List<Favorite>>

    @Query("SELECT * FROM favorite WHERE login = :login")
    fun getFavoriteByLogin(login: String): LiveData<List<Favorite>>

}