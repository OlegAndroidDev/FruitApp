package com.example.fruitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fruitapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bottomNavigation = binding.fruitNavigationBar


        val navController = findNavController(R.id.nav_container)
        bottomNavigation.setupWithNavController(navController)

        //binding.fruitNavigationBar.setupWithNavController(navController)


//        binding.fruitNavigationBar.apply {
//            setupWithNavController(findNavController(R.id.nav_container))
//        }
    }
}