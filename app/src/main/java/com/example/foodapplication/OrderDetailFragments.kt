package com.example.foodapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderDetailFragment : AppCompatActivity() {

    private lateinit var tvProductCartName: TextView
    private lateinit var tvProductCartPrice: TextView
    private lateinit var tvBillingProductQuantity: TextView
    private lateinit var tvOrderTotal: TextView
    private lateinit val orderId = TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_order_detail)

        tvProductCartName = findViewById(R.id.tvProductCartName)
        tvProductCartPrice = findViewById(R.id.tvProductCartPrice)
        tvBillingProductQuantity = findViewById(R.id.tvBillingProductQuantity)
        tvOrderTotal = findViewById(R.id.tvOrderTotal)

        // Load order details
        loadOrderDetails(orderId)
    }

    private fun loadOrderDetails(orderId: String) {

        val orderDetailsCall = RetrofitClient.instance.getOrderDetails(orderId)

        orderDetailsCall.enqueue(object : Callback<OrderDetailsResponse> {
            override fun onResponse(call: Call<OrderDetailsResponse>, response: Response<OrderDetailsResponse>) {
                if (response.isSuccessful) {
                    val orderDetails = response.body()
                    orderDetails?.let {
                        // Assuming OrderDetailsResponse has these fields
                        tvProductCartName.text = it.productName
                        tvProductCartPrice.text = "$${it.productPrice}"
                        tvBillingProductQuantity.text = it.quantity.toString()
                        tvOrderTotal.text = "Total: $${it.totalPrice}"
                    }
                } else {
                    // Handle error case
                }
            }

            override fun onFailure(call: Call<OrderDetailsResponse>, t: Throwable) {
                // Handle failure case
            }
        })
    }
}
