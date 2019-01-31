package ir.alirezaiyan.moviz.domain.feature.search

import ir.alirezaiyan.moviz.data.feature.search.SearchRepository
import ir.alirezaiyan.moviz.data.model.search.MSMovie
import ir.alirezaiyan.moviz.sdk.base.interactor.UseCase

class SearchUseCase(private val repository: SearchRepository): UseCase<MSMovie, String>() {

    override suspend fun run(params: String) = repository.search(params)

}