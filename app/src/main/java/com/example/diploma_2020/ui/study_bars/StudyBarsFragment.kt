package com.example.diploma_2020.ui.study_bars

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.diploma_2020.R
import com.example.diploma_2020.data.Place
import com.example.diploma_2020.helpers.OnPlaceClickListener
import com.example.diploma_2020.ui.traditional.TraditionalAdapter
import com.example.diploma_2020.ui.traditional.TraditionalFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class StudyBarsFragment : Fragment(), OnPlaceClickListener {

    private val viewModel: StudyBarsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.study_bars_fragment, container, false)

        val recycler: RecyclerView = root.findViewById(R.id.recycler)
        val searchButton: ImageView = root.findViewById(R.id.search_button)
        val searchEditText: EditText = root.findViewById(R.id.search_edit_text)


        viewModel.getPlaces()
        viewModel.places.observe(viewLifecycleOwner, Observer {
            val placesAdapter = TraditionalAdapter(
                it,
                requireContext(),
                this
            )

            recycler.adapter = placesAdapter

            searchButton.setOnClickListener {
                placesAdapter.searchPlaces(searchEditText.text.toString())
            }
        })

        return root
    }

    override fun onPlaceClickListener(place: Place) {
        val directions = StudyBarsFragmentDirections.actionStudyBarsFragmentToTraditionalDetailsFragment(place)
        findNavController().navigate(directions)
    }
}