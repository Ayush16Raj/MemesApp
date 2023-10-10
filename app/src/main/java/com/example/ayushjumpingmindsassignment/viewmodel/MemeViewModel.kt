package com.example.ayushjumpingmindsassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ayushjumpingmindsassignment.model.Meme
import com.example.ayushjumpingmindsassignment.model.memes
import com.example.ayushjumpingmindsassignment.repository.MemeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemeViewModel(private val memeRepository: MemeRepository): ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {  memeRepository.getMemes() }

    }

    val meme : LiveData<memes>
        get() = memeRepository.meme
}