package com.haypp.githubuser.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Favorite")
@Parcelize
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "login")
    var login: String? = null,
    @ColumnInfo(name = "avatar_url")
    var avatar_url: String? = null,
) : Parcelable
