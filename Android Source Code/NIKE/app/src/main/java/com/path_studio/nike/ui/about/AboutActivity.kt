package com.path_studio.nike.ui.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.path_studio.nike.R
import com.path_studio.nike.databinding.ActivityAboutBinding
import com.path_studio.nike.databinding.ActivitySettingBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}