package com.example.rickandmortypractice.domain.usecase

import com.example.rickandmortypractice.domain.repository.CharacterRepository

class GetSearchedCharacterUseCase(
    private val characterRepository: CharacterRepository
){

    fun execute(query : String? = null) =
        characterRepository.getResultStream(query)


}