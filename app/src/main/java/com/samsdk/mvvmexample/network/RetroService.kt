package com.samsdk.mvvmexample.network

import com.samsdk.mvvmexample.model.RecyclerList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("repositories")//repositories?q=newyork
    fun getDataFromApi(@Query("q") q: String): Call<RecyclerList>
}