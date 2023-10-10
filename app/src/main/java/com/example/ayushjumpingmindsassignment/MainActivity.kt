package com.example.ayushjumpingmindsassignment


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ayushjumpingmindsassignment.api.ApiInterface
import com.example.ayushjumpingmindsassignment.api.ApiUtilities
import com.example.ayushjumpingmindsassignment.model.Meme
import com.example.ayushjumpingmindsassignment.model.memes
import com.example.ayushjumpingmindsassignment.repository.MemeRepository
import com.example.ayushjumpingmindsassignment.viewmodel.MemeViewModel
import com.example.ayushjumpingmindsassignment.viewmodel.MemesViewModelFactory
import retrofit2.http.Tag


class MainActivity : AppCompatActivity() {

    private lateinit var memeViewModel: MemeViewModel
    private lateinit var memeAdapter: MemeAdapter
    var memeList = ArrayList<Meme>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

  val memeRepository = (application as HelpingClass).memeRepository

        memeViewModel = ViewModelProvider(this,MemesViewModelFactory(memeRepository))[MemeViewModel::class.java]


        memeViewModel.meme.observe(this, Observer {
            memeList = it.data.memes as ArrayList<Meme>    // Adding fetched data to list

            memeAdapter = MemeAdapter(this,memeList)
            recyclerView.adapter = memeAdapter    //giving memeAdapter to our recyclerView adapter
      })

   }
}


