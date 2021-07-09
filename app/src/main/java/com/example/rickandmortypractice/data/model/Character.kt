package com.example.rickandmortypractice.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Character(

    @SerializedName("id")
    val id : Long?,

    @SerializedName("name")
    val name : String,

    @SerializedName("species")
    val species : String,

    @SerializedName("url")
    val url : String,

    @SerializedName("image")
    val image : String,

    @SerializedName("status")
    val status : String,

    @SerializedName("origin")
    val origin : Origin?


) : Serializable
