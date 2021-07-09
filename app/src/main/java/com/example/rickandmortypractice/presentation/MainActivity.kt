package com.example.rickandmortypractice.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortypractice.R
import com.example.rickandmortypractice.data.api.RickAndMortyAPIService
import com.example.rickandmortypractice.databinding.ActivityMainBinding
import com.example.rickandmortypractice.presentation.adapter.CharacterAdapter
import com.example.rickandmortypractice.presentation.viewmodel.CharacterViewModel
import com.example.rickandmortypractice.presentation.viewmodel.CharacterViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var factory: CharacterViewModelFactory
    @Inject
    lateinit var characterAdapter: CharacterAdapter

    lateinit var viewModel : CharacterViewModel

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, factory).get(CharacterViewModel::class.java)

    }
}