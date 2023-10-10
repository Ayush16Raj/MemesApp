package com.example.ayushjumpingmindsassignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ayushjumpingmindsassignment.repository.MemeRepository

class MemesViewModelFactory(private val memeRepository: MemeRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MemeViewModel(memeRepository) as T
    }

}