package ir.alirezaiyan.moviz.domain.feature.search

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import ir.alirezaiyan.moviz.data.ApiService
import ir.alirezaiyan.moviz.data.model.search.MSMovie
import ir.alirezaiyan.moviz.data.utils.NetworkHandler
import ir.alirezaiyan.moviz.sdk.base.Either
import ir.alirezaiyan.moviz.sdk.base.exception.Failure
import org.amshove.kluent.mock
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.mockito.Mockito
import retrofit2.Call
import retrofit2.Response

class SearchRepositoryBot {

    private var networkHandler = mock(NetworkHandler::class)
    private var api = mock(ApiService::class)
    @Suppress("UNCHECKED_CAST")
    private var call = mock(Call::class) as Call<MSMovie>
    @Suppress("UNCHECKED_CAST")
    private var searchRepository = mock(Response::class) as Response<MSMovie>

    private var repository = SearchRepository.SearchRepositoryImpl(api, networkHandler)


    fun connectToNetwork(beConnect: Boolean?): SearchRepositoryBot {
        given { networkHandler.isConnected }.willReturn(beConnect)
        return this
    }


    fun searchReturnsThiscall(movieName: String): SearchRepositoryBot {
        Mockito.`when`(api.searchMovie(movieName)).thenReturn(call)
        return this
    }

    fun responseBeSuccessful(beSuccessful: Boolean): SearchRepositoryBot {
        given { searchRepository.isSuccessful }.willReturn(beSuccessful)
        return this
    }

    fun responseBodyReturns(msMovie: MSMovie): SearchRepositoryBot {
        given { searchRepository.body() }.willReturn(msMovie)
        return this
    }

    fun executeCallReturns(): SearchRepositoryBot {
        given { call.execute() }.willReturn(searchRepository)
        return this
    }

    fun runAndVerify(movieName: String, msMovie: MSMovie): SearchRepositoryBot {
        val search = repository.search(movieName)

        search shouldBeInstanceOf Either::class.java

        when {
            search.isRight -> {
                search shouldEqual Either.Right(msMovie)
            }
            searchRepository.isSuccessful -> {

                search.either({ failure ->
                    when (failure) {
                        is Failure.NetworkConnection -> failure shouldBeInstanceOf Failure.NetworkConnection::class.java
                        is Failure.FeatureFailure -> failure shouldBeInstanceOf Failure.FeatureFailure::class.java
                    }
                }, {})

            }
            !searchRepository.isSuccessful -> {
                search.either({ failure -> failure shouldBeInstanceOf Failure.NetworkConnection::class.java }, {})
            }
            else -> {
                search.either({ failure -> failure shouldBeInstanceOf Failure.FeatureFailure::class.java }, {})
            }
        }

        return this
    }

    fun verifySearch(movieName: String) {
        verify(api).searchMovie(movieName)
    }

}