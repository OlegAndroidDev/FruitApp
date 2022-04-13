package com.example.fruitapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fruitapp.R
import com.example.fruitapp.adapter.FruitAdapter
import com.example.fruitapp.databinding.FragmentFruitBinding

class NewFruitFragment : BaseFragment() {

    private val binding by lazy{
        FragmentFruitBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_fruit, container, false)
    }
}