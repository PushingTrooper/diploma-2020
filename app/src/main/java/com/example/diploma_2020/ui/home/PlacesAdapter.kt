package com.example.diploma_2020.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.diploma_2020.R
import com.example.diploma_2020.data.Place
import com.example.diploma_2020.helpers.BASE_URL
import com.example.diploma_2020.helpers.OnPlaceClickListener

class PlacesAdapter(
    private val places: List<Place>,
    private val context: Context,
    private val placeClickListener: OnPlaceClickListener
) :
    RecyclerView.Adapter<PlacesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)
        val type: TextView = view.findViewById(R.id.type)
        val name: TextView = view.findViewById(R.id.name)
        val starts: RatingBar = view.findViewById(R.id.reviews)

        val layout: ConstraintLayout = view.findViewById(R.id.layout)
    }

    override fun getItemCount(): Int = places.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.places_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load("$BASE_URL${places[position].imageUrl}")
            .into(holder.image)

        holder.name.text = places[position].name
        holder.type.text = places[position].type
        holder.starts.rating = (places[position].rating.toDouble()/10).toFloat()

        holder.layout.setOnClickListener {
            placeClickListener.onPlaceClickListener(places[position])
        }
    }
}