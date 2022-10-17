package com.haypp.githubuser.data.api

import android.media.session.MediaSession.Token
import retrofit2.Call
import retrofit2.http.*
    private const val Token = "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"
interface ApiService {
    @GET("users/{login}")
    @Headers("Authorization: token $Token")
    fun getDataUser(
        @Path("login") id: String
    ): Call<DetilUserData>

    @GET("search/users")
    @Headers("Authorization: token $Token")
    fun getSearchUser(
        @Query("q") query: String
    ): Call<ListDataProfile>

    @GET("users/{login}/following")
    @Headers("Authorization: token $Token")
    fun getFollowing(
        @Path("login") id: String
    ): Call<FollowData>

    @GET("users/{login}/followers")
    @Headers("Authorization: token $Token")
    fun getFollower(
        @Path("login") id: String
    ): Call<FollowData>
}