package com.example.bitirmeprojesi

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_kaydet.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream

class KaydetFragment : Fragment() {

    private lateinit var generateImageButton: Button
    private lateinit var imageView: ImageView
   // private lateinit var button: Button
    private val PICK_IMAGE_REQUEST = 1

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_galeri, container, false)

        return inflater.inflate(R.layout.fragment_kaydet, container, false)

    }
    private fun generateAndShowImage() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://api.unsplash.com/photos/random?client_id=2fui24ZLllPX--mViI7AtRoU_NaTsrDkd3f_o_aM4-E")
            .build()

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = client.newCall(request).execute()

                if (!response.isSuccessful) {
                    println("Hata: ${response.code}")
                    return@launch
                }

                val responseData = response.body?.string()
                val jsonObject = JSONObject(responseData)

                val imageUrl = jsonObject.getJSONObject("urls").getString("regular")

                val imageRequest = Request.Builder()
                    .url(imageUrl)
                    .build()

                val photoResponse = client.newCall(imageRequest).execute()

                if (!photoResponse.isSuccessful) {
                    println("Fotoğraf alınamadı: ${photoResponse.code}")
                    return@launch
                }

                val inputStream = photoResponse.body?.byteStream()
                val bitmap = BitmapFactory.decodeStream(inputStream)

                launch(Dispatchers.Main) {
                    imageView.setImageBitmap(bitmap)
                }

                println("Rastgele fotoğraf başarıyla gösterildi.")

            } catch (e: Exception) {
                println("Hata: ${e.message}")
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        geridon.setOnClickListener {
            val action = KaydetFragmentDirections.actionKaydetFragmentToSecimFragment()
            Navigation.findNavController(it).navigate(action)
        }
        anasayfa2.setOnClickListener {
            val action = KaydetFragmentDirections.actionKaydetFragmentToSecimFragment()
            Navigation.findNavController(it).navigate(action)
        }
        generateImageButton = view.findViewById(R.id.button_generate_photo)
        imageView = view.findViewById(R.id.imageView)

        generateImageButton.setOnClickListener {
            generateAndShowImage()
        }

    }

}
