package ir.alirezaiyan.moviz.sdk.platform.platform


import androidx.lifecycle.MutableLiveData
import ir.alirezaiyan.moviz.sdk.base.exception.Failure
import ir.alirezaiyan.moviz.sdk.test.AndroidTest
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Test



class BaseViewModelTest : AndroidTest() {


    @Test
    fun `should handle failure by updating live data`() {
        val viewModel = MyViewModel()

        viewModel.handleError(Failure.NetworkConnection())

        val failure = viewModel.failure
        val error = viewModel.failure.value

        failure shouldBeInstanceOf MutableLiveData::class.java
        error shouldBeInstanceOf Failure.NetworkConnection::class.java
    }

    private class MyViewModel : BaseViewModel() {
        fun handleError(failure: Failure) = handleFailure(failure)
    }
}