package com.ali_sajjadi.frenchpastry.data.remote.apiInterface

import com.ali_sajjadi.frenchpastry.data.remote.model.home.HomeResponse
import retrofit2.Response

import retrofit2.http.GET

interface HomeApi {

    @GET("v1/main")
    suspend fun getMain():Response<HomeResponse>
}