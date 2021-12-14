package com.path_studio.nike.ui.seeAllProduct

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.path_studio.nike.R
import com.path_studio.nike.databinding.ActivitySeeAllProductBinding
import com.path_studio.nike.viewModel.ViewModelFactory
import com.path_studio.nike.vo.Status

class SeeAllProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeeAllProductBinding

    private lateinit var viewModel: SeeAllViewModel
    private var selectedCategory = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeeAllProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //preparing chip and rv data
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[SeeAllViewModel::class.java]

        prepareCategoryChips()

        //chip group listener
        with(binding){
            categoriesChipsContainer.setOnCheckedChangeListener { chipGroup, _ ->
                prepareCollectionRV(viewModel, chipGroup.checkedChipId)
            }
        }

        //btn back listener
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    @SuppressLint("UseCompatLoadingForColorStateLists")
    private fun prepareCategoryChips(){
        viewModel.getAllCategory().observe(this, { categories ->
            if (categories != null) {
                when (categories.status) {
                    Status.LOADING -> {}
                    Status.SUCCESS -> {
                        val catData = categories.data
                        if(catData != null && catData.size > 0){
                            var isFirstCategory = true
                            for (d in catData){
                                val chip = Chip(this)
                                val chipDrawable = ChipDrawable.createFromAttributes(
                                    this,
                                    null,
                                    0,
                                    R.style.CustomChipChoice
                                )
                                chip.setChipDrawable(chipDrawable)
                                chip.isClickable = true

                                val chipId = d.id.toInt()
                                chip.id = chipId
                                chip.tag = chipId

                                chip.text = d.name
                                chip.setTextColor(resources.getColorStateList(R.color.text_color_chip_state_list))
                                chip.isCloseIconVisible = false

                                chip.setOnClickListener {
                                    binding.categoriesChipsContainer.check(chipId)
                                }

                                binding.categoriesChipsContainer.addView(chip)

                                if(isFirstCategory){
                                    selectedCategory = chipId
                                    binding.categoriesChipsContainer.check(chip.id)
                                    isFirstCategory = false
                                }
                            }
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, "Something Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun prepareCollectionRV(viewModel: SeeAllViewModel, categoryId: Int){
        val collectionAdapter = SeeAllProductAdapter(viewModel)

        viewModel.getProductsByCategory(categoryId).observe(this, { products ->
            if (products != null) {
                when (products.status) {
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        showHideDataIndicator(true)
                        binding.progressBar.visibility = View.GONE
                        collectionAdapter.setProducts(products.data)
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, "Something Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                showHideDataIndicator(false)
            }
        })

        with(binding) {
            rvCollections.layoutManager = GridLayoutManager(this@SeeAllProductActivity, 2)
            rvCollections.setHasFixedSize(true)
            rvCollections.adapter = collectionAdapter
        }
    }

    private fun showHideDataIndicator(hide: Boolean){
        with(binding){
            if (hide){
                noCollectionIcon.visibility = View.GONE
                noCollectionTxt.visibility = View.GONE
            }else{
                noCollectionIcon.visibility = View.VISIBLE
                noCollectionTxt.visibility = View.VISIBLE
            }
        }
    }

}