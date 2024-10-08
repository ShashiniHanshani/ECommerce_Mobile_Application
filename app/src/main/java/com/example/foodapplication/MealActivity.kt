package com.example.foodapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.foodapplication.databinding.ActivityitemBinding
import com.example.foodapplication.modelClass.item
import com.example.foodapplication.viewModel.HomeViewModel
import com.example.foodapplication.viewModel.itemViewModel

class itemActivity : AppCompatActivity() {
    private lateinit var itemId:String
    private lateinit var itemName:String
    private lateinit var itemThumb:String
    private lateinit var binding:ActivityitemBinding
    private lateinit var itemMvvm : itemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityitemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemMvvm = ViewModelProvider(this)[itemViewModel::class.java]
        getitemInformationromIntent()
        setInformationInViews()
        observeritemDetailsiveData()

        itemMvvm.getitemDetails(itemId)

    }

    private fun observeritemDetailsiveData() {
        itemMvvm.observeritemDetailsLiveData().observe(this, Observer { item ->
            if (item != null)
            {
                binding.tvCategory.text = item.category
                binding.tvItemprice.text = item.category // Consider replacing this with the actual price
                binding.tvItemdesc.text = item.description
            }
        })
    }


    private fun setInformationInViews() {
        // Ensure itemThumb is not empty before loading
        if (itemThumb.isNotEmpty()) {
            Glide.with(this) // Use the activity context instead of application context
                .load(itemThumb)
                .into(binding.imgitemDetail)
        } else {
            // Handle the case where itemThumb is empty or invalid
            binding.imgitemDetail.setImageResource(R.drawable.baseline_category) // Use a placeholder image
        }

        binding.collapsingToolbar.title = itemName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getitemInformationromIntent(){
        val intent= intent

        itemId = intent.getStringExtra(HomeFragment.item_ID) ?: ""
        itemName = intent.getStringExtra(HomeFragment.item_NAME) ?: "Unknown item"
        itemThumb = intent.getStringExtra(HomeFragment.item_THUMB) ?: ""

    }
}