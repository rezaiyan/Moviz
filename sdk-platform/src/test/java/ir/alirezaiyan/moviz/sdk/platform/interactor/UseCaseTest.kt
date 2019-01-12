package ir.alirezaiyan.moviz.sdk.platform.interactor

import org.amshove.kluent.shouldEqual
import org.junit.Test
import ir.alirezaiyan.moviz.sdk.platform.AndroidTest
import ir.alirezaiyan.moviz.sdk.base.Either.Right
import ir.alirezaiyan.moviz.sdk.base.Either
import ir.alirezaiyan.moviz.sdk.base.exception.Failure
import kotlinx.coroutines.*


class UseCaseTest : AndroidTest() {

    private val TYPE_TEST = "Test"
    private val TYPE_PARAM = "ParamTest"

    private val useCase = MyUseCase()

    @Test
    fun `running use case should return 'Either' of use case type`() {
     runBlocking {
         val params = MyParams(TYPE_PARAM)
         val result = runBlocking { useCase.run(params) }

         result shouldEqual Either.Right(MyType(TYPE_TEST))
     }
    }


    @Test
    fun `should return incorrect data when executing use case`() {
           var result: Either<Failure, MyType>? = null

           val params = MyParams("TestParam")
           val onResult = { myResult: Either<Failure, MyType> -> result = myResult }
           useCase(params, onResult)

           result shouldEqual Either.Right(MyType(TYPE_TEST))
       }

    data class MyType(val name: String)
    data class MyParams(val name: String)

    private inner class MyUseCase : UseCase<MyType, MyParams>() {
        override suspend fun run(params: MyParams) = Right(MyType(TYPE_TEST))
    }
}
