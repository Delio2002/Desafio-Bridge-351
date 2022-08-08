package com.example.pokemonapp2022.presenter.utils

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import com.example.pokemonapp2022.R
import com.example.pokemonapp2022.data.dto.response.PokemonResponse
import com.example.pokemonapp2022.presenter.detail.dataui.PokemonDetailDataUi
import com.google.android.material.textview.MaterialTextView


fun String.getPokemonPhotoURL(): String{
    val index = this.split("/".toRegex()).dropLast(1).last()
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/$index.png"
}


fun View.setTypeColor(type: String){
    when(type){
        IndexType.GRASS.typeName ->{
            this.setBackgroundColor(resources.getColor(R.color.grass_color, resources.newTheme()))
        }
        IndexType.BUG.typeName ->{
            this.setBackgroundColor(resources.getColor(R.color.bug_color, resources.newTheme()))
        }
        IndexType.DARK.typeName ->{
            this.setBackgroundColor(resources.getColor(R.color.dark_color, resources.newTheme()))
        }
        IndexType.DRAGON.typeName->{
            this.setBackgroundColor(resources.getColor(R.color.dragon_color, resources.newTheme()))
        }
        IndexType.ELECTRIC.typeName ->{
            this.setBackgroundColor(resources.getColor(R.color.electric_color, resources.newTheme()))
        }
        IndexType.FAIRY.typeName ->{
            this.setBackgroundColor(resources.getColor(R.color.fairy_color, resources.newTheme()))
        }
        IndexType.FIGHTING.typeName ->{
            this.setBackgroundColor(resources.getColor(R.color.fighting_color, resources.newTheme()))
        }
        IndexType.FIRE.typeName ->{
            this.setBackgroundColor(resources.getColor(R.color.fire_color, resources.newTheme()))
        }
        IndexType.NORMAL.typeName ->{
            this.setBackgroundColor(resources.getColor(R.color.normal_color, resources.newTheme()))
        }
        IndexType.WATER.typeName ->{
            this.setBackgroundColor(resources.getColor(R.color.water_color, resources.newTheme()))
        }
        IndexType.GHOST.typeName ->{
            this.setBackgroundColor(resources.getColor(R.color.ghost_color, resources.newTheme()))
        }
        IndexType.ICE.typeName ->{
            this.setBackgroundColor(resources.getColor(R.color.ice_color, resources.newTheme()))
        }
        IndexType.PSYCHIC.typeName ->{
            this.setBackgroundColor(resources.getColor(R.color.psychic_color, resources.newTheme()))
        }
        IndexType.ROCK.typeName ->{
            this.setBackgroundColor(resources.getColor(R.color.rock_color, resources.newTheme()))
        }
        IndexType.STEEL.typeName ->{
            this.setBackgroundColor(resources.getColor(R.color.steel_color, resources.newTheme()))
        }
        IndexType.FLYING.typeName ->{
            this.setBackgroundColor(resources.getColor(R.color.flying_color, resources.newTheme()))
        }
        IndexType.GROUND.typeName ->{
            this.setBackgroundColor(resources.getColor(R.color.ground_color, resources.newTheme()))
        }
        IndexType.POISON.typeName ->{
            this.setBackgroundColor(resources.getColor(R.color.poison_color, resources.newTheme()))
        }
    }
}


fun setTypeBackground(type: String) : Int{
    when(type){
        IndexType.GRASS.typeName ->{
            return R.color.grass_color
        }
        IndexType.BUG.typeName ->{
            return R.color.bug_color
        }
        IndexType.DARK.typeName ->{
            return R.color.dark_color
        }
        IndexType.DRAGON.typeName->{
            return R.color.dragon_color
        }
        IndexType.ELECTRIC.typeName ->{
            return R.color.electric_color
        }
        IndexType.FAIRY.typeName ->{
            return R.color.fairy_color
        }
        IndexType.FIGHTING.typeName ->{
            return R.color.fighting_color
        }
        IndexType.FIRE.typeName ->{
            return R.color.fire_color
        }
        IndexType.NORMAL.typeName ->{
            return R.color.normal_color
        }
        IndexType.WATER.typeName ->{
            return R.color.water_color
        }
        IndexType.GHOST.typeName ->{
            return R.color.ghost_color
        }
        IndexType.ICE.typeName ->{
            return R.color.ice_color
        }
        IndexType.PSYCHIC.typeName ->{
            return R.color.psychic_color
        }
        IndexType.ROCK.typeName ->{
            return R.color.rock_color
        }
        IndexType.STEEL.typeName ->{
            return R.color.steel_color
        }
        IndexType.FLYING.typeName ->{
            return R.color.flying_color
        }
        IndexType.GROUND.typeName ->{
            return R.color.ground_color
        }
        IndexType.POISON.typeName ->{
            return R.color.poison_color
        }else->{
        return R.color.normal_color
    }
    }
}

fun Int.heightFormat() : String{
    val newHeight = this.toString()
    val formatted: String = if (newHeight.length >=2){
        "${newHeight.substring(0, 1)}.${newHeight.substring(1, newHeight.length)}"
    }else{
        "0.${newHeight}"
    }
    return formatted
}





