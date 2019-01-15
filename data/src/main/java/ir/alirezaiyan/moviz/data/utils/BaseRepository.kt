package ir.alirezaiyan.moviz.data.utils

import com.squareup.moshi.Moshi
import ir.alirezaiyan.moviz.data.model.ErrorModel
import ir.alirezaiyan.moviz.sdk.base.Either
import ir.alirezaiyan.moviz.sdk.base.exception.Failure
import retrofit2.Call
import java.net.HttpURLConnection

open class BaseRepository {

    @Suppress("SENSELESS_COMPARISON")
    fun <T, R> request(call: Call<T>?, transform: (T) -> R): Either<Failure, R> {

        return try {
            val response = call!!.execute()

            if (response == null)
                Either.Left(Failure.NetworkConnection())
            else {
                val errorBody = response.errorBody()
                when {
                    response.isSuccessful -> Either.Right(transform((response.body()!!)))
                    (!response.isSuccessful && errorBody != null) -> {
                        if (response.code() == HttpURLConnection.HTTP_INTERNAL_ERROR) {
                            Either.Left(Failure.ServerError(response.code(), "Your API key is incorrect"))
                        }else{
                            val moshi = Moshi.Builder().build()
                            val jsonAdapter = moshi.adapter<ErrorModel>(ErrorModel::class.java)
                            val errorModel = jsonAdapter.fromJson(errorBody.string())

                            val errorMessage = errorModel!!.msg
                            val errorCode = response.code()

                            Either.Left(Failure.ServerError(errorCode, errorMessage))
                        }

                    }
                    else -> Either.Left(Failure.NetworkConnection())
                }

            }
        } catch (exception: Throwable) {
            Either.Left(Failure.FeatureFailure(exception))
        }
    }


}