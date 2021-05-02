package com.example.diploma_2020.ui.traditional

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
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.diploma_2020.R
import com.example.diploma_2020.data.Place
import com.example.diploma_2020.helpers.BASE_URL
import com.example.diploma_2020.helpers.OnPlaceClickListener

class TraditionalAdapter(
    private val places: List<Place>,
    private val context: Context,
    private val placeClickListener: OnPlaceClickListener
) :
    RecyclerView.Adapter<TraditionalAdapter.ViewHolder>() {

    private val placesShown: MutableList<Place> = places.toMutableList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)
        val type: TextView = view.findViewById(R.id.type)
        val name: TextView = view.findViewById(R.id.name)
        val starts: RatingBar = view.findViewById(R.id.reviews)

        val layout: ConstraintLayout = view.findViewById(R.id.layout)
    }

    fun searchPlaces(searchText: String) {
        val tempList = mutableListOf<Place>()

        if (searchText.isNotEmpty()) {
            places.forEach { place ->
                if (place.name.toLowerCase().contains(searchText.toLowerCase())) {
                    tempList.add(place)
                }
            }
            placesShown.clear()
            placesShown.addAll(tempList)
        } else {
            placesShown.clear()
            placesShown.addAll(places)
        }

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = placesShown.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.places_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load("$BASE_URL/${placesShown[position].imageUrl}")
            .apply(
                RequestOptions().transform(
                    CenterCrop(),
                    RoundedCorners(50)
                )
            )
            .into(holder.image)

        holder.name.text = placesShown[position].name
        holder.type.text = placesShown[position].type
        holder.starts.rating = (placesShown[position].rating.toDouble() / 10).toFloat()

        holder.layout.setOnClickListener {
            placeClickListener.onPlaceClickListener(placesShown[position])
        }
    }
}