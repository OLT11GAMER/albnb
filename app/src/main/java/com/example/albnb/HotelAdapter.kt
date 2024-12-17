package com.example.albnb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HotelAdapter(
    private var hotelList: List<Hotel>,
    private val onHotelClick: (Hotel) -> Unit
) : RecyclerView.Adapter<HotelAdapter.HotelViewHolder>() {

    private var filteredList = hotelList

    class HotelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val hotelImage: ImageView = view.findViewById(R.id.hotelImage)
        val hotelName: TextView = view.findViewById(R.id.hotelName)
        val hotelDescription: TextView = view.findViewById(R.id.hotelDescription)
        val hotelPrice: TextView = view.findViewById(R.id.hotelPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hotel_item, parent, false)
        return HotelViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val hotel = filteredList[position]
        holder.hotelName.text = hotel.title
        holder.hotelDescription.text = hotel.description
        holder.hotelPrice.text = "Price: ${hotel.price} USD"
        Glide.with(holder.hotelImage.context).load(hotel.image).into(holder.hotelImage)

        holder.itemView.setOnClickListener {
            onHotelClick(hotel) // Handle click events
        }
    }

    override fun getItemCount(): Int = filteredList.size

    fun updateData(newList: List<Hotel>) {
        filteredList = newList
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            hotelList
        } else {
            hotelList.filter { it.title.contains(query, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }
}
