package ir.alirezaiyan.moviz.feature.search

import android.os.Bundle
import ir.alirezaiyan.moviz.R.layout
import ir.alirezaiyan.moviz.R
import ir.alirezaiyan.moviz.data.model.search.MSMovie
import ir.alirezaiyan.moviz.sdk.base.exception.Failure
import ir.alirezaiyan.moviz.sdk.platform.platform.BaseFragment
import kotlinx.android.synthetic.main.frg_search.*
import org.koin.android.ext.android.inject

class SearchFragment : BaseFragment() {

    private val vm: SearchViewModel by inject()

    override fun layoutId() = layout.frg_search

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(vm) {
            observe(movie, ::onSuccessLogin)
            failure(failure, ::onFailure)
        }

        btnSearch.setOnClickListener {
            showProgress()
            vm.startSearch(searchInput.text.toString())
        }
    }

    private fun onSuccessLogin(movie: MSMovie?) {
        hideProgress()
        tvResult.text = movie?.title
    }

    private fun onFailure(failure: Failure?) {
        hideProgress()
        when (failure) {
            is Failure.NetworkConnection -> {
                tvResult.text = "NetworkConnection"
            }
            is Failure.ServerError -> {
                tvResult.text = "ServerError"
            }
        }

    }
}