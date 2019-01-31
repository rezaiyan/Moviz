package ir.alirezaiyan.moviz.domain.feature.search

import ir.alirezaiyan.moviz.data.model.search.MSMovie
import ir.alirezaiyan.moviz.sdk.base.Either
import ir.alirezaiyan.moviz.sdk.base.exception.Failure
import ir.alirezaiyan.moviz.sdk.base.utils.UnitTest
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SearchUseCaseTest : UnitTest() {

    private val entity = MSMovie.mockObject
    private val error = Failure.ServerError(404,"Not found")
    private val successResponse = Either.Right(entity)
    private val errorResponse = Either.Left(error)
    private val movieName = "IncorrectPayload"

    @Test
    fun `should get data without error`() {

       runBlocking {
           SearchUseCaseBot()
                   .repositoryReturns(movieName, entity)
                   .resultShouldBe(movieName, successResponse)
       }

    }

    @Test
    fun `Get users with network error`() {
      runBlocking {
          SearchUseCaseBot()
                  .repositoryThrows(movieName, error)
                  .resultShouldBe(movieName,errorResponse)
      }
    }
}