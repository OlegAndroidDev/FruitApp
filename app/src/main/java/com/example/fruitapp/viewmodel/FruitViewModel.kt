package com.example.fruitapp.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.fruitapp.model.Fruit
import com.example.fruitapp.repository.FruitApiRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FruitViewModel(
    private val fruitApiRepository: FruitApiRepository,
    private val Dispatcher: CoroutineDispatcher

) : ViewModel() {
    private val _fruitMutable : MutableLiveData<ResultState> = MutableLiveData(ResultState.LOADING)
    val fruitLiveData : LiveData<ResultState> get() = _fruitMutable
    var fruitItem : Fruit? = null
    var job: Job? = null

    fun getFruits(
        selected_root:String
    ) {
        _fruitMutable.postValue(ResultState.LOADING)

        job = viewModelScope.launch(Dispatcher) {
            fruitApiRepository.getFruits(selected_root)
                .collect{state ->
                _fruitMutable.postValue(state)
            }
        }
    }

    fun getFruitByName(
        selected_root:String
    ) {
        _fruitMutable.postValue(ResultState.LOADING)
        viewModelScope.launch(Dispatcher) {
            try {
                val response = fruitApiRepository.getFruitByName(selected_root)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _fruitMutable.postValue(ResultState.SUCCESS(it))
                    }?: throw Exception("Response is Null")
                }
                else {
                    throw Exception("Unsuccessful Response")
                }
            }
            catch (e: Exception) {
                _fruitMutable.postValue(ResultState.ERROR(e))
            }
        }
    }
    fun InitFruitMutable() {
        _fruitMutable.postValue(ResultState.LOADING)
    }
}