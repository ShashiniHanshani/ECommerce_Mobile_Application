package com.example.foodapplication.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapplication.modelClass.Meal
import com.example.foodapplication.modelClass.MealList
import com.example.foodapplication.modelClass.ecommerce
import com.example.foodapplication.modelClass.ecommerceItem
import com.example.foodapplication.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel(): ViewModel() {
    private var mealDetailsLiveData = MutableLiveData<ecommerceItem>()

    fun getMealDetails(id:String){
        RetrofitInstance.api.getMealDetails(id).enqueue(object: Callback<ecommerce>{
            override fun onResponse(call: Call<ecommerce>, response: Response<ecommerce>) {
                if(response.body() != null) {
                    val randomMeal: ecommerceItem = response.body()!!.meals[0]
                    Log.d("check random meal", "meal id ${randomMeal.id} name ${randomMeal.title}")

                    mealDetailsLiveData.value = response.body()!!.meals[0]

                } else {
                    Log.d("HomeFragment", "No meal found")
                }
            }

            override fun onFailure(call: Call<ecommerce>, t: Throwable) {
                Log.d("MealActivity", t.message.toString())
            }
        })
    }
    fun observerMealDetailsLiveData():LiveData<ecommerceItem>{
        return mealDetailsLiveData
    }
}