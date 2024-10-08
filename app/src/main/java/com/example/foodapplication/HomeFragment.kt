package com.example.foodapplication

import androidx.lifecycle.ViewModelProvider
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.foodapplication.databinding.FragmentHomeBinding
import com.example.foodapplication.modelClass.item
import com.example.foodapplication.modelClass.ecommerceItem
import com.example.foodapplication.viewModel.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm :HomeViewModel
    private lateinit var randomitem: ecommerceItem

    companion object{
        const val item_ID ="com.example.foodapplication.fragments.iditem"
        const val item_NAME ="com.example.foodapplication.fragments.nameitem"
        const val item_THUMB ="com.example.foodapplication.fragments.thumbitem"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm = ViewModelProvider(this)[HomeViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Use ViewBinding to inflate the layout
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeMvvm.getRandomitem()
        onRandomitemClick()
        observerRandomitem()


        val imgCart: ImageView = view.findViewById(R.id.img_cart)
        imgCart.setOnClickListener {
            // Create an Intent to navigate to CartActivity
            val intent = Intent(activity, CartFragment::class.java)
            startActivity(intent)
        }


    }
    //when clicking the item
    private fun onRandomitemClick() {
        binding.imgRandomitem.setOnClickListener {
            Log.d("HomeFragment", "Random item card clicked")
            val intent = Intent(activity, itemActivity::class.java)
            intent.putExtra(item_ID,randomitem.id)
            intent.putExtra(item_NAME,randomitem.title)
            intent.putExtra(item_THUMB, randomitem.category)

            startActivity(intent)
        }
    }

    private fun observerRandomitem() {
        homeMvvm.observerRandomitemLiveData().observe(viewLifecycleOwner, Observer { item ->
            if (item != null) {
                // Load the item image using Glide
                Glide.with(this)
                    .load(item.category)
                    .into(binding.imgRandomitem)

                this.randomitem= item
            }
        })
    }


}
