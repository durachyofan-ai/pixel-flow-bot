package com.example.pixelflowbot

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.pixelflowbot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var botRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            startBot()
        }

        binding.stopButton.setOnClickListener {
            stopBot()
        }

        binding.openAccessibilitySettings.setOnClickListener {
            openAccessibilitySettings()
        }
    }

    private fun startBot() {
        val serviceEnabled = isAccessibilityServiceEnabled()
        if (!serviceEnabled) {
            binding.statusText.text = "Status: Enable accessibility service in Settings"
            openAccessibilitySettings()
            return
        }

        botRunning = true
        binding.statusText.text = "Status: bot running"
        Log.d("PixelFlowBot", "Bot started")
    }

    private fun stopBot() {
        botRunning = false
        binding.statusText.text = "Status: stopped"
        Log.d("PixelFlowBot", "Bot stopped")
    }

    private fun openAccessibilitySettings() {
        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        startActivity(intent)
    }

    private fun isAccessibilityServiceEnabled(): Boolean {
        val enabledServices = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        )
        return enabledServices?.contains(packageName + "/" + PixelFlowAccessibilityService::class.java.name) == true
    }
}
