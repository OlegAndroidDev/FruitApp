package com.example.fruitapp.repository

import android.util.Log
import com.example.fruitapp.model.Fruit
import com.example.fruitapp.rest.FruitApi
import com.example.fruitapp.viewmodel.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class FruitApiRepositoryImpl(
    private val fruitApi: FruitApi
):FruitApiRepository{
    override fun getFruits(selected_path: String): Flow<ResultState> {
        return flow{
            try {
                val response = fruitApi.getFruits(selected_path)
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(ResultState.SUCCESS(it))
                    }?: throw Exception("Response is Null")
                }
                else {
                    throw Exception("Unsuccessful Response")
                }
            }
            catch (e: Exception) {
                emit(ResultState.ERROR(e))
            }
        }
    }
    override suspend fun getFruitByName(name_path: String): Response<Fruit> {
        return fruitApi.getFruitByName(name_path)
    }
}

interface FruitApiRepository {
fun getFruits(
    selected_path: String
): Flow<ResultState>

suspend fun getFruitByName(
    name_path: String
):Response<Fruit>
}

