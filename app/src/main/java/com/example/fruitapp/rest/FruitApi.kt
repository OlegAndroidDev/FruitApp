package com.example.fruitapp.rest

import com.example.fruitapp.model.Fruit
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FruitApi {
    @GET(ALL_ENDPOINT)
    suspend fun getFruits(
        @Path("selected_path") selected_path: String
    ): Response<List<Fruit>>

    @GET(ALL_ENDPOINT)
    suspend fun getFruitByName(
        @Path("selected_path") selected_path: String
    ): Response<Fruit>

    companion object {
        //https://www.fruityvice.com/api/fruit/all
        //https://www.fruityvice.com/api/fruit/family/Rosaceae
        //https://www.fruityvice.com/api/fruit/family/Musaceae
        //https://www.fruityvice.com/api/fruit/genus/Prunus
        //https://www.fruityvice.com/api/fruit/banana

        const val BASE_URL = "https://www.fruityvice.com/api/"
        private const val ALL_ENDPOINT = "fruit/{selected_path}"
        private const val FAMILY_ENDPOIT = "family"
        private const val GENUS_ENDPOIT = "family"
    }
}