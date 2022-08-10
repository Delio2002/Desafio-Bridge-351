package com.example.pokemonapp2022.presenter.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pokemonapp2022.databinding.ItemPokemonListBinding
import com.example.pokemonapp2022.presenter.home.HomeFragmentDirections
import com.example.pokemonapp2022.presenter.home.dataui.PokemonItemDataUi


class PokemonAdapter : PagingDataAdapter<PokemonItemDataUi, PokemonAdapter.HomeViewHolder>(COMPARATOR) {

    class HomeViewHolder(val binding: ItemPokemonListBinding): RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.binding.apply {
            mtvName.text = pokemon!!.name.toUpperCase()
            mivPhoto.load("${pokemon.imageUrl}"){
                crossfade(true)
            }
        }

        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragment2ToDetailFragment(pokemon!!)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemPokemonListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<PokemonItemDataUi>() {
            override fun areItemsTheSame(oldItem: PokemonItemDataUi, newItem: PokemonItemDataUi): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: PokemonItemDataUi, newItem: PokemonItemDataUi): Boolean =
                oldItem == newItem

        }
    }
}