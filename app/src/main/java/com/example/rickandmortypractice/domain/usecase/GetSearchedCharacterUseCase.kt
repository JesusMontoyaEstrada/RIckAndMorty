package com.example.rickandmortypractice.domain.usecase

import com.example.rickandmortypractice.domain.repository.CharacterRepository

class GetSearchedCharacterUseCase(
    private val characterRepository: CharacterRepository
){

    fun execute(query : String? = null) =
        characterRepository.getResultStream(query)

//    fun execute(query : String) =
//        characterRepository.getSearchedCharacter(query)

//    fun getResultStream(query : String? = null) = characterRepository.getSearchedCharacter(query)

}