package com.path_studio.nike.ui.main.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.core.view.iterator
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.google.android.material.chip.Chip
import com.path_studio.moviecatalogue.vo.Status
import com.path_studio.nike.databinding.FragmentHomeBinding
import com.path_studio.nike.ui.main.home.adapter.CollectionAdapter
import com.path_studio.nike.viewModel.ViewModelFactory

import com.google.android.material.chip.ChipDrawable
import com.path_studio.nike.R


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding

    private lateinit var viewModel:HomeViewModel
    private var selectedCategory = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //set binding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.categoriesChipsContainer.isSelectionRequired = true

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(requireActivity(), factory)[HomeViewModel::class.java]

            prepareCategoryChips()
        }

        //scroll listener
        binding.scrollContainer.viewTreeObserver.addOnScrollChangedListener {
            val scrollY: Int = binding.scrollContainer.scrollY
            val navView = requireActivity().findViewById<View>(com.path_studio.nike.R.id.top_nav_container)

            if (scrollY in 0..70) {
                navView.visibility = View.VISIBLE
            }else{
                navView.visibility = View.INVISIBLE
            }
        }

        //chip group listener
        binding.categoriesChipsContainer.setOnCheckedChangeListener { chipGroup, id ->
            prepareCollectionRV(viewModel, chipGroup.checkedChipId)
        }
    }

    @SuppressLint("UseCompatLoadingForColorStateLists")
    private fun prepareCategoryChips(){
        viewModel.getAllCategory().observe(requireActivity(), { categories ->
            if (categories != null) {
                when (categories.status) {
                    Status.LOADING -> {}
                    Status.SUCCESS -> {
                        val catData = categories.data
                        if(catData != null && catData.size > 0){
                            var isFirstCategory = true
                            for (d in catData){
                                val chip = Chip(requireActivity())
                                val chipDrawable = ChipDrawable.createFromAttributes(
                                    requireActivity(),
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
                        Toast.makeText(context, "Something Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun prepareCollectionRV(viewModel: HomeViewModel, categoryId: Int){
        val collectionAdapter = CollectionAdapter()
        viewModel.getProductsByCategory(categoryId).observe(requireActivity(), { products ->
            if (products != null) {
                when (products.status) {
                    Status.LOADING -> binding.progressBarCollection.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        showHideDataIndicator("collection", true)
                        binding.progressBarCollection.visibility = View.GONE
                        collectionAdapter.submitList(products.data)
                    }
                    Status.ERROR -> {
                        binding.progressBarCollection.visibility = View.GONE
                        Toast.makeText(context, "Something Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                showHideDataIndicator("collection", false)
            }
        })

        with(binding) {
            rvCollections.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            rvCollections.setHasFixedSize(true)
            rvCollections.adapter = collectionAdapter
        }
    }

    private fun showHideDataIndicator(status: String, hide: Boolean){
        with(binding){
            when(status){
                "all" ->{
                    noCollectionIcon.visibility = View.VISIBLE
                    noCollectionTxt.visibility = View.VISIBLE

                    noLatestIcon.visibility = View.VISIBLE
                    noLatestTxt.visibility = View.VISIBLE

                    noBasketIcon.visibility = View.VISIBLE
                    noBasketTxt.visibility = View.VISIBLE

                    noHighTopIcon.visibility = View.VISIBLE
                    noHighTopTxt.visibility = View.VISIBLE

                    noSneakerIcon.visibility = View.VISIBLE
                    noSneakerTxt.visibility = View.VISIBLE
                }
                "collection" ->{
                    if (hide){
                        noCollectionIcon.visibility = View.GONE
                        noCollectionTxt.visibility = View.GONE
                    }else{
                        noCollectionIcon.visibility = View.VISIBLE
                        noCollectionTxt.visibility = View.VISIBLE
                    }
                }
                "latest" ->{
                    if (hide){
                        noLatestIcon.visibility = View.GONE
                        noLatestTxt.visibility = View.GONE
                    }else{
                        noLatestIcon.visibility = View.VISIBLE
                        noLatestTxt.visibility = View.VISIBLE
                    }
                }
                else ->{}
            }
        }
    }

}