package com.montdeska.tmdb.ui.data.remote

import com.montdeska.tmdb.BuildConfig
import com.montdeska.tmdb.ui.data.models.Movie
import com.montdeska.tmdb.ui.data.models.Movies
import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.HeaderMap

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BuildConfig.API)
    .build()


interface ApiService {
    @GET("movie/popular")
    suspend fun getPopular(@HeaderMap headers: Map<String, String>): Movies

    @GET("movie/upcoming")
    suspend fun getUpcoming(@HeaderMap headers: Map<String, String>): Movies

    @GET("movie/latest")
    suspend fun getLatest(@HeaderMap headers: Map<String, String>): Movie
}

object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}