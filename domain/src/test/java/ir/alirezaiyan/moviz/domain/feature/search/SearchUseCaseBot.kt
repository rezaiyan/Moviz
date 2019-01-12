package ir.alirezaiyan.moviz.domain.feature.search

import com.nhaarman.mockitokotlin2.given
import ir.alirezaiyan.moviz.data.feature.search.SearchRepository
import ir.alirezaiyan.moviz.data.model.search.MSMovie
import ir.alirezaiyan.moviz.sdk.base.Either
import ir.alirezaiyan.moviz.sdk.base.exception.Failure
import org.junit.Assert.assertEquals
import org.mockito.Mockito

class SearchUseCaseBot {


    private val repository = Mockito.mock(SearchRepository::class.java)
    private var loginUseCase = SearchUseCase(repository)

    fun repositoryReturns(movieName: String, response: MSMovie): SearchUseCaseBot {
        given { repository.search(movieName) }.willReturn(Either.Right(response))
        return this
    }

    suspend fun resultShouldBe(movieName: String, expectedResult: Either<Failure, MSMovie>): SearchUseCaseBot {
        assertEquals(expectedResult, loginUseCase.run(movieName))
        return this
    }

    fun repositoryThrows(movieName: String, exception: Failure): SearchUseCaseBot {
        Mockito.`when`(repository.search(movieName)).thenReturn(Either.Left(exception))
        return this
    }

}