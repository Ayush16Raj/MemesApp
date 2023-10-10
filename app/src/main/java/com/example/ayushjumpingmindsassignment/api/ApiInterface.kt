package com.example.ayushjumpingmindsassignment.api


import com.example.ayushjumpingmindsassignment.model.Meme
import com.example.ayushjumpingmindsassignment.model.memes
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("get_memes")
   suspend fun getMemes(): Response<memes>

}