package com.haypp.githubuser.data.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Favorite(
    @ColumnInfo(name = "avatar_url")
    var avatar_url: String? = null,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "login")
    var login: String,
) : Parcelable
