package com.example.albnb

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.*

class CityDetailsActivity : AppCompatActivity() {

    private lateinit var hotelAdapter: HotelAdapter
    private val hotelList = mutableListOf<Hotel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_details)

        val cityImage = findViewById<ImageView>(R.id.cityImage)
        val cityName = findViewById<TextView>(R.id.cityName)
        val cityDescription = findViewById<TextView>(R.id.cityDescription)

        val hotelRecyclerView = findViewById<RecyclerView>(R.id.hotelRecyclerView)

        val name = intent.getStringExtra("cityTitle")
        val description = intent.getStringExtra("cityDescription")
        val imageUrl = intent.getStringExtra("cityImage")

        cityName.text =name
        cityDescription.text = description
        Glide.with(this).load(imageUrl).into(cityImage)

        hotelRecyclerView.layoutManager = LinearLayoutManager(this)
        hotelAdapter = HotelAdapter(hotelList) { selectedHotel ->
            val intent = Intent(this, HotelDetailsActivity::class.java).apply {
                putExtra("hotelName", selectedHotel.title)
                putExtra("hotelDescription", selectedHotel.description)
                putExtra("hotelPrice", selectedHotel.price)
                putExtra("hotelImage", selectedHotel.image)
            }
            startActivity(intent)
        }

        hotelRecyclerView.adapter = hotelAdapter


        fetchHotels(name?: "")



    }

    private fun fetchHotels(cityTitle: String) {
        val db = FirebaseDatabase.getInstance().getReference("cities")

        db.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                hotelList.clear()
                Log.d("CityDetailsActivity", "Snapshot exists: ${snapshot.exists()}")
                for (citySnapshot in snapshot.children) {
                    val title = citySnapshot.child("title").getValue(String::class.java)

                    Log.d("CityDetailsActivity", "City name: $title")
                    Log.d("CityDetailsActivity", "City name: $cityTitle")
                    if (title == cityTitle) {
                        Log.d("CityDetailsActivity", "TRTRTRTCity name: $title")

                        val hotelsSnapshot = citySnapshot.child("hotels")
                        for (hotelSnapshot in hotelsSnapshot.children) {
                            val name = hotelSnapshot.child("title").getValue(String::class.java) ?: "No Name"
                            val description = hotelSnapshot.child("description").getValue(String::class.java) ?: "No Description"
                            val price = hotelSnapshot.child("price").getValue(Int::class.java) ?: 0
                            val image = hotelSnapshot.child("image").getValue(String::class.java) ?: ""
                            hotelList.add(Hotel(name, description, price, image))
                        }
                    }
                }
            }


            override fun onCancelled(error: DatabaseError) {
                Log.e("CityDetailsActivity", "Error fetching hotels: ${error.message}")
            }
        })
    }

}
