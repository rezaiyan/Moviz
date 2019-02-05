package ir.alirezaiyan.moviz.data.utils

import com.nhaarman.mockitokotlin2.given
import ir.alirezaiyan.moviz.data.ApiService
import ir.alirezaiyan.moviz.data.model.ErrorModel
import ir.alirezaiyan.moviz.data.model.search.MSMovie
import ir.alirezaiyan.moviz.sdk.base.Either
import ir.alirezaiyan.moviz.sdk.base.exception.Failure
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.ResponseBody
import org.amshove.kluent.mock
import org.amshove.kluent.shouldBeInstanceOf
import org.mockito.Mockito
import retrofit2.Call
import retrofit2.Response
import kotlin.test.assertFalse


class BaseRepositoryBot {

    private var api = mock(ApiService::class)
    @Suppress("UNCHECKED_CAST")
    private var call = mock(Call::class) as Call<MSMovie>?
    @Suppress("UNCHECKED_CAST")
    private var loginResponse = mock(Response::class) as Response<MSMovie>?
    private var repository = BaseRepository()
    private val errorMessage = "اطلاعات برای اهراز هویت ارسال نشده است"
    val errorResponse = Response.error<ErrorModel>(
            ResponseBody.create(
                    MediaType.parse("application/json"),
                    "{\"status\":false,\"msg\":\"$errorMessage\",\"success\":false}"
            ), okhttp3.Response.Builder().request(Request.Builder().url("http://google.com").build()).code(401).protocol(Protocol.HTTP_1_1).message("Error message test").build())


    fun withThisResponse(response: Response<MSMovie>?): BaseRepositoryBot {
        this.loginResponse = response
        return this
    }

    fun withThisCall(call: Call<MSMovie>?): BaseRepositoryBot {
        this.call = call
        return this
    }


    fun loginReturnsThiscall(payload: String): BaseRepositoryBot {
        Mockito.`when`(api.searchMovie(payload)).thenReturn(call)
        return this
    }

    fun withErrorBody(): BaseRepositoryBot {

        given { loginResponse!!.errorBody() }.willReturn(errorResponse.errorBody())
        return this
    }


    fun executeCallReturns(): BaseRepositoryBot {
        given { call!!.execute() }.willReturn(loginResponse!!)
        return this
    }
    @Suppress("RedundantLambdaArrow")
    fun verifyNullResponse() {
        val onResult = { _: MSMovie -> Either.Left(Failure.NetworkConnection()) }
        val result = repository.request(api.searchMovie("IncorrectPayload"), onResult)

        result.either({ failure -> failure shouldBeInstanceOf Failure.NetworkConnection::class.java }, {})
    }
    @Suppress("RedundantLambdaArrow")
    fun verifyException() {
        val onResult = {  _: MSMovie -> Either.Left(Failure.FeatureFailure(Throwable())) }
        val result = repository.request(api.searchMovie("IncorrectPayload"), onResult)

        result.either({ failure -> failure shouldBeInstanceOf Failure.FeatureFailure::class.java }, {})
    }

    fun verifySuccessful() {
        val onResult = { login: MSMovie -> Either.Right(login) }
        val result = repository.request(api.searchMovie("IncorrectPayload"), onResult)

        result.either({ }, { response -> response shouldBeInstanceOf Either.Right::class.java })
    }

    fun verifyUnsuccessful() {
        val onResult = { msMovie: MSMovie -> Either.Right(msMovie) }
        val result = repository.request(api.searchMovie("IncorrectPayload"), onResult)

        result.either({ failure ->
            failure shouldBeInstanceOf Failure.ServerError::class.java
            assertFalse((failure as Failure.ServerError).message.isNullOrBlank())
//            failure.code shouldBe 401
        }, {})
    }
}