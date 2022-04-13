package com.example.fruitapp.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fruitapp.R
import com.example.fruitapp.databinding.FragmentFindBinding
import com.example.fruitapp.databinding.FragmentFruitBinding
import com.example.fruitapp.model.Fruit
import com.example.fruitapp.viewmodel.ResultState
import com.google.android.material.snackbar.Snackbar

class FindFragment : BaseFragment() {

    private val binding by lazy{
        FragmentFindBinding.inflate(layoutInflater)
    }

    private val _fruit: MutableLiveData<ResultState> = MutableLiveData(ResultState.LOADING)
    val fruit : LiveData<ResultState> get() = _fruit


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fruitViewModel.fruitLiveData.observe(viewLifecycleOwner,::handleState)

        binding.btnSearch.setOnClickListener(){
            val fruitName = binding.editName.getText().trim()
            if(fruitName.isNotEmpty() && binding.editName.getText().length > 1){
                fruitViewModel.getFruitByName(fruitName.toString().lowercase())
            }else
            {
                val snack = Snackbar.make(it,"Please enter a valid name",Snackbar.LENGTH_LONG)
                snack.show()
            }
        }
        return binding.root
    }
    fun handleState(state: ResultState){
        fruitViewModel.fruitLiveData.observe(viewLifecycleOwner) { state ->
            when(state) {
                is ResultState.SUCCESS<*> -> {

                    val fruit = state.fruits as Fruit
                    binding.fruitItemText.text = fruit.name
                    binding.fruitFamilyValue.text = fruit.family
                    binding.fruitGinusValue.text = fruit.genus

                    binding.txtFruitCarbohydrateValue.text= fruit.nutritions.carbohydrates.toString()
                    binding.txtFruitFatValue.text = fruit.nutritions.fat.toString()
                    binding.txtFruitProteinValue.text = fruit.nutritions.protein.toString()
                    binding.txtFruitCaloriesValue.text = fruit.nutritions.calories.toString()
                    fruitViewModel.InitFruitMutable()
                }
                is ResultState.ERROR -> {
                    Toast.makeText(
                        requireContext(), state.throwable.localizedMessage, Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}