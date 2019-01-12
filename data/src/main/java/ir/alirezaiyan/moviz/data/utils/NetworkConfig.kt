package ir.alirezaiyan.moviz.data.utils

import ir.alirezaiyan.moviz.data.feature.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


fun provideRetrofit(isDebug: Boolean): ApiService {
    return Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com")
            .client(provideHttpClient(isDebug))
            .addConverterFactory(MoshiConverterFactory.create())
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

