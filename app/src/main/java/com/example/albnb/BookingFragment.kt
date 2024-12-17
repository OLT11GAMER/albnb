package com.example.albnb

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

class BookingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_booking, container, false)

        // Initialize views
        val roomsInput = view.findViewById<EditText>(R.id.input_rooms)
        val peopleInput = view.findViewById<EditText>(R.id.input_people)
        val checkinDateInput = view.findViewById<EditText>(R.id.input_checkin_date)
        val checkoutDateInput = view.findViewById<EditText>(R.id.input_checkout_date)
        val radioGroup = view.findViewById<RadioGroup>(R.id.radio_group_booking_type)
        val submitButton = view.findViewById<Button>(R.id.button_submit_booking)

        // Set up date pickers
        val calendar = Calendar.getInstance()

        checkinDateInput.setOnClickListener {
            showDatePicker(calendar) { date ->
                checkinDateInput.setText(date)
            }
        }

        checkoutDateInput.setOnClickListener {
            showDatePicker(calendar) { date ->
                checkoutDateInput.setText(date)
            }
        }

        // Handle Submit Button Click
        submitButton.setOnClickListener {
            val rooms = roomsInput.text.toString()
            val people = peopleInput.text.toString()
            val checkinDate = checkinDateInput.text.toString()
            val checkoutDate = checkoutDateInput.text.toString()

            // Get selected booking type
            val selectedTypeId = radioGroup.checkedRadioButtonId
            val bookingType = if (selectedTypeId != -1) {
                view.findViewById<RadioButton>(selectedTypeId).text.toString()
            } else {
                null
            }

            // Validate inputs
            if (rooms.isBlank() || people.isBlank() || checkinDate.isBlank() || checkoutDate.isBlank() || bookingType == null) {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Prepare booking details
            val bookingDetails = """
                Booking Type: $bookingType
                Number of Rooms: $rooms
                Number of People: $people
                Check-in Date: $checkinDate
                Check-out Date: $checkoutDate
            """.trimIndent()

            // Send email to user and yourself
            sendEmail(
                userEmail = "olt.imeri@student.uni-pr.edu",
                bookingDetails = bookingDetails
            )
        }

        return view
    }

    private fun showDatePicker(calendar: Calendar, onDateSelected: (String) -> Unit) {
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                onDateSelected(dateFormat.format(calendar.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun sendEmail(userEmail: String, bookingDetails: String) {
        val subject = "Booking Confirmation"
        val userMessage = """
            Thank you for your booking!
            Here are your booking details:
            
            $bookingDetails
        """.trimIndent()

        val adminMessage = """
            New Booking Received!
            Details:
            
            $bookingDetails
        """.trimIndent()

        // Send email to user
        val userEmailIntent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_EMAIL, arrayOf(userEmail))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, userMessage)
        }

        // Send email to admin
        val adminEmailIntent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("olt10gamer@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "New Booking Received")
            putExtra(Intent.EXTRA_TEXT, adminMessage)
        }

        // Open chooser for user email
        val chooserIntent = Intent.createChooser(userEmailIntent, "Send Email")
        startActivity(chooserIntent)

        // Open chooser for admin email
        startActivity(adminEmailIntent)
    }
}
