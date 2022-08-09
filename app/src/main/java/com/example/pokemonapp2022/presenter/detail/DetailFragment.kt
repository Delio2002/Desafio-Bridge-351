package com.example.pokemonapp2022.presenter.detail

import android.os.Bundle
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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.pokemonapp2022.MainActivity
import com.example.pokemonapp2022.R
import com.example.pokemonapp2022.databinding.FragmentDetailBinding
import com.example.pokemonapp2022.presenter.detail.adapter.AbilitiesAdapter
import com.example.pokemonapp2022.presenter.detail.dataui.PokemonDetailDataUi
import com.example.pokemonapp2022.presenter.detail.viewmodel.DetailViewModel
import com.example.pokemonapp2022.presenter.utils.heightFormat
import com.example.pokemonapp2022.presenter.utils.setTypeBackground
import com.example.pokemonapp2022.presenter.utils.setTypeColor
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailBinding
    private val abilitiesAdapter: AbilitiesAdapter by lazy { AbilitiesAdapter() }
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        setupActionBar()
        setupRecyclerView()
        setupObservers()
        viewModel.fetchPokemon(args.pokemon.name)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.details_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setupView(pokemonDetailDataUi: PokemonDetailDataUi) {
        binding.constraintParent.setTypeColor(pokemonDetailDataUi.types[0].type.name)
        binding.mtvPokemonTitle.text = pokemonDetailDataUi.name.toUpperCase()
        binding.mtvPokemonNumber.text = "#" + pokemonDetailDataUi.id.toString().padStart(3, '0')
        binding.mtvPokemonName.text = pokemonDetailDataUi.name
        binding.mtvPokemonHeight.text = pokemonDetailDataUi.height?.heightFormat()
        binding.mtvPokemonWeight.text = pokemonDetailDataUi.weight.toString()
        binding.mtvPokemonBaseExperience.text = pokemonDetailDataUi.base_experience.toString()
        binding.mivPokemonPhoto.load(args.pokemon.imageUrl)
        changeActionBarColor(pokemonDetailDataUi)
    }

    private fun setupRecyclerView() {
        binding.mrvPokemonAbilities.apply {
            adapter = abilitiesAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun changeActionBarColor(pokemonDetailDataUi: PokemonDetailDataUi) {
        (activity as MainActivity).supportActionBar.let {
            it?.setBackgroundDrawable(resources.getDrawable(R.color.white_100_percent_opacity))
            it?.title = ""
        }
        requireActivity().window.statusBarColor =
            resources.getColor(setTypeBackground(pokemonDetailDataUi.types[0].type.name))
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
                    is DetailViewState.Success -> showSuccessState(it.data)
                    is DetailViewState.Loading -> showLoadingState()
                    is DetailViewState.Error -> showErrorState(it.error)
                    is DetailViewState.Initial -> showInitialState()
                }
            }
        }
    }

    private fun showInitialState() {
        binding.apply {
            constraintParent.isVisible = false
        }
    }

    private fun showSuccessState(pokemonDetailDataUi: PokemonDetailDataUi) {
        setupView(pokemonDetailDataUi)
        abilitiesAdapter.setData(pokemonDetailDataUi.abilities)
        binding.apply {
            constraintParent.isVisible = true
            animateUi()
            (activity as AppCompatActivity).supportActionBar?.show()
        }
    }

    private fun showLoadingState() {
        showInitialState()
    }

    private fun showErrorState(message: String) {
        binding.apply {
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
            (activity as AppCompatActivity).supportActionBar?.hide()
        }
    }

    private fun animateUi() {

        binding.constraintParent.alpha = 0f
        binding.materialCardView.translationY = 80f
        binding.mivPokemonPhoto.scaleX = 0.01f
        binding.mivPokemonPhoto.scaleY = 0.01f

        binding.mtvPokemonTitle.translationY = 10f
        binding.mtvPokemonNumber.translationY = 10f

        Thread {

            binding.constraintParent.animate().apply {
                alpha(1f)
            }

            Thread.sleep(90)

            binding.materialCardView.animate().apply {
                translationY(0f)
            }

            binding.mtvPokemonTitle.animate().apply {
                translationY(0f)
            }

            binding.mtvPokemonNumber.animate().apply {
                translationY(0f)
            }

            Thread.sleep(100)

            binding.mivPokemonPhoto.animate().apply {
                scaleX(1.001f)
                scaleY(1.001f)
            }
        }.start()


    }

}