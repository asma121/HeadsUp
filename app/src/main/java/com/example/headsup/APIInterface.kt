package com.example.headsup

import retrofit2.Call
import retrofit2.http.*

interface APIInterface {
    @Headers("Content-Type: application/json")
    @GET("/celebrities/")
    fun getCelebrityDetails(): Call<List<CelebrityDetails.Datum>>

    @Headers("Content-Type: application/json")
    @POST("/celebrities/")
    fun addCelebrityDetails(@Body userdata:CelebrityDetails.Datum): Call<CelebrityDetails.Datum>

    @Headers("Content-Type: application/json")
    @PUT("/celebrities/{id}")
    fun upDateCelebrityDetails(@Path("id") id:Int, @Body userdata: CelebrityDetails.Datum): Call<CelebrityDetails.Datum>

    @Headers("Content-Type: application/json")
    @DELETE("/celebrities/{id}")
    fun delCelebrityDetails(@Path("id") id: Int): Call<Void>

}