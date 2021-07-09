package com.example.rickandmortypractice.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmortypractice.data.model.Character
import com.example.rickandmortypractice.databinding.CharacterViewItemBinding

class CharacterAdapter : PagingDataAdapter<Character, CharacterViewHolder>(CHARACTER_COMPARATOR){

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val characterItem = getItem(position)
        if(characterItem != null){
            holder.bind(characterItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.create(parent)
    }

    companion object {
        private val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem == newItem
        }

        var onItemClickListener : ((Character)-> Unit) ? = null
    }


    fun setOnClickListener(listener : (Character) -> Unit){
        onItemClickListener = listener
    }
}