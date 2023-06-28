package com.example.bitirmeprojesi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_secim.*


class SecimFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_secim, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        metingirbuton.setOnClickListener {
            val action =SecimFragmentDirections.actionSecimFragmentToMetinFragment()
            Navigation.findNavController(it).navigate(action)
        }
        galerideneklebuton.setOnClickListener {
            val action=SecimFragmentDirections.actionSecimFragmentToGaleriFragment()
            Navigation.findNavController(it).navigate(action)
        }
        fotografcekbuton.setOnClickListener {
            val action =SecimFragmentDirections.actionSecimFragmentToKameraFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }


}