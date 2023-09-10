package com.mansi.jokesapplication.di

import com.mansi.jokesapplication.BuildConfig
import com.mansi.jokesapplication.data.remote.ApiService
import com.mansi.jokesapplication.domain.repository.JokesRepository
import com.mansi.jokesapplication.domain.repository.JokesRepositoryImp
import com.mansi.jokesapplication.usecase.GetJokesUseCase
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 30L

val NetworkModule = module {

    single { createService(get()) }

    single { createRetrofit(get(), BuildConfig.BASE_URL) }
    single { createOkHttpClient() }

    single { MoshiConverterFactory.create() }

    single { Moshi.Builder().build() }

}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create()).build()
}

fun createService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

fun createJokeRepository(apiService: ApiService): JokesRepository {
    return JokesRepositoryImp(apiService)
}

fun createGetJokesUseCase(jokesRepository: JokesRepository): GetJokesUseCase {
    return GetJokesUseCase(jokesRepository)
}
