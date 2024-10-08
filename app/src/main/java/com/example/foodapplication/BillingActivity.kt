package com.example.foodapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapplication.databinding.ActivityBillingBinding

class BillingActivity : AppCompatActivity() {

    // ViewBinding for accessing views
    private lateinit var binding: ActivityBillingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize view binding
        binding = ActivityBillingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
        handlePlaceOrder()
    }

    // Set up toolbar and close functionality
    private fun setupToolbar() {
        binding.imageCloseBilling.setOnClickListener {
            finish()
        }
    }


    private fun setupRecyclerView() {
        val productList = listOf
        val productAdapter = ProductAdapter(productList)
        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(this@BillingActivity)
            adapter = productAdapter
        }
    }

    // Handle place order button click
    private fun handlePlaceOrder() {
        binding.btnPlaceOrder.setOnClickListener {
            val address = getShippingAddress()
            if (address.isNullOrEmpty()) {
                Toast.makeText(this, "Please enter a valid shipping address", Toast.LENGTH_SHORT).show()
            } else {
                placeOrder(address)
            }
        }
    }

    private fun getShippingAddress(): String {
        // Get references to the EditText fields
        val addressTitle = findViewById<EditText>(R.id.ed_address_title).text.toString()
        val fullName = findViewById<EditText>(R.id.ed_full_name).text.toString()
        val street = findViewById<EditText>(R.id.ed_street).text.toString()
        val city = findViewById<EditText>(R.id.ed_city).text.toString()
        val state = findViewById<EditText>(R.id.ed_state).text.toString()
        val phone = findViewById<EditText>(R.id.ed_phone).text.toString()

        // Concatenate the values to form a complete address
        return "$fullName, $street, $city, $state\nPhone: $phone\nAddress Type: $addressTitle"
    }


    private fun placeOrder(address: String) {
        // TODO: Implement the API call to place the order
        Toast.makeText(this, "Order placed successfully for address: $address", Toast.LENGTH_SHORT).show()

        finish()
    }
}
