package ir.alirezaiyan.moviz.data.utils

import ir.alirezaiyan.moviz.sdk.base.utils.UnitTest
import kotlinx.coroutines.runBlocking
import org.junit.Test

class BaseRepositoryTest : UnitTest() {


    @Test
    fun test_with_null_response() {

        runBlocking {
            BaseRepositoryBot()
                    .withThisResponse(null)
                    .loginReturnsThiscall("IncorrectPayload")
                    .verifyNullResponse()
        }

    }

    @Test
    fun test_with_null_call_occurred_exception() {

        runBlocking {
            BaseRepositoryBot()
                    .withThisCall(null)
                    .loginReturnsThiscall("IncorrectPayload")
                    .verifyException()
        }
    }

    @Test
    fun `test with successful response`(){
        runBlocking {
            BaseRepositoryBot()
                    .verifySuccessful()
        }
    }

    @Test
    fun `test with unsuccessful response`(){
        runBlocking {
            BaseRepositoryBot()
                    .withErrorBody()
                    .loginReturnsThiscall("IncorrectPayload")
                    .executeCallReturns()
                    .verifyUnsuccessful()
        }
    }

}