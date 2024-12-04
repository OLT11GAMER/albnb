package com.example.albnb

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.albnb.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {// Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bookjera = view.findViewById<Button>(R.id.book_button_1)
        val booklotboutiquehotel = view.findViewById<Button>(R.id.book_button_2)
        val booksunflowerhotel = view.findViewById<Button>(R.id.book_button_3)
        val bookzenstayapartments = view.findViewById<Button>(R.id.book_button_4)
        val expandtirana = view.findViewById<Button>(R.id.tirana_expand)
        val expandfoodgjilan = view.findViewById<Button>(R.id.gjilan_eat_expand)
        val expandrivera = view.findViewById<Button>(R.id.rivera_expand)

        expandtirana.setOnClickListener{
            Log.d("TAG","yoyoyoy")
            findNavController().navigate(R.id.tirana_expanded_layout)
        }
        expandfoodgjilan.setOnClickListener{
            Log.d("TAG","yoyoyoy")
            findNavController().navigate(R.id.food_gjilan_expanded)
        }
        expandrivera.setOnClickListener{
            Log.d("TAG","yoyoyoy")
            findNavController().navigate(R.id.fragment_rivera)
        }
//
//        bookjera.setOnClickListener {
////          findNavController().navigate(R.id.gjilan_expanded)
//            Log.d("TAG","yoyoyoy")
//            findNavController().navigate(R.id.fragment_booking)
//        }
//        booklotboutiquehotel.setOnClickListener {
////          findNavController().navigate(R.id.gjilan_expanded)
//            Log.d("TAG","yoyoyoy")
//            findNavController().navigate(R.id.fragment_booking)
//        }
//        booksunflowerhotel.setOnClickListener {
////          findNavController().navigate(R.id.gjilan_expanded)
//            Log.d("TAG","yoyoyoy")
//            findNavController().navigate(R.id.fragment_booking)
//        }
//        bookzenstayapartments.setOnClickListener {
////          findNavController().navigate(R.id.gjilan_expanded)
//            Log.d("TAG","yoyoyoy")
//            findNavController().navigate(R.id.fragment_booking)
//        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}