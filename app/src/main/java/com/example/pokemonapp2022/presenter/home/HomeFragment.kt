package com.example.pokemonapp2022.presenter.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp2022.MainActivity
import com.example.pokemonapp2022.R
import com.example.pokemonapp2022.databinding.FragmentHomeBinding
import com.example.pokemonapp2022.presenter.home.adapter.PokemonAdapter
import com.example.pokemonapp2022.presenter.home.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var rvPokemon: RecyclerView
    private val mAdapter: PokemonAdapter by lazy { PokemonAdapter() }
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupAppBar()
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setupRecyclerView()
        observeState()
        return binding.root
    }

    private fun setupRecyclerView(){
        rvPokemon = binding.mrvPokemonList
        rvPokemon.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun setupAppBar(){
        requireActivity().window.statusBarColor = resources.getColor(R.color.white_100_percent_opacity)
        (activity as MainActivity).supportActionBar.let {
            it?.setBackgroundDrawable(resources.getDrawable(R.color.white_100_percent_opacity, resources.newTheme()))
        }
    }

    private fun observeState(){
        lifecycleScope.launch {
            viewModel.state.collect{
                when(it){
                    is HomeViewState.Loading -> {
                        if(it.data)
                            binding.mpgLoading.visibility = View.VISIBLE
                         else
                            binding.mpgLoading.visibility = View.GONE
                    }
                    is HomeViewState.Success -> {
                        it.data.let { pokemon -> mAdapter.setData(pokemon) }
                        binding.mpgLoading.visibility = View.GONE
                    }
                    is HomeViewState.Error -> {
                        binding.mpgLoading.visibility = View.GONE
                        Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_fragment_menu, menu)
        val menuSearch = menu.findItem(R.id.menu_search)
        menuSearch.icon.setTint(resources.getColor(R.color.white))
        super.onCreateOptionsMenu(menu, inflater)
    }
}