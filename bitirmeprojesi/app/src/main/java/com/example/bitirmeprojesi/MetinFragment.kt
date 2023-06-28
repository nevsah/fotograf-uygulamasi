package com.example.bitirmeprojesi

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.widget.EditText
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_metin.*

class MetinFragment : Fragment() {
    private lateinit var editText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_metin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editText = view.findViewById(R.id.editText) // editText özelliği başlatılır

        anasayfa.setOnClickListener {
            val action = MetinFragmentDirections.actionMetinFragmentToSecimFragment()
            Navigation.findNavController(it).navigate(action)
        }
        metindenkaydede.setOnClickListener {

            if (editText.text.toString().isEmpty()) {
                Toast.makeText( requireContext()," text giriniz", Toast.LENGTH_SHORT).show()

            }else{
                val action = MetinFragmentDirections.actionMetinFragmentToKaydetFragment2()
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

}