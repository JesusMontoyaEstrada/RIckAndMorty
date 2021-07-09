package com.example.rickandmortypractice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.rickandmortypractice.databinding.FragmentCharacterDetailBinding

class CharacterDetailFragment : Fragment() {

    private lateinit var binding : FragmentCharacterDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCharacterDetailBinding.bind(view)
        val args : CharacterDetailFragmentArgs by navArgs()
        val character = args.character


        Glide.with(binding.ivCharacter.context)
            .load(character.image)
            .into(binding.ivCharacter)

        binding.tvCharacterName.text = character.name
        binding.tvCharacterOrigin.text = character.origin?.origin
        binding.tvCharacterSpecie.text = character.species
        binding.tvCharacterStatus.text = character.status

    }
}