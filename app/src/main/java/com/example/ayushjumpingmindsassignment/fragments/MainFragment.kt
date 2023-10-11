package com.example.ayushjumpingmindsassignment.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ayushjumpingmindsassignment.HelpingClass
import com.example.ayushjumpingmindsassignment.InternetCheck
import com.example.ayushjumpingmindsassignment.MemeAdapter
import com.example.ayushjumpingmindsassignment.R
import com.example.ayushjumpingmindsassignment.model.Meme
import com.example.ayushjumpingmindsassignment.viewmodel.MemeViewModel
import com.example.ayushjumpingmindsassignment.viewmodel.MemesViewModelFactory


class MainFragment : Fragment(),MemeAdapter.MyClickListener{


    private lateinit var memeViewModel: MemeViewModel
    private lateinit var memeAdapter: MemeAdapter
    var memeList = ArrayList<Meme>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
  val layoutView =  inflater.inflate(R.layout.fragment_main, container, false)

        var recyclerView = layoutView.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(context)

        val memeRepository = (activity?.application as HelpingClass).memeRepository

        memeViewModel = ViewModelProvider(this,
            MemesViewModelFactory(memeRepository)
        )[MemeViewModel::class.java]


        memeViewModel.meme.observe(requireActivity(), Observer {
            memeList = it.data.memes as ArrayList<Meme>    // Adding fetched data to list

            memeAdapter = MemeAdapter(requireContext(), memeList,this@MainFragment)
            recyclerView.adapter = memeAdapter    //giving memeAdapter to our recyclerView adapter
        })

        if(!InternetCheck.isInternetAvailable(requireActivity().applicationContext)) {
            Toast.makeText(activity,"No network connection!", Toast.LENGTH_LONG).show()
        }




        return layoutView



    }

    override fun onClick(position: Int) {
        val imageUrl = memeList[position].url

        val bundle = Bundle()
        bundle.putString("imageUrl", imageUrl)
//
//        // Navigate to the DetailFragment with the image URL
//        val action = MainFragmentDirections.actionMainFragmentToDetailedFragment(imageUrl)
//        findNavController().navigate(action)

        // Create a new instance of DetailedFragment and set the arguments
        val detailedFragment = DetailedFragment()

        detailedFragment.arguments = bundle

        // Navigate to the DetailedFragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, detailedFragment)
            .addToBackStack(null)
            .commit()
    }


}