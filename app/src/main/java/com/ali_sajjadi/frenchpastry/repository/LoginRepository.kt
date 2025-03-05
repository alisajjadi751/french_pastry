package com.ali_sajjadi.frenchpastry.repository

import com.ali_sajjadi.frenchpastry.data.remote.RetrofitService
import com.ali_sajjadi.frenchpastry.data.remote.model.login.SendPhoneResponse
import com.ali_sajjadi.frenchpastry.data.remote.model.login.VerifyCode

class LoginRepository {

    suspend fun apiLogin2(deviceUID: String, publicKey: String, phone: String): SendPhoneResponse {
        val response = RetrofitService.loginApi.sendPhone(
            deviceUID = deviceUID,
            publicKey = publicKey,
            phone = phone
        )
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty response")
        } else {
            throw Exception("Error ${response.code()}: ${response.errorBody()?.string()}")
        }
    }

    suspend fun apiCode2(deviceUID: String, publicKey: String,code: String, phone: String): VerifyCode {
        val response = RetrofitService.loginApi.verifyCode(
            deviceUID = deviceUID,
            publicKey = publicKey,
            code = code,
            phone = phone
        )
        if (response.isSuccessful){
            return response.body() ?: throw Exception("Empty response")
        }else {
            throw Exception("Error ${response.code()} : ${response.errorBody()?.string()}")
        }
    }
}
