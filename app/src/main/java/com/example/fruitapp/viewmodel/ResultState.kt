package com.example.fruitapp.viewmodel

sealed class ResultState {
    object LOADING: ResultState()
    //object INIT: ResultState()
    class SUCCESS<T>(val fruits : T) : ResultState()
    class ERROR(val throwable: Throwable) : ResultState()
}