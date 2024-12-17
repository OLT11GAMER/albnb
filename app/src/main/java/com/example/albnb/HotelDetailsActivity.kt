package com.example.albnb

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class HotelDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_details)


        val hotelImage = findViewById<ImageView>(R.id.hotelImage)
        val hotelName = findViewById<TextView>(R.id.hotelName)
        val hotelDescription = findViewById<TextView>(R.id.hotelDescription)
        val hotelPrice = findViewById<TextView>(R.id.hotelPrice)
        val bookButton = findViewById<Button>(R.id.bookButton)


        val name = intent.getStringExtra("hotelName")
        val description = intent.getStringExtra("hotelDescription")
        val price = intent.getIntExtra("hotelPrice", 0)
        val imageUrl = intent.getStringExtra("hotelImage")


        Glide.with(this).load(imageUrl).into(hotelImage)
        hotelName.text = name
        hotelDescription.text = description
        hotelPrice.text = "Price: $price USD per night"

        bookButton.setOnClickListener {
            val fragment = BookingFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.BookHotel, fragment)
                .addToBackStack(null)
                .commit()

        }
    }
}
