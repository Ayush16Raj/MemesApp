package com.example.ayushjumpingmindsassignment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ayushjumpingmindsassignment.model.Meme

class MemeAdapter(val context: Context, val list: List<Meme>) :
    RecyclerView.Adapter<MemeAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val previewImg = view.findViewById<ImageView>(R.id.previewImg)
        val textName = view.findViewById<TextView>(R.id.textName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.meme_list,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      Glide.with(context).load(list[position].url).into(holder.previewImg)
        holder.textName.text =  list[position].name
    }

}