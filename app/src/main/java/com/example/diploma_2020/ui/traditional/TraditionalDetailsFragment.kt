package com.example.diploma_2020.ui.traditional

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.diploma_2020.MainActivity
import com.example.diploma_2020.R
import com.example.diploma_2020.data.TimeSchedule
import com.example.diploma_2020.helpers.BASE_URL
import com.example.diploma_2020.ui.ReserveDialog
import com.example.diploma_2020.ui.home.HomeViewModel
import com.example.diploma_2020.ui.home.PlaceDetailsFragmentArgs
import org.koin.android.ext.android.inject


class TraditionalDetailsFragment : Fragment() {
    private val viewModel: HomeViewModel by inject()
    private val args: PlaceDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_traditional_details, container, false)
        val image: ImageView = root.findViewById(R.id.image)
        val typeTextView: TextView = root.findViewById(R.id.type)
        //val name: TextView = root.findViewById(R.id.name)
        val starts: RatingBar = root.findViewById(R.id.reviews)
        val address: TextView = root.findViewById(R.id.address)
        val description: TextView = root.findViewById(R.id.description)
        val reserve: Button = root.findViewById(R.id.reserve)
        //val timeScheduleTextView: TextView = root.findViewById(R.id.time)

        val monday: TextView = root.findViewById(R.id.monday)
        val tuesday: TextView = root.findViewById(R.id.tuesday)
        val wednesday: TextView = root.findViewById(R.id.wednesday)
        val thursday: TextView = root.findViewById(R.id.thursday)
        val friday: TextView = root.findViewById(R.id.friday)
        val saturday: TextView = root.findViewById(R.id.saturday)
        val sunday: TextView = root.findViewById(R.id.sunday)

        val place = args.place

        viewModel.getTimeSchedule(place.id)

        Glide.with(requireContext())
            .load("$BASE_URL/${place.imageUrl}")
            .into(image)

        //name.text = place.name
        (activity as MainActivity).supportActionBar?.title = place.name
        typeTextView.text = place.type
        starts.rating = (place.rating.toDouble()/10).toFloat()
        address.text = place.address
        description.text = place.description

        viewModel.timeSchedule.observe(viewLifecycleOwner, Observer {
            var timeSchedule: String = ""
            for(time in it.timeSchedule) {
                when(Integer.parseInt(time.weekday)) {
                    1 -> monday.text = getTimeString("E hënë", time)
                    2 -> tuesday.text = getTimeString("E martë", time)
                    3 -> wednesday.text = getTimeString("E mërkurë", time)
                    4 -> thursday.text = getTimeString("E enjte", time)
                    5 -> friday.text = getTimeString("E premte", time)
                    6 -> saturday.text = getTimeString("E shtunë", time)
                    7 -> sunday.text = getTimeString("E dielë", time)
                }
            }
        })

        reserve.setOnClickListener {
            val dialog = ReserveDialog(requireContext())
            dialog.show()
            dialog.reserve.setOnClickListener {
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_EMAIL, "test@email.com")
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("test@email.com"))
                    putExtra(Intent.EXTRA_TEXT, "Përshëndetje, dua të rezervoj një tavolinë për ${dialog.number.text.toString()} veta në emrin ${dialog.name.text.toString()} në datën ${dialog.dateText.text}")
                    putExtra(Intent.EXTRA_SUBJECT, "Rezervim")
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(intent, null)
                startActivity(shareIntent)
            }
        }

        return root
    }

    private fun getTimeString(day: String, time: TimeSchedule): String =
        if(time.startHour.isNullOrEmpty() || time.endHour.isNullOrEmpty()){
            "Mbyllur"
        } else {
            "${time.startHour} - ${time.endHour}"
        }

}