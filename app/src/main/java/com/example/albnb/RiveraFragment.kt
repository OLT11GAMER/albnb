package com.example.albnb

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RiveraFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RiveraFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rivera, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bookjera = view.findViewById<Button>(R.id.book_button_1)
        val booklotboutiquehotel = view.findViewById<Button>(R.id.book_button_2)
        val booksunflowerhotel = view.findViewById<Button>(R.id.book_button_3)
        val bookzenstayapartments = view.findViewById<Button>(R.id.book_button_4)

        bookjera.setOnClickListener{
            Log.d("TAG","yoyoyoy")
            findNavController().navigate(R.id.fragment_booking)
        }
        booklotboutiquehotel.setOnClickListener{
            Log.d("TAG","yoyoyoy")
            findNavController().navigate(R.id.fragment_booking)
        }
        booksunflowerhotel.setOnClickListener{
            Log.d("TAG","yoyoyoy")
            findNavController().navigate(R.id.fragment_booking)
        }
        bookzenstayapartments.setOnClickListener{
            Log.d("TAG","yoyoyoy")
            findNavController().navigate(R.id.fragment_booking)
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


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RiveraFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RiveraFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}