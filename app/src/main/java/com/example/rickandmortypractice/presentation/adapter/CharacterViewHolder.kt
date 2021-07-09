package com.example.rickandmortypractice.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortypractice.R
import com.example.rickandmortypractice.data.model.Character
import com.example.rickandmortypractice.databinding.CharacterViewItemBinding

class CharacterViewHolder(val binding : CharacterViewItemBinding): RecyclerView.ViewHolder(binding.root) {

    private var character : Character? = null

    fun bind (character : Character?){
        if(character == null){
            binding.tvCharacter.text = "Cargando..."

        } else {
            showCharacterData(character)
        }
    }

    private fun showCharacterData(character : Character) {
        this.character = character
        binding.tvCharacter.text = character.name

        binding.root.setOnClickListener {
            CharacterAdapter.onItemClickListener?.let {
                it(character)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): CharacterViewHolder {
            val binding = CharacterViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CharacterViewHolder(binding)
        }
    }

}