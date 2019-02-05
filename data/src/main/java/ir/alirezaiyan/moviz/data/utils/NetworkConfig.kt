package ir.alirezaiyan.moviz.data.utils

import ir.alirezaiyan.moviz.data.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun provideRetrofit(isDebug: Boolean): ApiService {
    return Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com")
            .client(provideHttpClient(isDebug))
        .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
}


fun provideHttpClient(isDebug: Boolean): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
    val clientBuilder = OkHttpClient.Builder()
    if (isDebug) {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)
    }
    return clientBuilder.build()
}

