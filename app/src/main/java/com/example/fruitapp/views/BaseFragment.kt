package com.example.fruitapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fruitapp.R
import com.example.fruitapp.adapter.FruitAdapter
import com.example.fruitapp.viewmodel.FruitViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

open class BaseFragment : Fragment() {
    protected val fruitViewModel: FruitViewModel by sharedViewModel()

    protected val fruitAdapter by lazy {
        FruitAdapter(onFruitClicked = {
//            fruitViewModel.fruitLiveData = it
            fruitViewModel.fruitItem = it
            findNavController().navigate(R.id.detailsFragment)
        })
    }
}