package ir.alirezaiyan.moviz.sdk.platform.platform

import androidx.lifecycle.*
import ir.alirezaiyan.moviz.sdk.base.exception.Failure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Base ViewModel class with default Failure handling.
 * @see ViewModel
 * @see Failure
 */
abstract class BaseViewModel : ViewModel(), CoroutineScope {

    var failure: MutableLiveData<Failure> = MutableLiveData()

    var job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO
    override fun onCleared() {
        super.onCleared()
        job.cancel()
        job = Job()
    }

    protected fun handleFailure(it: Failure) {
        launch(Dispatchers.Main) {
            failure.value = it
        }
    }

    fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
        liveData.observe(this, Observer(body))

    fun <L : LiveData<Failure>> LifecycleOwner.failure(liveData: L, body: (Failure?) -> Unit) =
        liveData.observe(this, Observer(body))

}