package ir.alirezaiyan.moviz.feature.search

import androidx.lifecycle.MutableLiveData
import ir.alirezaiyan.moviz.data.model.search.MSMovie
import ir.alirezaiyan.moviz.domain.feature.search.SearchRepository
import ir.alirezaiyan.moviz.sdk.platform.platform.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepository: SearchRepository) : BaseViewModel() {

    var movie: MutableLiveData<MSMovie> = MutableLiveData()

    fun startSearch(movieName: String) {
        launch(job) {
            searchRepository.search(movieName).either(::handleFailure, ::handleSearch)
        }
    }

    private fun handleSearch(it: MSMovie) {
        launch(Dispatchers.Main) { movie.value = it }
    }
}