package com.example.ayushjumpingmindsassignment.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtilities {
    val baseUrl = "https://api.imgflip.com/"

    fun getInstance() : Retrofit{
  return Retrofit.Builder().baseUrl(baseUrl)
      .addConverterFactory(GsonConverterFactory.create()).build()
    }

}