package ir.alirezaiyan.moviz.data.features.auth

import ir.alirezaiyan.moviz.data.model.search.MSMovie
import ir.alirezaiyan.moviz.sdk.base.test.UnitTest
import org.junit.Test
import kotlinx.coroutines.runBlocking

class SearchRepositoryTest : UnitTest() {

    private val loginPayload = "IncorrectPayload"
    private val loginEntity = MSMovie(result=true,errorMessage = "",title = "TestTitle",posterUrl = "invalid",ratings = emptyList())


    @Test
    fun `login request with correct status`() {

        runBlocking {
            SearchRepositoryBot()
                    .connectToNetwork(true)
                    .responseBodyReturns(loginEntity)
                    .responseBeSuccessful(true)
                    .executeCallReturns()
                    .searchReturnsThiscall(loginPayload)
                    .runAndVerify(loginPayload, loginEntity)
                    .verifySearch(loginPayload)
        }

    }

    @Test
    fun `login request with incorrect payload and return server error with no connection`() {

        runBlocking {
            SearchRepositoryBot()
                    .connectToNetwork(false)
                    .runAndVerify(loginPayload, loginEntity)
        }
    }

    @Test
    fun `login request with incorrect payload and return server error with unsuccessful response`() {

        runBlocking {
            SearchRepositoryBot()
                    .connectToNetwork(true)
                    .searchReturnsThiscall(loginPayload)
                    .responseBeSuccessful(false)
                    .runAndVerify(loginPayload, loginEntity)

        }
    }
}
