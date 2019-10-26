package com.lacourt.myapplication.network

import com.lacourt.myapplication.dto.DetailsDTO
import com.lacourt.myapplication.dto.GenreResponseDTO
import com.lacourt.myapplication.dto.MovieResponseDTO
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {
    //    https://api.themoviedb.org/3/movie/top_rated?api_key=<<api_key>>&language=en-US&page=1
    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("language")
        language: String,
        @Query("page")
        page: Int
    ): Observable<MovieResponseDTO>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("language")
        language: String,
        @Query("page")
        page: Int
    ): Observable<MovieResponseDTO>

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("language")
        language: String,
        @Query("page")
        page: Int
    ): Observable<MovieResponseDTO>

    @GET("search/movie")
    fun searchMovie(
        @Query("language")
        language: String,
        @Query("query")
        query: String,
        @Query("include_adult")
        adult: Boolean

    ): Call<MovieResponseDTO>

    //    https://api.themoviedb.org/3/movie/287947/recommendations?api_key=fef98cf6bd829f53836bb7d92b02d6ef&language=en-US&page=1
    @GET("movie/{id}")
    fun getRecommendations(
        @Path("id")
        id: Int,
        @Query("language")
        language: String,
        @Query("page")
        page: Int
    ): Observable<MovieResponseDTO>

    @GET("movie/{id}")//@GET("movie/{id}")
    fun getDetails2(
        @Path("id")
        id: Int
    ): Observable<DetailsDTO>

    @GET("movie/{id}")//@GET("movie/{id}")
    fun getDetails(
        @Path("id")
        id: Int
    ): Call<DetailsDTO>

    //    https://api.themoviedb.org/3/tv/latest?api_key=<<api_key>>&language=en-US
    @GET("tv/latest")
    fun getLatestTv(
        @Query("language")
        language: String,
        @Query("page")
        page: Int
    ): Observable<MovieResponseDTO>

    //    https://api.themoviedb.org/3/tv/top_rated?api_key=<<api_key>>&language=en-US&page=1
    @GET("tv/top_rated")
    fun getTopRatedTv(
        @Query("language")
        language: String,
        @Query("page")
        page: Int
    ): Observable<MovieResponseDTO>

    //    https://api.themoviedb.org/3/trending/tv/day?api_key=
    @GET("trending/tv/day")
    fun getTrendingTv(
        @Query("language")
        language: String,
        @Query("page")
        page: Int
    ): Observable<MovieResponseDTO>

    @GET("trending/movie/day")
    fun getTrendingMovies(
        @Query("language")
        language: String,
        @Query("page")
        page: Int
    ): Observable<MovieResponseDTO>

    @GET("genre/movie/list")
    fun getGenresObservable(): Observable<GenreResponseDTO>
}