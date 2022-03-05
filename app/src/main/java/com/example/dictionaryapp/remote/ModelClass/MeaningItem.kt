package com.example.dictionaryapp.remote.ModelClass

data class MeaningItem(
    val license: License?,
    val meanings: List<MeaningX>?,
    val phonetics: List<Any>?,
    val sourceUrls: List<String>?,
    val word: String?
)