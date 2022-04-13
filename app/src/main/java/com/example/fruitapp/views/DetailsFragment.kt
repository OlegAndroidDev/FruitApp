package com.example.fruitapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fruitapp.R
import com.example.fruitapp.databinding.FragmentDetailsBinding
import com.example.fruitapp.viewmodel.FruitViewModel
import com.squareup.picasso.Picasso

class DetailsFragment : BaseFragment() {

    private val binding by lazy {
        FragmentDetailsBinding.inflate(layoutInflater)
    }

    private val fruitItem by lazy {
        fruitViewModel.fruitItem
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.fruitItemText.text = fruitItem?.name
        binding.fruitFamilyValue.text = fruitItem?.family
        binding.fruitGinusValue.text = fruitItem?.genus

        binding.txtFruitCarbohydrateValue.text= fruitItem?.nutritions?.carbohydrates.toString()
        binding.txtFruitFatValue.text = fruitItem?.nutritions?.fat.toString()
        binding.txtFruitProteinValue.text = fruitItem?.nutritions?.protein.toString()
        binding.txtFruitCaloriesValue.text = fruitItem?.nutritions?.calories.toString()

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        return binding.root

    }
}