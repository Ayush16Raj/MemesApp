package com.example.ayushjumpingmindsassignment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.ayushjumpingmindsassignment.R


class DetailedFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detailed, container, false)
        val imageView = view.findViewById<ImageView>(R.id.detailedImage)

        val imageUrl = arguments?.getString("imageUrl")
        if (imageUrl != null) {
            Glide.with(requireContext())
                .load(imageUrl)
                .into(imageView)
        }

        return view

    }


}