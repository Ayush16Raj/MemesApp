package com.example.ayushjumpingmindsassignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ayushjumpingmindsassignment.R
import com.example.ayushjumpingmindsassignment.model.Meme
import com.example.ayushjumpingmindsassignment.viewmodel.MemeViewModel

class MemeAdapter(val context: Context, var list: List<Meme>, val listener: MyClickListener,
                  private val viewModel: MemeViewModel
) :
    RecyclerView.Adapter<MemeAdapter.ViewHolder>() {




   inner  class ViewHolder(view: View) : RecyclerView.ViewHolder(view),View.OnClickListener{
        val previewImg = view.findViewById<ImageView>(R.id.previewImg)
        val textName = view.findViewById<TextView>(R.id.textName)
        val imgAddToFav: ImageView = view.findViewById(R.id.imgAddtoFav)



       init {
           view.setOnClickListener(this)
           imgAddToFav.setOnClickListener {
               onFavoriteIconClick(adapterPosition) }

        }

       override fun onClick(p0: View?) {
          val position = adapterPosition
           if(position!=RecyclerView.NO_POSITION){ //To check valid position
               listener.onClick(position)


           }
       }


   }

    private fun onFavoriteIconClick(position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            val meme = list[position]
            // Call toggleFavoriteMeme to change the favorite status of the clicked meme
            viewModel.toggleFavoriteMeme(meme)

            notifyItemChanged(position)
        }
    }

    fun setFilteredList(list: List<Meme>){
        this.list = list
        notifyDataSetChanged()
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


        // Set the favorite icon based on the "isFavorite" property(changing color when added)
        holder.imgAddToFav.setImageResource(if (list[position].isFavorite) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24)



    }
    interface MyClickListener{
        fun onClick(position: Int)
    }


}