package com.example.pokemonapp2022.presenter.detail.mapper

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.pokemonapp2022.R

private fun ImageView.rotateAnimation(context: Context) {
    var rotateAnimation: Animation = AnimationUtils.loadAnimation(context, R.anim.rotate)
    this.startAnimation(rotateAnimation)
}