package com.kishanmaurya.kad.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by kishanmaurya on 25/9/17.
 */
interface AppApi
{
    @GET("/top.json")
    fun getTop(@Query("after") after: String, @Query("limit") limit: String): Call<NewsResponse>
}