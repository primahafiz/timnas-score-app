package com.example.timnasscore

import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @GET(".")
    fun getMatchData(): Call<List<APIDataItem>>

    @GET
    fun getGoalData(@Url url: String): Call<List<APIDataGoalItem>>
}