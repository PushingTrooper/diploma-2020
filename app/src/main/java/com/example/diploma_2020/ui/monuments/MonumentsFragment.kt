package com.example.diploma_2020.ui.monuments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.diploma_2020.R
import org.koin.android.ext.android.inject

class MonumentsFragment : Fragment() {
    private val monumentsViewModel: MonumentsViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        val recycler: RecyclerView = root.findViewById(R.id.recycler)
        monumentsViewModel.getMonuments()
        monumentsViewModel.monuments.observe(viewLifecycleOwner, Observer {
            recycler.adapter = MonumentsAdapter(
                it,
                requireContext()
            )
        })

        return root
    }
}