package com.path_studio.nike.ui.detailProduct.imagePreview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.path_studio.nike.R
import com.path_studio.nike.databinding.ActivityImageViewBinding
import com.path_studio.nike.ui.detailProduct.bottomSheet.OnBottomSheetCallbacks

class ImageViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageViewBinding
    private var listener: OnBottomSheetCallbacks? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras

        if (extras != null) {
            val imgUrl = extras.getString("img_url")

            Glide.with(this)
                .load(imgUrl)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error_img)
                )
                .into(binding.photoView)
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}