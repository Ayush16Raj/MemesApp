package com.example.ayushjumpingmindsassignment.repository


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ayushjumpingmindsassignment.InternetCheck
import com.example.ayushjumpingmindsassignment.api.ApiInterface
import com.example.ayushjumpingmindsassignment.model.Data
import com.example.ayushjumpingmindsassignment.model.Meme
import com.example.ayushjumpingmindsassignment.model.memes
import com.example.ayushjumpingmindsassignment.room.MemeDatabase


class MemeRepository(
    private val apiInterface: ApiInterface,
     val memeDatabase: MemeDatabase,
    private val applicationContext: Context
) {

    private val memesLiveData = MutableLiveData<memes>()


    val meme : LiveData<memes>
                get() = memesLiveData

    suspend fun getMemes(){
        if(InternetCheck.isInternetAvailable(applicationContext)){  //Checking for connected to internet or not
            val result = apiInterface.getMemes()
            if (result.body()!=null){

                memeDatabase.memeDao().insertMeme(result.body()!!.data.memes as ArrayList<Meme>)
                memesLiveData.postValue(result.body())
            }
        }else{                   //if not connected to internet showing data from room


            val memes = memeDatabase.memeDao().getMeme()
            val memeList = memes(Data(memes),true)
            memesLiveData.postValue(memeList)



        }

    }

    fun getFavorite(): LiveData<List<Meme>> {
        return memeDatabase.memeDao().getFavoriteMemes()
    }

    // Function to toggle the favorite status of a meme
     fun toggleFavoriteMeme(meme: Meme) {
        meme.isFavorite = !meme.isFavorite
        memeDatabase.memeDao().setMemeAsFavorite(meme.id, meme.isFavorite)
    }

}