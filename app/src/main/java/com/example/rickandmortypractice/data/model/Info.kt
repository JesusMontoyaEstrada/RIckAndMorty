package com.example.rickandmortypractice.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Info(

    @SerializedName("count")
    val count : Int,

    @SerializedName("pages")
    val pages : Int,

    @SerializedName("next")
    val next : String,

    @SerializedName("prev")
    val prev : String,

) : Serializable
