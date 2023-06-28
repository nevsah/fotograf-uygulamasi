package com.example.bitirmeprojesi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_galeri.*

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.FileOutputStream
import android.graphics.BitmapFactory
import kotlinx.coroutines.launch

class GaleriFragment : Fragment() {


    private lateinit var imageView: ImageView
    private lateinit var button: Button

    private val PICK_IMAGE_REQUEST = 1

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_galeri, container, false)
        imageView = view.findViewById(R.id.imageView)

        button = view.findViewById(R.id.button)
        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        olustur.setOnClickListener {
            val action= GaleriFragmentDirections.actionGaleriFragmentToKaydetFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri = data.data
            val bitmap: Bitmap =
                MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, imageUri)
            imageView.setImageBitmap(bitmap)
        }
    }

}