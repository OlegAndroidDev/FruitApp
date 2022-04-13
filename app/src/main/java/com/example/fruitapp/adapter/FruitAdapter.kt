package com.example.fruitapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fruitapp.databinding.FruitItemBinding
import com.example.fruitapp.model.Fruit

class FruitAdapter(
    private val fruitList: MutableList<Fruit> = mutableListOf(),
    private val onFruitClicked : (Fruit) -> Unit
):RecyclerView.Adapter<FruitsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitsViewHolder {
        val view = FruitItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FruitsViewHolder(view, onFruitClicked)
    }

    override fun onBindViewHolder(holder: FruitsViewHolder, position: Int) {
        holder.bind(fruitList[position])
    }

    override fun getItemCount(): Int = fruitList.size


    fun updateFruits(updateFruit: List<Fruit>) {
        fruitList.clear()
        fruitList.addAll(updateFruit)
        notifyDataSetChanged()
    }
}

class FruitsViewHolder(
    private val binding: FruitItemBinding,
    private val onFruitClicked: (Fruit) -> Unit

) : RecyclerView.ViewHolder(binding.root) {

    fun bind(fruit: Fruit) {
        binding.fruitItemText.text = fruit.name
        binding.fruitFamilyValue.text = fruit.family
        binding.fruitGinusValue.text = fruit.genus

        binding.fruitItem.setOnClickListener() {
            onFruitClicked.invoke(fruit)
        }
    }
}
