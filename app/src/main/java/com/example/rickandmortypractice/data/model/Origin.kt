package com.example.rickandmortypractice.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Origin(

    @SerializedName("name")
    val origin : String? = ""

) : Serializable
