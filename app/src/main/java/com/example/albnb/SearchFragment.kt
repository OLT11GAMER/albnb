package com.example.albnb

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*

class SearchFragment : Fragment() {

    private lateinit var adapter: CityAdapter
    private val cityList = mutableListOf<City>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        // RecyclerView and SearchView setup
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Setup RecyclerView adapter with click listener
        adapter = CityAdapter(cityList) { selectedCity ->
            Log.d("SearchFragment", "City clicked: ${selectedCity.name}")

            // Navigate to City Details Activity
            val intent = Intent(requireContext(), CityDetailsActivity::class.java).apply {
                putExtra("cityTitle", selectedCity.name)
                putExtra("cityDescription", selectedCity.description)
                putExtra("cityImage", selectedCity.imageUrl)
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        // Fetch city data from Firebase
        fetchCities()

        return view
    }

    private fun fetchCities() {
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("cities") // Reference to the 'cities' node

        Log.d("FirebaseDebug", "Fetching cities data...") // Debug log

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("FirebaseDebug", "DataSnapshot received: ${snapshot.value}") // Log raw data
                cityList.clear()
                for (citySnapshot in snapshot.children) {
                    // Match the database keys: title, description, image
                    val title = citySnapshot.child("title").getValue(String::class.java)
                    val description = citySnapshot.child("description").getValue(String::class.java)
                    val imageUrl = citySnapshot.child("image").getValue(String::class.java)

                    if (title != null && description != null && imageUrl != null) {
                        Log.d("FirebaseDebug", "City loaded: $title") // Log each city
                        cityList.add(City(title, description, imageUrl))
                    } else {
                        Log.e("FirebaseDebug", "Invalid city data: ${citySnapshot.value}")
                    }
                }
                adapter.updateData(cityList) // Update RecyclerView
                Log.d("FirebaseDebug", "Total cities loaded: ${cityList.size}")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseDebug", "Database error: ${error.message}")
            }
        })
    }

}
