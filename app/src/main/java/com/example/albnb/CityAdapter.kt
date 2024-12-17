package com.example.albnb

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.albnb.R

class CityAdapter(
    private var cityList: List<City>,
    private val onCityClick: (City) -> Unit
) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cityName: TextView = view.findViewById(R.id.cityName)
        val cityDescription: TextView = view.findViewById(R.id.cityDescription)
        val cityImage: ImageView = view.findViewById(R.id.cityImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_item, parent, false) // Make sure city_item layout exists
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cityList[position]
        holder.cityName.text = city.name
        holder.cityDescription.text = city.description
        Glide.with(holder.cityImage.context).load(city.imageUrl).into(holder.cityImage)

        // Attach the click listener to the root view
        holder.itemView.setOnClickListener {
            onCityClick(city)
        }
    }

    override fun getItemCount(): Int = cityList.size

    fun updateData(newList: List<City>) {
        cityList = newList
        notifyDataSetChanged()
    }
}
