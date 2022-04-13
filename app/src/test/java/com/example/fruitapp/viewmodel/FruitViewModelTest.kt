package com.example.fruitapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.fruitapp.model.fruits
import com.example.fruitapp.repository.FruitApiRepository
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.impl.annotations.MockK
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.Instant

class FruitViewModelTest{
    @get: Rule val rule = InstantTaskExecutorRule()

    //@MockK
//    private val testDispatcher = StandardTestDispatcher()
//    private val testCoroutineScope = TestScope()
    private val testDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testDispatcher)
    private val  mockRepository= mockk<FruitApiRepository>(relaxed = true)
    lateinit var target: FruitViewModel

    @Before
    fun setUp(){
        Dispatchers.setMain(testDispatcher)
        target = FruitViewModel(mockRepository,testDispatcher)
    }

    @After
        fun shutdown() {
            clearAllMocks()
        Dispatchers.resetMain()
        //testCoroutineScope.cancel()
        testCoroutineScope.cleanupTestCoroutines()
        }

    @Test
    fun `get fruits when trying to load returns loading state`(){
        // AAA
        // Assign
        val stateList = mutableListOf<ResultState>()
        target.fruitLiveData.observeForever {
            stateList.add(it)
        }

        // Action
        target.getFruits("all")

        // Assert
        assertThat(stateList).isNotEmpty()
        assertThat(stateList).hasSize(2)
        assertThat(stateList[0]).isInstanceOf(ResultState.LOADING::class.java)
        assertThat(stateList[1]).isInstanceOf(ResultState.LOADING::class.java)
    }

//    @Test
//    fun `get fruits when trying to load returns Success state`(){
//        // AAA
//        // Assign
//        every{mockRepository.getFruits("all")} returns flowOf(ResultState.SUCCESS(mockk<fruits>()))
//        val stateList = mutableListOf<ResultState>()
//        target.fruitLiveData.observeForever {
//            stateList.add(it)
//        }
//
//        // Action
//        target.getFruits("all")
//
//        // Assert
//        assertThat(stateList).isNotEmpty()
//        assertThat(stateList).hasSize(3)
//        assertThat(stateList[0]).isInstanceOf(ResultState.LOADING::class.java)
//        assertThat(stateList[1]).isInstanceOf(ResultState.LOADING::class.java)
//        assertThat(stateList[1]).isInstanceOf(ResultState.SUCCESS::class.java)
//    }
}