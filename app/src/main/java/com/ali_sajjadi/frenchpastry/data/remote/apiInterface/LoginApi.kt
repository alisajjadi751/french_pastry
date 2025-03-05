package com.ali_sajjadi.frenchpastry.data.remote.apiInterface

import com.ali_sajjadi.frenchpastry.data.remote.model.login.SendPhoneResponse
import com.ali_sajjadi.frenchpastry.data.remote.model.login.VerifyCode
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginApi {

    @FormUrlEncoded
    @POST("v1/auth/phone/login")
    suspend fun sendPhone(
        @Header("app-device-uid") deviceUID: String,
        @Header("app-public-key") publicKey: String,
        @Field("phone") phone: String
    ): Response<SendPhoneResponse>

    @FormUrlEncoded
    @POST("v1/auth/phone/login/verify")
    suspend fun verifyCode(
        @Header("app-device-uid") deviceUID: String,
        @Header("app-public-key") publicKey: String,
        @Field("code") code: String,
        @Field("phone") phone: String
    ) : Response<VerifyCode>
}