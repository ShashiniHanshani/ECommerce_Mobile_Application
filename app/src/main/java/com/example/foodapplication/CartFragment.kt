package com.example.foodapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapplication.databinding.FragmentCartBinding
import com.example.foodapplication.models.CartItem
import com.example.foodapplication.network.ApiClient
import com.example.foodapplication.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartFragment : AppCompatActivity() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var cartAdapter: CartAdapter
    private var cartItems: List<CartItem> = listOf()
    private var totalPrice: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        fetchCartItems()
        binding.btnCheckout.setOnClickListener { checkout() }
        binding.imgCloseCart.setOnClickListener { finish() } // Close cart
    }

    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(cartItems)
        binding.rvCart.layoutManager = LinearLayoutManager(this)
        binding.rvCart.adapter = cartAdapter
    }

    private fun fetchCartItems() {
        val apiService = ApiClient.getClient().create(ApiService::class.java)
        apiService.getCartItems().enqueue(object : Callback<List<CartItem>> {
            override fun onResponse(call: Call<List<CartItem>>, response: Response<List<CartItem>>) {
                if (response.isSuccessful) {
                    cartItems = response.body() ?: emptyList()
                    calculateTotalPrice()
                    cartAdapter.updateCartItems(cartItems)
                }
            }

            override fun onFailure(call: Call<List<CartItem>>, t: Throwable) {
                // Handle failure
            }
        })
    }

    private fun calculateTotalPrice() {
        totalPrice = cartItems.sumOf { it.price * it.quantity }
        binding.tvTotalprice.text = "Rs.${totalPrice.format()}"
    }

    private fun checkout() {
        val intent = Intent(this, BillingActivity::class.java)
        startActivity(intent)
    }
}


fun Double.format(): String {
    return String.format("%.2f", this)
}
