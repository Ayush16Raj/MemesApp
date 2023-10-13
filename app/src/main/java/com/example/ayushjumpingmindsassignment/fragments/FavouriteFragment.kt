package com.example.ayushjumpingmindsassignment.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ayushjumpingmindsassignment.HelpingClass
import com.example.ayushjumpingmindsassignment.R
import com.example.ayushjumpingmindsassignment.adapter.MemeAdapter
import com.example.ayushjumpingmindsassignment.model.Meme
import com.example.ayushjumpingmindsassignment.viewmodel.MemeViewModel
import com.example.ayushjumpingmindsassignment.viewmodel.MemesViewModelFactory
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch



@OptIn(DelicateCoroutinesApi::class)
class FavouriteFragment : Fragment() {

    private lateinit var memeAdapter: MemeAdapter
    private var favoriteMemesList = ArrayList<Meme>()
     lateinit var viewModel: MemeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favourite, container, false)

        // Initialize the RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.favRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val memeRepository = (activity?.application as HelpingClass).memeRepository

        viewModel = ViewModelProvider(this,
            MemesViewModelFactory(memeRepository)
        )[MemeViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch  {
            memeRepository.getFavorite().observe(requireActivity(), Observer {

                favoriteMemesList.clear()
                favoriteMemesList.addAll(it)


                memeAdapter = MemeAdapter(
                    requireContext(),
                    favoriteMemesList,
                    object : MemeAdapter.MyClickListener {
                        override fun onClick(position: Int) {
                           Toast.makeText(context,"Go to feed Screen",Toast.LENGTH_SHORT).show()
                        }

                    },
                    viewModel
                )
                recyclerView.adapter =
                    memeAdapter    //giving memeAdapter to our recyclerView adapter
            })
        }


        return view
    }


}





