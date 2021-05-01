package com.example.diploma_2020.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import com.example.diploma_2020.R

class NavigationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_navigation, container, false)
        val restaurants = root.findViewById<ConstraintLayout>(R.id.restaurant_layout)
        val monuments = root.findViewById<ConstraintLayout>(R.id.monuments_layout)
        val traditional = root.findViewById<ConstraintLayout>(R.id.classical_layout)
        val studyBars = root.findViewById<ConstraintLayout>(R.id.bars_layout)

        restaurants.setOnClickListener {
            findNavController().navigate(R.id.action_navigationFragment_to_nav_home)
        }

        monuments.setOnClickListener {
            findNavController().navigate(R.id.action_navigationFragment_to_nav_gallery)
        }

        traditional.setOnClickListener {
            findNavController().navigate(R.id.action_navigationFragment_to_nav_slideshow)
        }

        studyBars.setOnClickListener {
            findNavController().navigate(R.id.action_navigationFragment_to_nav_home)
        }

        return root
    }
}