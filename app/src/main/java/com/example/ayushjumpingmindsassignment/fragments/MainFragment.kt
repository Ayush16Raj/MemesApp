package com.example.ayushjumpingmindsassignment.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ayushjumpingmindsassignment.HelpingClass
import com.example.ayushjumpingmindsassignment.InternetCheck
import com.example.ayushjumpingmindsassignment.adapter.MemeAdapter
import com.example.ayushjumpingmindsassignment.R
import com.example.ayushjumpingmindsassignment.model.Meme
import com.example.ayushjumpingmindsassignment.viewmodel.MemeViewModel
import com.example.ayushjumpingmindsassignment.viewmodel.MemesViewModelFactory
import java.util.Locale


class MainFragment : Fragment(), MemeAdapter.MyClickListener{


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
        var searchView = layoutView.findViewById<SearchView>(R.id.searchView)

        recyclerView.layoutManager = LinearLayoutManager(context)

        val memeRepository = (activity?.application as HelpingClass).memeRepository

        memeViewModel = ViewModelProvider(this,
            MemesViewModelFactory(memeRepository)
        )[MemeViewModel::class.java]


        memeViewModel.meme.observe(requireActivity(), Observer {
            memeList = it.data.memes as ArrayList<Meme>    // Adding fetched data to list

            memeAdapter = MemeAdapter(requireContext(), memeList,this@MainFragment,memeViewModel)
            recyclerView.adapter = memeAdapter    //giving memeAdapter to our recyclerView adapter
        })

        if(!InternetCheck.isInternetAvailable(requireActivity().applicationContext)) {
            Toast.makeText(activity,"No network connection!", Toast.LENGTH_LONG).show()
        }

        //For searchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                filterList(p0)
                return true
            }

        })




        return layoutView



    }

    private fun filterList(query: String?) {
        if(query!=null){
            val filteredList = ArrayList<Meme>()
            for(i in memeList ){
                if(i.name.toLowerCase(Locale.ROOT).contains(query)){
                    filteredList.add(i)
                }
            }
            if(filteredList.isEmpty()){
                Toast.makeText(activity,"No Data Found",Toast.LENGTH_SHORT).show()
            }else{
                memeAdapter.setFilteredList(filteredList)

            }
        }
    }

    override fun onClick(position: Int) {
        val imageUrl = memeList[position].url

        val bundle = Bundle()
        bundle.putString("imageUrl", imageUrl)

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