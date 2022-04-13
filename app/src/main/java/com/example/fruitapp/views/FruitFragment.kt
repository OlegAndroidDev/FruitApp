package com.example.fruitapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fruitapp.adapter.FruitAdapter
import com.example.fruitapp.databinding.FragmentFruitBinding
import com.example.fruitapp.model.Fruit
import com.example.fruitapp.viewmodel.ResultState


class FruitFragment : BaseFragment() {

    private val binding by lazy{
        FragmentFruitBinding.inflate(layoutInflater)
    }

//    private val fruitAdapter by lazy {
//        FruitAdapter(onFruitClicked = {
//
//        })
//    }

    private val _fruit: MutableLiveData<ResultState> = MutableLiveData(ResultState.LOADING)
    val fruit : LiveData<ResultState> get() = _fruit

    private var _listToUpdate = mutableListOf<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fruitLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false)

        binding.fruitRecycler.apply {
            layoutManager = fruitLayoutManager
            adapter = fruitAdapter
        }

        fruitViewModel.fruitLiveData.observe(viewLifecycleOwner) { state ->
            when(state) {
                is ResultState.LOADING -> {
                   Toast.makeText(
                        requireContext(), "Loading ....", Toast.LENGTH_LONG
                    ).show()
                }
                is ResultState.SUCCESS<*> -> {
                    val fruits = state.fruits as List<Fruit>
                    fruitAdapter.updateFruits(fruits)
//                    Toast.makeText(
//                        requireContext(), "Success ....", Toast.LENGTH_LONG
//                   ).show()
                    fruitViewModel.InitFruitMutable()
                }

                is ResultState.ERROR -> {
                    Toast.makeText(
                        requireContext(), state.throwable.localizedMessage, Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        fruitViewModel.getFruits("all")
        //fruitViewModel.InitFruitMutable()
        return binding.root

    }
}