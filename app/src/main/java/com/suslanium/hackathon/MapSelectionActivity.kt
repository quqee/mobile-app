package com.suslanium.hackathon

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.suslanium.hackathon.databinding.ActivityMapSelectionBinding
import com.yandex.mapkit.MapKitFactory

class MapSelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapSelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapSelectionBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        MapKitFactory.initialize(this)

        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.mapView.onStart()
    }

    override fun onStop() {
        MapKitFactory.getInstance().onStop()
        binding.mapView.onStop()
        super.onStop()
    }
}