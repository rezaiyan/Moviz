package ir.alirezaiyan.moviz.feature.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import com.squareup.picasso.Picasso
import ir.alirezaiyan.moviz.R
import ir.alirezaiyan.moviz.R.layout
import ir.alirezaiyan.moviz.data.model.search.MSMovie
import ir.alirezaiyan.moviz.sdk.base.exception.Failure
import ir.alirezaiyan.moviz.sdk.platform.extension.clear
import ir.alirezaiyan.moviz.sdk.platform.extension.nextLine
import ir.alirezaiyan.moviz.sdk.platform.extension.setOnRightDrawableClickListener
import ir.alirezaiyan.moviz.sdk.platform.extension.toSpan
import ir.alirezaiyan.moviz.sdk.platform.platform.BaseFragment
import kotlinx.android.synthetic.main.frg_search.*
import org.koin.android.ext.android.inject

private const val actorTitle = "Actors"
private const val genreTitle = "Genre"

class SearchFragment : BaseFragment() {

    private val vm: SearchViewModel by inject()
    private val titleSize by lazy {
        resources.getDimension(R.dimen.titleSize)
    }

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

        searchInput.setOnRightDrawableClickListener {
            searchInput.text.clear()
            loadImage("Null")
            tvResult.clear()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun onSuccessLogin(movie: MSMovie?) {
        hideProgress()

        movie?.let {

            if (it.response.toBoolean()) {
                searchInput.setText(it.title)

                val finalText = TextUtils.concat(
                    actorTitle.toSpan(titleSize.toInt()), " : ${it.actors}".nextLine(),
                    genreTitle.toSpan(titleSize.toInt()), " : ${it.genre}"
                )
                tvResult.text = finalText

                loadImage(it.posterUrl)
            } else
                tvResult.text = "Not found"
        }

    }

    @SuppressLint("SetTextI18n")
    private fun onFailure(failure: Failure?) {
        hideProgress()
        when (failure) {
            is Failure.NetworkConnection -> {
                tvResult.text = "Network connection"
            }
            is Failure.ServerError -> {
                tvResult.text = failure.message
            }
        }

    }

    @Suppress("DEPRECATION")
    private fun loadImage(imageUrl: String) =
        Picasso.get().load(imageUrl).placeholder(resources.getDrawable(R.drawable.placeholder)).fit().centerInside()
            .into(imageView)
}