package com.example.pokemonapp2022.presenter.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.pokemonapp2022.R
import com.example.pokemonapp2022.databinding.FragmentHomeBinding
import com.example.pokemonapp2022.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return binding.root

        //binding.imageView.load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/3.png")
    }


}