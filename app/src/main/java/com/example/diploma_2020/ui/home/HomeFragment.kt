package com.example.diploma_2020.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.diploma_2020.R
import com.example.diploma_2020.data.Place
import com.example.diploma_2020.helpers.OnPlaceClickListener
import org.koin.android.ext.android.inject

class HomeFragment : Fragment(), OnPlaceClickListener {

    private val homeViewModel: HomeViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val recycler: RecyclerView = root.findViewById(R.id.recycler)
        homeViewModel.getPlaces()
        homeViewModel.places.observe(viewLifecycleOwner, Observer {
            recycler.adapter = PlacesAdapter(
                it,
                requireContext(),
                this
            )
        })

        return root
    }

    override fun onPlaceClickListener(place: Place) {
        val directions = HomeFragmentDirections.actionNavHomeToPlaceDetailsFragment(place)
        findNavController().navigate(directions)
    }
}