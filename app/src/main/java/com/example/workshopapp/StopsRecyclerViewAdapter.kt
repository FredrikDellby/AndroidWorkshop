package com.example.workshopapp

import android.content.Context
import android.location.Location
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.workshopapp.R
import com.example.workshopapp.StopLocation
import kotlin.math.roundToInt


class StopsRecyclerViewAdapter(
        private val context: Context,
        private val listOfStops: List<StopLocation>,
        private val userLocation: Location,
        private val clickListener: (StopLocation) -> Unit
) :
        RecyclerView.Adapter<StopsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder.create(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(context, listOfStops[position], userLocation, clickListener)

    override fun getItemCount(): Int = listOfStops.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val rootView: CardView = view.findViewById(R.id.recycler_view_rootView)
        private val textViewName: TextView = view.findViewById(R.id.recycler_view_textViewName)
        private val textViewDistance: TextView =
                view.findViewById(R.id.recycler_view_textViewDistance)

        fun bind(
                context: Context,
                stop: StopLocation,
                userLocation: Location,
                clickListener: (StopLocation) -> Unit
        ) {

            // Setting the name of the stop
            textViewName.text = stop.name

            // Calculating the distance to the stop
            val stopLocation = Location("")
            stopLocation.latitude = stop.lat.toDouble()
            stopLocation.longitude = stop.lon.toDouble()
            val distanceToStopInMeters = userLocation.distanceTo(stopLocation).roundToInt()

            // Showing the distance to the stop
            textViewDistance.text = context.getString(R.string.meters_away, distanceToStopInMeters)

            // Setting the click listener
            rootView.setOnClickListener{
                clickListener(stop)
            }
        }

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.recycler_view_layout_stop, parent, false)
                return ViewHolder(view)
            }
        }
    }
}