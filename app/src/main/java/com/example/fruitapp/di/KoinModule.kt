package com.example.fruitapp.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.fruitapp.repository.FruitApiRepository
import com.example.fruitapp.repository.FruitApiRepositoryImpl
import com.example.fruitapp.rest.FruitApi
import com.example.fruitapp.viewmodel.FruitViewModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    fun providesGson() = Gson()

    fun providesLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()


    fun providesFruitApi(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(FruitApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(FruitApi::class.java)

    fun providesFruitRepository(fruitApiService: FruitApi) : FruitApiRepository =
        FruitApiRepositoryImpl(fruitApiService)

    single { providesFruitRepository(get()) }
    single { providesLoggingInterceptor() }
    single { providesOkHttpClient(get()) }
    single { providesFruitApi(get()) }
}

val viewModelModule = module {
    fun providesDispatcher() : CoroutineDispatcher = Dispatchers.IO
    single { providesDispatcher() }
    viewModel {FruitViewModel(get(), get()) }
}
