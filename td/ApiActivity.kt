package com.flovit.td

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.td.databinding.ActivityApiBinding
import com.example.td.databinding.ActivityWeatherBinding

class ApiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding by lazy { ActivityApiBinding.inflate(layoutInflater)}
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}