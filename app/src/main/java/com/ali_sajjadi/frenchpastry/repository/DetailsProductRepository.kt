package com.ali_sajjadi.frenchpastry.repository


import android.util.Log
import com.ali_sajjadi.frenchpastry.data.remote.RetrofitService
import com.ali_sajjadi.frenchpastry.data.remote.model.details.DetailsProduct
import com.ali_sajjadi.frenchpastry.data.remote.model.details.setComment.SetComment


class DetailsProductRepository {

    suspend fun getDetailsProduct(id: Int): DetailsProduct {
        val response = RetrofitService.detailsApi.getDetailsById(id)

        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty response")
        } else {
            val error = response.errorBody()?.toString()
            throw Exception("Error ${response.code()}: $error")
        }
    }

    suspend fun setComment(
        apiKey: String,
        deviceID: String,
        publicKey: String,
        postId: Int,
        content: String,
        rate: Int
    ): SetComment {

        val response = RetrofitService.detailsApi.setComment(
            apiKey,
            deviceID,
            publicKey,
            postId,
            content,
            rate
        )
        if (response.isSuccessful) {
            Log.i("Comment_Success", "کامنت با موفقیت ارسال شد: ${response.body()?.message}")
            Log.i("API_Success", "Comment posted successfully: ${response.body()}")
            return response.body() ?: throw Exception("Empty Response")

        } else {
            val error = response.errorBody()?.toString()
            Log.e("API_Error", "Error posting comment: ${response.code()}: $error")
            throw Exception("Error ${response.code()}: $error")
        }

    }

}