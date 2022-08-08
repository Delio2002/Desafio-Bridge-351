package com.example.pokemonapp2022.presenter.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pokemonapp2022.databinding.ItemPokemonListBinding
import com.example.pokemonapp2022.presenter.home.HomeFragmentDirections
import com.example.pokemonapp2022.presenter.home.dataui.PokemonDataUi
import com.example.pokemonapp2022.presenter.utils.DiffUtilGeneric



class PokemonAdapter :  RecyclerView.Adapter<PokemonAdapter.HomeViewHolder>() {

    private var pokemonList = emptyList<PokemonDataUi>()

    class HomeViewHolder(val binding: ItemPokemonListBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemPokemonListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount() = pokemonList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.binding.apply {
            mtvName.text = pokemonList[position].name.toUpperCase()
            mivPhoto.load("${pokemonList[position].imageUrl}"){
                crossfade(true)
             }
        }

        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragment2ToDetailFragment(pokemonList[position])
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(list: List<PokemonDataUi>){
        val pokemonDiffUtil = DiffUtilGeneric(this.pokemonList, list)
        val pokemonResult = DiffUtil.calculateDiff(pokemonDiffUtil)
        this.pokemonList = list
        pokemonResult.dispatchUpdatesTo(this)
    }
}