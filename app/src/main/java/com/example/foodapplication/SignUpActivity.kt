package com.example.foodapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodapplication.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignUpActivity : AppCompatActivity() {

    private lateinit var firstNameEt: EditText
    private lateinit var lastNameEt: EditText
    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText
    private lateinit var phoneEt: EditText
    private lateinit var signUpButton: Button
    private lateinit var signInTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize views
        firstNameEt = findViewById(R.id.firstNameEt)
        lastNameEt = findViewById(R.id.lastNameEt)
        emailEt = findViewById(R.id.emailEt)
        passwordEt = findViewById(R.id.passwordEt)
        phoneEt = findViewById(R.id.phoneEt)
        signUpButton = findViewById(R.id.signUpButton)
        signInTextView = findViewById(R.id.textView)


        signUpButton.setOnClickListener {
            registerUser()
        }


        signInTextView.setOnClickListener {
            // Navigate to SignInActivity
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun registerUser() {
        // Retrieve input values
        val firstName = firstNameEt.text.toString()
        val lastName = lastNameEt.text.toString()
        val email = emailEt.text.toString()
        val password = passwordEt.text.toString()
        val phone = phoneEt.text.toString()

        // Validate input (optional)
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val apiService = retrofit.create(RetrofitInstance::class.java)

        // Call the registerUser method
        apiService.registerUser(user).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@SignUpActivity, "Registration successful", Toast.LENGTH_SHORT).show()
                    // Optionally, navigate to the next screen
                } else {
                    Toast.makeText(this@SignUpActivity, "Registration failed: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@SignUpActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
