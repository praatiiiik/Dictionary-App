package com.example.dictionaryapp.data.repository

import android.util.Log
import com.example.dictionaryapp.utils.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class NetworkBoundRepo<REQUEST> {

    /**
     * Gives Response
     */
    abstract suspend fun getMeaning():Response<REQUEST>

    fun asFlow() = flow<Resource<REQUEST>>{
        val apiResponse = getMeaning()

        val data = apiResponse.body()
        Log.d("dict","repo $data")

        if(apiResponse.isSuccessful && data!=null){
            Log.d("dict","success")
            emit(
                Resource.Success(data)
            )
        }else{
            Log.d("dict","error")
            emit(
                Resource.Failed("Can't Fetch Meaning")
            )
        }
    }.catch { e ->
        e.printStackTrace()
        emit(
            Resource.Failed("Network Error!")
        )
    }


}