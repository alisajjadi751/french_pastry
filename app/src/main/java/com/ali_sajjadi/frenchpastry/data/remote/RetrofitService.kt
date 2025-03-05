package com.ali_sajjadi.frenchpastry.data.remote


import android.content.Context
import com.ali_sajjadi.frenchpastry.data.remote.apiInterface.DetailsApi
import com.ali_sajjadi.frenchpastry.data.remote.apiInterface.HomeApi
import com.ali_sajjadi.frenchpastry.data.remote.apiInterface.LoginApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitService {
    private const val BASE_URL = "https://pastry.alirezaahmadi.info/api/"
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS) // تایم‌اوت اتصال
        .readTimeout(30, TimeUnit.SECONDS)   // تایم‌اوت خواندن
        .build()


    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val loginApi: LoginApi = retrofit.create(LoginApi::class.java)
    val homeApi: HomeApi = retrofit.create(HomeApi::class.java)
    val detailsApi: DetailsApi = retrofit.create(DetailsApi::class.java)
}

/*object RetrofitService {
    private const val BASE_URL = "https://pastry.alirezaahmadi.info/api/"

    // Define OkHttpClient with the CustomHeadersInterceptor
    private fun provideOkHttpClient(context: Context, apiKey: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(CustomHeadersInterceptor(context, apiKey)) // Add the interceptor
            .connectTimeout(30, TimeUnit.SECONDS)  // Timeout for connecting
            .readTimeout(30, TimeUnit.SECONDS)     // Timeout for reading
            .writeTimeout(30, TimeUnit.SECONDS)    // Timeout for writing
            .build()
    }

    // Create Retrofit instance
    private fun provideRetrofit(context: Context, apiKey: String): Retrofit {
        val client = provideOkHttpClient(context, apiKey)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client) // Set the custom OkHttpClient
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Creating API interfaces
    fun getLoginApi(context: Context, apiKey: String): LoginApi {
        return provideRetrofit(context, apiKey).create(LoginApi::class.java)
    }

    fun getHomeApi(context: Context, apiKey: String): HomeApi {
        return provideRetrofit(context, apiKey).create(HomeApi::class.java)
    }

    fun getDetailsApi(context: Context, apiKey: String): DetailsApi {
        return provideRetrofit(context, apiKey).create(DetailsApi::class.java)
    }
}*/



