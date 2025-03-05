package com.ali_sajjadi.frenchpastry.data.remote.apiInterface


import com.ali_sajjadi.frenchpastry.data.remote.model.details.DetailsProduct
import com.ali_sajjadi.frenchpastry.data.remote.model.details.Rate
import com.ali_sajjadi.frenchpastry.data.remote.model.details.setComment.SetComment
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface DetailsApi {

    @GET("v1/pastry/{id}")
    suspend fun getDetailsById(
        @Path("id") id: Int
    ): Response<DetailsProduct>

    @FormUrlEncoded
    @POST("v1/comment/")
    suspend fun setComment(
        @Header("app-api-key") apiKey: String,
        @Header("app-device-uid") deviceID: String,
        @Header("app-public-key") publicKey: String,
        @Field("post_id") postId: Int,
        @Field("content") content: String,
        @Field("rate") rate: Int
    ): Response<SetComment>

}