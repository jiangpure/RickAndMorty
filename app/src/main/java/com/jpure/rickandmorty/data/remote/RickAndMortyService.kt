package com.jpure.rickandmorty.data.remote

import com.jpure.rickandmorty.data.entities.RickAndMortyEntity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author Jp
 * @date 2021/2/25.
 */
interface RickAndMortyService {
    @GET("character/")
    suspend  fun getRoleList(
        @Query("page") pageId:Int=0
    ): RickAndMortyEntity


    companion object{
        /**
         * 主Url地址
         */
        private const val BASE_API = "https://rickandmortyapi.com/api/";

        /**
         * 创建service对象
         */
        fun  create(): RickAndMortyService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_API)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(RickAndMortyService::class.java)
        }
    }
}