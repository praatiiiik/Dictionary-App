package com.example.dictionaryapp.data

import com.example.dictionaryapp.data.repository.NetworkBoundRepo
import com.example.dictionaryapp.remote.ModelClass.Meaning
import com.example.dictionaryapp.remote.NetworkService
import com.example.dictionaryapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

interface MainRepo {
    /**
     * Gives Body of response as flow
     */
     fun getMeaning(word : String): Flow<Resource<Meaning>>
}
class DefaultMainRepo @Inject constructor(private val networkService: NetworkService):MainRepo{
    override  fun getMeaning(word: String): Flow<Resource<Meaning>> {
        return object : NetworkBoundRepo<Meaning>(){
            override suspend fun getMeaning(): Response<Meaning> {
                return networkService.getMeaning(word)
            }

        }.asFlow()
    }

}