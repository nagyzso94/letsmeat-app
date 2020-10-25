package com.letsmeatapp.letsmeatapp.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.letsmeatapp.letsmeatapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RemoteDataSource {
    companion object {
        //locally: private const val BASE_URL = "http://192.168.0.10:8000/api/"
        private const val BASE_URL = "http://letsmeatapp.com/letsmeatwebapp/public/api/"
    }

    val gson: Gson = GsonBuilder()
                        .setLenient()
                        .create();


    fun <Api> buildApi(
        api: Class<Api>,
        authToken: String? = null
    ): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        chain.proceed(chain.request().newBuilder().also {
                            it.addHeader("Authorization", "Bearer $authToken")
                            it.addHeader("Content-Type","application/json")
                            it.addHeader("Accept","application/json")
                        }.build())
                    }.also { client ->
                        if (BuildConfig.DEBUG) {
                            val logging = HttpLoggingInterceptor()
                            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                            client.addInterceptor(logging)
                        }
                    }.build()
            )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(api)
    }
}