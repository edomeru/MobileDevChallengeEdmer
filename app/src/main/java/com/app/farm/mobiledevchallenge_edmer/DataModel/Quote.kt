package com.app.farm.mobiledevchallenge_edmer.DataModel


import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("Country")
    val country: String,
    @SerializedName("Value")
    val value: Double
)