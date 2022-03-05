package com.example.dictionaryapp.data.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionaryapp.data.MainRepo
import com.example.dictionaryapp.remote.ModelClass.Meaning
import com.example.dictionaryapp.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val mainRepo: MainRepo) : ViewModel() {

    private val _meaning: MutableLiveData<Status<Meaning>> = MutableLiveData()
    val meaning: LiveData<Status<Meaning>> = _meaning

    fun getMeaning(word: String) {
        viewModelScope.launch {
            mainRepo.getMeaning(word).onStart {
                _meaning.value = Status.Loading()
            }.map { resource->
                Status.fromResource(resource)
            }.collect {
                _meaning.value = it
            }
        }
    }

}