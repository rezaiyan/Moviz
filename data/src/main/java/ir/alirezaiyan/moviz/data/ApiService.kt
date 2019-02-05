package ir.alirezaiyan.moviz.data

import ir.alirezaiyan.moviz.data.model.search.MSMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/")
    fun searchMovie(
        @Query("t") movieName: String,
        @Query("apiKey") apiKey: String = BuildConfig.OMDB_API_KEY
    ): Call<MSMovie>
}
