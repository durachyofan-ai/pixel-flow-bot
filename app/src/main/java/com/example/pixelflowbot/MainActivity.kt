package com.example.pixelflowbot

import android.content.Intent
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.example.pixelflowbot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.statusText.text = "Status: prototype ready"

        binding.startButton.setOnClickListener {
            if (isAccessibilityServiceEnabled()) {
                binding.statusText.text = "Status: bot engine is active"
            } else {
                binding.statusText.text = "Status: enable accessibility service"
                openAccessibilitySettings()
            }
        }

        binding.stopButton.setOnClickListener {
            binding.statusText.text = "Status: stopped"
        }

        binding.openAccessibilitySettings.setOnClickListener {
            openAccessibilitySettings()
        }
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

        val expected = packageName + "/" + PixelFlowAccessibilityService::class.java.name
        return enabledServices?.contains(expected) == true
    }
}
