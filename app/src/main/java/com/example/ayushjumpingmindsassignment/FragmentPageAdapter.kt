package com.example.ayushjumpingmindsassignment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ayushjumpingmindsassignment.fragments.FavouriteFragment
import com.example.ayushjumpingmindsassignment.fragments.MainFragment

class FragmentPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if(position == 0){
            MainFragment()
        }else  {
            FavouriteFragment()
        }
    }
}