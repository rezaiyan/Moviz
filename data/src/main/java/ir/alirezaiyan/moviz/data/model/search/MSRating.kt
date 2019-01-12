package ir.alirezaiyan.moviz.data.model.search

import com.google.gson.annotations.SerializedName

data class MSRating(
    @SerializedName("Source") val source: String,
    @SerializedName("Value") val rating: String
) {

    val summary: String get() = "$rating (${sourceShortName(source)})"

    private fun sourceShortName(ratingSource: String): String {
        return when {
            ratingSource.contains("Internet Movie Database") -> "IMDB"
            ratingSource.contains("Rotten Tomatoes") -> "RT"
            ratingSource.contains("Metacritic") -> "Metac"
            else -> ratingSource
        }
    }
}