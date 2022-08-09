package com.example.pokemonapp2022.presenter.home

import android.os.Bundle
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp2022.MainActivity
import com.example.pokemonapp2022.R
import com.example.pokemonapp2022.databinding.FragmentHomeBinding
import com.example.pokemonapp2022.presenter.home.adapter.PokemonAdapter
import com.example.pokemonapp2022.presenter.home.dataui.PokemonDataUi
import com.example.pokemonapp2022.presenter.home.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {


    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    private val pokemonAdapter: PokemonAdapter by lazy { PokemonAdapter() }
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupActionBar()
        setupRecyclerView()
        setupObservers()
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.mrvPokemonList.apply {
            adapter = pokemonAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun setupActionBar() {
        requireActivity().window.statusBarColor =
            resources.getColor(R.color.white_100_percent_opacity)
        (activity as MainActivity).supportActionBar.let {
            it?.setBackgroundDrawable(
                resources.getDrawable(
                    R.color.white_100_percent_opacity,
                    resources.newTheme()
                )
            )
        }
    }

    private fun setupObservers() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect {
                when (it) {
                    is HomeViewState.Success -> showSuccessState(it.data)
                    is HomeViewState.Loading -> showLoadingState()
                    is HomeViewState.Error -> showErrorState(it.error)
                    is HomeViewState.Initial -> showInitialState()
                }
            }
        }
    }

    private fun showInitialState() {
        binding.apply {
            mrvPokemonList.isVisible = false
            mpgLoading.isVisible = false
            mtvTitle.isVisible = false
        }
    }

    private fun showSuccessState(productsList: List<PokemonDataUi>) {
        pokemonAdapter.setData(productsList)
        binding.apply {
            mrvPokemonList.isVisible = true
            mtvTitle.isVisible = mrvPokemonList.isVisible
            mpgLoading.isVisible = false
            (activity as AppCompatActivity).supportActionBar?.show()
        }
    }

    private fun showLoadingState() {
        binding.apply {
            mrvPokemonList.isVisible = false
            mtvTitle.isVisible = false
            mpgLoading.isVisible = true
        }
    }

    private fun showErrorState(message: String) {
        binding.apply {
            mrvPokemonList.isVisible = false
            mpgLoading.isVisible = false
            mtvTitle.isVisible = false
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
            (activity as AppCompatActivity).supportActionBar?.hide()
        }
    }
}
