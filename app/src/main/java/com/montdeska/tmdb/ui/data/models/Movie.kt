package com.montdeska.tmdb.ui.data.models

data class Movie(
    val id: Int,
    val title: String,
    val popularity: Double? = null,
    val budget: Double? = null,
    val vote_count: Int? = null,
    val video: Boolean? = null,
    val poster_path: String? = null,
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val genre_ids: List<Int>? = null,
    val vote_average: Float? = null,
    val overview: String? = null,
    val release_date: String? = null,
    val homepage: String? = null,
    val imdb_id: String? = null,
    val status: String? = null,
    val tagline: String? = null,
    val production_companies: List<String>? = null,
    val production_countries: List<String>? = null,
    val revenue: Double? = null
)