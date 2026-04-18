package com.example.foodexpiryscanner.ui.scan

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.foodexpiryscanner.databinding.ActivityScanBinding
import com.example.foodexpiryscanner.ui.addedit.AddEditItemActivity
import com.example.foodexpiryscanner.utils.DateUtils
import com.google.zxing.integration.android.IntentIntegrator

class ScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanBinding

    private val cameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            startScanner()
        } else {
            DateUtils.toast(this, "Camera permission is needed to scan barcodes")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { finish() }
        binding.btnCameraPermission.setOnClickListener { requestCameraIfNeeded() }
        binding.btnStartScan.setOnClickListener { requestCameraIfNeeded() }
    }

    private fun requestCameraIfNeeded() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            startScanner()
        } else {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun startScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setCaptureActivity(PortraitCaptureActivity::class.java)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setPrompt("Scan a food barcode or press back to cancel")
        integrator.setBeepEnabled(true)
        integrator.setOrientationLocked(true)
        integrator.initiateScan()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null && result.contents != null) {
            val intent = Intent(this, AddEditItemActivity::class.java)
            intent.putExtra("barcode", result.contents)
            startActivity(intent)
            finish()
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
