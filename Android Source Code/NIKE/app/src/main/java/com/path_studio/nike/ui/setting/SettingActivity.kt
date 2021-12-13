package com.path_studio.nike.ui.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.path_studio.nike.R
import com.path_studio.nike.databinding.ActivityMainBinding
import com.path_studio.nike.databinding.ActivitySettingBinding
import com.path_studio.nike.ui.about.AboutActivity
import com.path_studio.nike.ui.welcome.WelcomeActivity

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnAbout.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
    }
}