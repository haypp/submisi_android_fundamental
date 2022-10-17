package com.haypp.githubuser.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import retrofit2.http.Headers

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favorite: Favorite)
    
    @Query("DELETE FROM favorite WHERE login = :login")
    abstract fun delete(login: String)

    @Query("SELECT * FROM favorite ORDER BY login ASC")
    fun getAllFavorites(): LiveData<List<Favorite>>
    
    @Query("SELECT * FROM favorite WHERE login = :login")
    fun getFavoriteByLogin(login: String): List<Favorite>

}