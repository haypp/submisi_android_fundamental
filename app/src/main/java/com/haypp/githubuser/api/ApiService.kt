package com.haypp.githubuser.api

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("users/{login}")
    fun getDataUser(
        @Path("login") id: String
    ): Call<DetilUserData>

    @GET("search/users")
    fun getSearchUser(
        @Query("q") query: String
    ): Call<ListDataProfile>

    @GET("users/{login}/following")
    fun getFollowing(
        @Path("login") id: String
    ): Call<FollowData>

    @GET("users/{login}/followers")
    fun getFollower(
        @Path("login") id: String
    ): Call<FollowData>
}