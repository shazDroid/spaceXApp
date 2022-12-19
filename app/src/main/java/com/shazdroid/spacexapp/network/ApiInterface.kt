package com.shazdroid.spacexapp.network

import com.shazdroid.spacexapp.view.fragments.rocket_list.model.RocketListResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("rockets")
    suspend fun getRocketList() : List<RocketListResponseItem>?

    @GET("rockets/{id}")
    suspend fun getRocketDetail(@Path("id") id: String) : RocketListResponseItem
}