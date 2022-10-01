package com.haypp.githubuser.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ListDataProfile(
	@field:SerializedName("items")
	val items: ArrayList<Items>
) : Parcelable

@Parcelize
data class Items(
	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@SerializedName("login")
	val login: String,
) : Parcelable

data class DetilUserData(
	@field:SerializedName("followers")
	val followers: Int? = null,

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@field:SerializedName("following")
	val following: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("company")
	val company: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("public_repos")
	val publicRepos: Int? = null,

	@field:SerializedName("login")
	val login: String? = null
)

