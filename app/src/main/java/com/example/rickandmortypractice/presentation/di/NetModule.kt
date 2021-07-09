package com.example.rickandmortypractice.presentation.di

import com.example.rickandmortypractice.BuildConfig
import com.example.rickandmortypractice.data.api.RickAndMortyAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Singleton
    @Provides
    fun providerRetrofit() : Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.URL)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun providerRickAndMortyAPIService(retrofit : Retrofit) : RickAndMortyAPIService {
        return retrofit.create(RickAndMortyAPIService::class.java)
    }

}