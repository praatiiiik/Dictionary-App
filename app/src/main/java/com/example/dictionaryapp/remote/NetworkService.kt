package com.example.dictionaryapp.remote

import com.example.dictionaryapp.remote.ModelClass.Meaning
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {

    /**
     * Fetch data from api
     */
    @GET("api/v2/entries/en_US/"+"{word}")
    suspend fun getMeaning(
        @Path("word") word: String?
    ): Response<Meaning>

}
