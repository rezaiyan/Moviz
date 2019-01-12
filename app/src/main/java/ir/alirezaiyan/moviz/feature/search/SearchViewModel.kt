package ir.alirezaiyan.moviz.feature.search

import androidx.lifecycle.MutableLiveData
import ir.alirezaiyan.moviz.data.model.search.MSMovie
import ir.alirezaiyan.moviz.domain.feature.search.SearchUseCase
import ir.alirezaiyan.moviz.sdk.platform.platform.BaseViewModel

class SearchViewModel(private val loginUseCase: SearchUseCase):BaseViewModel(){

    var movie: MutableLiveData<MSMovie> = MutableLiveData()

    fun startSearch(movieName: String) = loginUseCase(movieName) { it.either(::handleFailure, ::handleSearch) }

    private fun handleSearch(movie: MSMovie) {
        this.movie.value = movie
    }
}