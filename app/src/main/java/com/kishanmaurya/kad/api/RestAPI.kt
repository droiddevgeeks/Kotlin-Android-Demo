package com.kishanmaurya.kad.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by kishanmaurya on 25/9/17.
 */
class RestAPI
{
    private val redditApi: AppApi

    init
    {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.reddit.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        redditApi = retrofit.create(AppApi::class.java)
    }

    fun getNews(after: String, limit: String) = redditApi.getTop(after, limit)
}