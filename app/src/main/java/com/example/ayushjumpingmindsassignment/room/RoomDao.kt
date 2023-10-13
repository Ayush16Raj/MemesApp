package com.example.ayushjumpingmindsassignment.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ayushjumpingmindsassignment.model.Meme

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertMeme(meme:List<Meme>)

    @Query("SELECT * FROM meme")
     fun getMeme() : List<Meme>

    @Query("SELECT * FROM meme WHERE isFavorite = 1")
    fun getFavoriteMemes(): LiveData<List<Meme>>

    @Query("UPDATE meme SET isFavorite = :isFavorite WHERE id = :memeId")
    fun setMemeAsFavorite(memeId: String, isFavorite: Boolean)
}