package com.app.farm.mobiledevchallenge_edmer.DataModel




 data class LiveCurrency(
    val success: Boolean,
    val terms: String,
    val timestamp: Int,
    val source: String,
    val quotes: List<Quote>
)