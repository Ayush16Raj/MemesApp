package com.example.ayushjumpingmindsassignment

import android.app.Application
import com.example.ayushjumpingmindsassignment.api.ApiInterface
import com.example.ayushjumpingmindsassignment.api.ApiUtilities
import com.example.ayushjumpingmindsassignment.repository.MemeRepository
import com.example.ayushjumpingmindsassignment.room.MemeDatabase

class HelpingClass : Application() {  //This class is to help MainActivity for more clean code
                                      //If more such activities require these data then no need to write repeated code

    lateinit var memeRepository: MemeRepository

    override fun onCreate() {
        super.onCreate()


        val apiInterface = ApiUtilities.getInstance().create(ApiInterface::class.java)

        val database = MemeDatabase.getDatabase(applicationContext)

         memeRepository = MemeRepository(apiInterface,database,applicationContext)
    }
}