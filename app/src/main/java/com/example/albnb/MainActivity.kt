package com.example.albnb

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Icon
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.compose.runtime.Composable
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.albnb.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import org.w3c.dom.Text
import com.google.android.material.floatingactionbutton.FloatingActionButton



class MainActivity : AppCompatActivity ()  {

    private lateinit var binding: ActivityMainBinding
    private lateinit var collapsedCard: View
    private lateinit var expandedContainer: FrameLayout
    private lateinit var closeButton: FloatingActionButton
    private lateinit var bookingButton: Button

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_search,
                R.id.navigation_deals,
                R.id.navigation_home,
                R.id.navigation_attractions,
                R.id.navigation_travel
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragment_home, HomeFragment())
//            .commit()
//
//        collapsedCard = findViewById(R.id.cardGjilan)
//        expandedContainer = findViewById(R.id.expanded_container)
//        closeButton = findViewById(R.id.close_button)
//        bookingButton = findViewById(R.id.booking_button)
//
//        // Expand card on click
//        collapsedCard.setOnClickListener {
//            expandCard()
//        }

        // Close expanded view
//        closeButton.setOnClickListener { collapseCard() }
//        expandedContainer.setOnClickListener { collapseCard() }
//
//        // Booking button action
//        bookingButton.setOnClickListener {
//            // Show booking content
//            Toast.makeText(this, "Booking page will be implemented.", Toast.LENGTH_SHORT).show()
//          }


    }
//    fun backtoroot(){
//        setContentView(binding.root)
//    }
    private fun expandCard() {
        expandedContainer.visibility = View.VISIBLE
        expandedContainer.alpha = 0f
        expandedContainer.animate()
            .alpha(1f)
            .setDuration(300)
            .setListener(null)
    }

    private fun collapseCard() {
        expandedContainer.animate()
            .alpha(0f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    expandedContainer.visibility = View.GONE
                }
            })
    }



}