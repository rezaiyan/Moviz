package ir.alirezaiyan.moviz.data.model.search

import com.google.gson.annotations.SerializedName

data class MSMovie(
    @SerializedName("Result") val result: Boolean,
    @SerializedName("Error") val errorMessage: String? = null,
    @SerializedName("Title") val title: String = "",
    @SerializedName("Poster") val posterUrl: String = "",
    @SerializedName("Ratings") val ratings: List<MSRating> = emptyList()
) {
    val ratingSummary: String
        get() {
            return ratings.fold("") { summary, msRating -> "$summary\n${msRating.summary}" }
        }
}