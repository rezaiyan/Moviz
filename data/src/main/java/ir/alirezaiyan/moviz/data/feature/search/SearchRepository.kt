package ir.alirezaiyan.moviz.data.feature.search

import ir.alirezaiyan.moviz.data.feature.ApiService
import ir.alirezaiyan.moviz.data.model.search.MSMovie
import ir.alirezaiyan.moviz.data.utils.BaseRepository
import ir.alirezaiyan.moviz.data.utils.NetworkHandler
import ir.alirezaiyan.moviz.sdk.base.Either
import ir.alirezaiyan.moviz.sdk.base.Either.Left
import ir.alirezaiyan.moviz.sdk.base.exception.Failure
import ir.alirezaiyan.moviz.sdk.base.exception.Failure.NetworkConnection

interface SearchRepository {

    fun search(movieName: String): Either<Failure, MSMovie>

    class Network(private val api: ApiService, private val networkHandler: NetworkHandler) : BaseRepository(),
        SearchRepository {
        override fun search(movieName: String): Either<Failure, MSMovie> {
            return when (networkHandler.isConnected) {
                true -> request(api.searchMovie(movieName)) { it }
                false, null -> Left(NetworkConnection())
            }
        }

    }
}