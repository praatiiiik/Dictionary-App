package com.example.dictionaryapp.utils

import java.lang.Error

sealed class Status<T>{

    data class Success<T>(val data : T): Status<T>()
    data class Error<T>(val msg : String): Status<T>()
    class Loading<T>() : Status<T>()

    companion object{

        fun <T> fromResource(resource: Resource<T>):Status<T>{
            return when(resource){
                is Resource.Success -> Success(resource.data)
                is Resource.Failed -> Error(resource.message)
            }
        }

    }

}
