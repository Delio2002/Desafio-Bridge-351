package com.example.pokemonapp2022.presenter.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp2022.databinding.ItemListBinding
import com.example.pokemonapp2022.presenter.detail.dataui.AbilityDataUi
import com.example.pokemonapp2022.presenter.utils.DiffUtilGeneric


class AbilitiesAdapter :  RecyclerView.Adapter<AbilitiesAdapter.HomeViewHolder>() {

    private var abilityList = emptyList<AbilityDataUi>()

    class HomeViewHolder(val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount() = abilityList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.binding.apply {
            tvAbilities.text = abilityList[position].ability.name
        }
    }

    fun setData(list: List<AbilityDataUi>){
        val pokemonDiffUtil = DiffUtilGeneric(abilityList, list)
        val pokemonResult = DiffUtil.calculateDiff(pokemonDiffUtil)
        this.abilityList = list
        pokemonResult.dispatchUpdatesTo(this)
    }
}