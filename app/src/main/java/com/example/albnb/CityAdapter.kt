package com.example.albnb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.albnb.R

class CityAdapter(private var cityList: List<City>) :
    RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private var filteredList = cityList

    class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cityName: TextView = view.findViewById(R.id.cityName)
        val cityDescription: TextView = view.findViewById(R.id.cityDescription)
        val cityImage: ImageView = view.findViewById(R.id.cityImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_item, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = filteredList[position]
        holder.cityName.text = city.name
        holder.cityDescription.text = city.description
        Glide.with(holder.cityImage.context).load(city.imageUrl).into(holder.cityImage)
    }

    override fun getItemCount(): Int = filteredList.size

    fun updateData(newList: List<City>) {
        filteredList = newList
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            cityList
        } else {
            cityList.filter { it.name.contains(query, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }
}
