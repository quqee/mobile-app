package com.suslanium.hackathon.createdefect.presentation.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.ViewGroup.MarginLayoutParams
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.suslanium.hackathon.R
import com.suslanium.hackathon.databinding.ActivityMapSelectionBinding
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.ScreenPoint
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition

class MapSelectionActivity : AppCompatActivity() {

    companion object {
        const val LATITUDE = "latitude"
        const val LONGITUDE = "longitude"
    }

    private lateinit var binding: ActivityMapSelectionBinding

    @SuppressLint("MissingPermission")
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.getOrDefault(
                android.Manifest.permission.ACCESS_FINE_LOCATION, false
            ) || permissions.getOrDefault(android.Manifest.permission.ACCESS_COARSE_LOCATION, false)
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location == null) return@addOnSuccessListener
                binding.mapView.mapWindow.map.move(
                    CameraPosition(
                        Point(location.latitude, location.longitude), 17.0f, 150.0f, 30.0f
                    )
                )
            }
        }
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapSelectionBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        MapKitFactory.initialize(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        ViewCompat.setOnApplyWindowInsetsListener(
            binding.confirmFab
        ) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.updateLayoutParams<MarginLayoutParams> {
                bottomMargin =
                    this@MapSelectionActivity.resources.getDimensionPixelSize(R.dimen.margin_map) + insets.bottom
            }

            WindowInsetsCompat.CONSUMED
        }

        setContentView(binding.root)

        binding.locationFab.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this, android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(
                    arrayOf(
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            } else {
                fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                    if (location == null) return@addOnSuccessListener
                    binding.mapView.mapWindow.map.move(
                        CameraPosition(
                            Point(location.latitude, location.longitude), 17.0f, 150.0f, 30.0f
                        )
                    )
                }
            }
        }

        binding.confirmFab.setOnClickListener {
            val centerX = binding.mapView.mapWindow.width() / 2f
            val centerY = binding.mapView.mapWindow.height() / 2f
            val centerPoint = ScreenPoint(centerX, centerY)
            val worldPoint = binding.mapView.mapWindow.screenToWorld(centerPoint)
            val resultIntent = Intent().apply {
                putExtra(LATITUDE, worldPoint?.latitude)
                putExtra(LONGITUDE, worldPoint?.longitude)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
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