package com.example.rickandmortypractice.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.rickandmortypractice.databinding.ActivityMainBinding
import com.example.rickandmortypractice.presentation.adapter.CharacterAdapter
import com.example.rickandmortypractice.presentation.viewmodel.CharacterViewModels
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var characterAdapter: CharacterAdapter

    private lateinit var binding : ActivityMainBinding

    val characterViewModels : CharacterViewModels by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}