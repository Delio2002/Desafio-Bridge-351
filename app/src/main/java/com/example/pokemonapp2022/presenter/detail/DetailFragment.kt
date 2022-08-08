package com.example.pokemonapp2022.presenter.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pokemonapp2022.MainActivity
import com.example.pokemonapp2022.R
import com.example.pokemonapp2022.databinding.FragmentDetailBinding
import com.example.pokemonapp2022.presenter.detail.adapter.AbilitiesAdapter
import com.example.pokemonapp2022.presenter.detail.dataui.PokemonDetailDataUi
import com.example.pokemonapp2022.presenter.detail.viewmodel.DetailViewModel
import com.example.pokemonapp2022.presenter.home.HomeViewState
import com.example.pokemonapp2022.presenter.home.viewmodel.HomeViewModel
import com.example.pokemonapp2022.presenter.utils.heightFormat
import com.example.pokemonapp2022.presenter.utils.setTypeBackground
import com.example.pokemonapp2022.presenter.utils.setTypeColor
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailBinding
    private lateinit var rvAbilities: RecyclerView
    private val mAdapter: AbilitiesAdapter by lazy { AbilitiesAdapter() }
    private lateinit var rotateAnimation: Animation
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        observeState()
        viewModel.fetchPokemonList(args.pokemon.name)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.details_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun ImageView.rotateAnimation() {
        rotateAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate)
        this.startAnimation(rotateAnimation)
    }

    private fun setupViews(pokemonDetailDataUi: PokemonDetailDataUi) {
        binding.constraintParent.setTypeColor(pokemonDetailDataUi.types[0].type.name)
        binding.mtvPokemonTitle.text = pokemonDetailDataUi.name.toUpperCase()
        binding.mtvPokemonNumber.text = "#" + pokemonDetailDataUi.id.toString().padStart(3, '0')
        binding.mtvPokemonName.text = pokemonDetailDataUi.name
        binding.mtvPokemonHeight.text = pokemonDetailDataUi.height?.heightFormat()
        binding.mtvPokemonWeight.text = pokemonDetailDataUi.weight.toString()
        binding.mtvPokemonBaseExperience.text = pokemonDetailDataUi.base_experience.toString()
        binding.mivPokeball.rotateAnimation()
        binding.mivPokemonPhoto.load(args.pokemon.imageUrl)
        (activity as MainActivity).supportActionBar.let {
            it?.setBackgroundDrawable(resources.getDrawable(R.color.white_100_percent_opacity))
            it?.title = ""
        }
        requireActivity().window.statusBarColor =
           resources.getColor(setTypeBackground(pokemonDetailDataUi.types[0].type.name))

    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is DetailViewState.Initial -> {
                        binding.apply {
                            mivPokemonPhoto.visibility = View.GONE
                            mivPokeball.visibility = View.GONE
                            mtvPokemonBaseExperience.visibility = View.GONE
                            mtvPokemonWeight.visibility = View.GONE
                            mtvPokemonHeight.visibility = View.GONE
                            mtvPokemonName.visibility = View.GONE
                            mtvPokemonNumber.visibility = View.GONE
                            mtvPokemonTitle.visibility = View.GONE
                            materialCardView.visibility = View.GONE
                            mrvPokemonAbilities.visibility = View.GONE
                            mtvAbilities.visibility = View.GONE
                            mivPokemonPhoto.visibility = View.GONE
                        }
                    }
                    is DetailViewState.Loading -> {
                        binding.apply {
                            mivPokemonPhoto.visibility = View.GONE
                            mivPokeball.visibility = View.GONE
                            mtvPokemonBaseExperience.visibility = View.GONE
                            mtvPokemonWeight.visibility = View.GONE
                            mtvPokemonHeight.visibility = View.GONE
                            mtvPokemonName.visibility = View.GONE
                            mtvPokemonNumber.visibility = View.GONE
                            mtvPokemonTitle.visibility = View.GONE
                            materialCardView.visibility = View.GONE
                            mrvPokemonAbilities.visibility = View.GONE
                            mtvAbilities.visibility = View.GONE
                            mivPokemonPhoto.visibility = View.GONE
                        }
                    }
                    is DetailViewState.Success -> {
                        setupViews(it.data)
                        mAdapter.setData(it.data.abilities)
                        setupRecyclerView()
                        binding.mivPokemonPhoto.visibility = View.VISIBLE
                        binding.mivPokeball.visibility = View.VISIBLE
                        binding.mtvPokemonBaseExperience.visibility = View.VISIBLE
                        binding.mtvPokemonWeight.visibility = View.VISIBLE
                        binding.mtvPokemonHeight.visibility = View.VISIBLE
                        binding.mtvPokemonName.visibility = View.VISIBLE
                        binding.mtvPokemonNumber.visibility = View.VISIBLE
                        binding.mtvPokemonTitle.visibility = View.VISIBLE
                        binding.materialCardView.visibility = View.VISIBLE
                        binding.mrvPokemonAbilities.visibility = View.VISIBLE
                        binding.mtvAbilities.visibility = View.VISIBLE


                    }
                    is DetailViewState.Error -> {
                        binding.mivPokemonPhoto.visibility = View.GONE
                        binding.mivPokeball.visibility = View.GONE
                        binding.mtvPokemonBaseExperience.visibility = View.GONE
                        binding.mtvPokemonWeight.visibility = View.GONE
                        binding.mtvPokemonHeight.visibility = View.GONE
                        binding.mtvPokemonName.visibility = View.GONE
                        binding.mtvPokemonNumber.visibility = View.GONE
                        binding.mtvPokemonTitle.visibility = View.GONE
                        binding.materialCardView.visibility = View.GONE
                        binding.mrvPokemonAbilities.visibility = View.GONE
                        binding.mtvAbilities.visibility = View.GONE
                        Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

    }

    private fun setupRecyclerView() {
        rvAbilities = binding.mrvPokemonAbilities
        rvAbilities.apply {
            adapter = mAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }


}