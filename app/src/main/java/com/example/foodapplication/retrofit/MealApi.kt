package com.example.foodapplication.retrofit

import com.example.foodapplication.modelClass.MealList
import com.example.foodapplication.modelClass.ecommerce
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    fun getRandomMeal(): Call<ecommerce>

    @GET("lookup.php?")
    fun getMealDetails(@Query("i")id:String) : Call<ecommerce>
}