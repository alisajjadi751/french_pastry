package com.ali_sajjadi.frenchpastry.repository

import android.util.Log
import com.ali_sajjadi.frenchpastry.data.remote.RetrofitService
import com.ali_sajjadi.frenchpastry.data.remote.model.home.HomeResponse

@Suppress("UNREACHABLE_CODE")
class HomeRepository {

    suspend fun getMain() : HomeResponse{
        val response = RetrofitService.homeApi.getMain()
        if (response.isSuccessful){
            Log.i("BODY", "Response successful: ${response.body()}")
            return response.body() ?: throw Exception("Empty response")
            Log.i("HTTPs",response.message().toString())
        }else{
            val error = response.errorBody()?.string()
            Log.e("HTTPs", "Error response: ${response.code()}, $error")
            throw Exception("Error ${response.code()}: $error")
        }
    }
}