package com.example.diploma_2020.ui.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.diploma_2020.R
import com.example.diploma_2020.data.Monument
import com.example.diploma_2020.helpers.BASE_URL

class MonumentsAdapter(
    private val places: List<Monument>,
    private val context: Context
) :
    RecyclerView.Adapter<MonumentsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)
        val name: TextView = view.findViewById(R.id.name)
        //val starts: RatingBar = view.findViewById(R.id.reviews)
    }

    override fun getItemCount(): Int = places.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.monuments_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load("$BASE_URL${places[position].imageUrl}")
            .into(holder.image)

        holder.name.text = places[position].name
        //holder.starts.rating = (Integer.parseInt(places[position].rating) / 10).toFloat()
    }
}