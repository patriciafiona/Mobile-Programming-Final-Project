package com.path_studio.nike.ui.main.home

import android.content.Intent
import android.os.Bundle
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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.google.android.material.chip.Chip
import com.path_studio.moviecatalogue.vo.Status
import com.path_studio.nike.R
import com.path_studio.nike.databinding.FragmentFavoriteBinding
import com.path_studio.nike.databinding.FragmentHomeBinding
import com.path_studio.nike.ui.main.home.adapter.CollectionAdapter
import com.path_studio.nike.viewModel.ViewModelFactory




class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding

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

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(requireActivity(), factory)[HomeViewModel::class.java]

            val movieAdapter = CollectionAdapter()
            viewModel.getAllCategory().observe(requireActivity(), { categories ->
                if (categories != null) {
                    when (categories.status) {
                        Status.LOADING -> {}
                        Status.SUCCESS -> {
                            val catData = categories.data
                            if(catData != null && catData.size > 0){
                                for (d in catData){
                                    val chip = Chip(ContextThemeWrapper(activity, com.path_studio.nike.R.style.ThinnerChip))
                                    chip.text = d.name
                                    chip.isCloseIconVisible = false
                                    if(d.id != selectedCategory.toLong()) {
                                        chip.setChipBackgroundColorResource(com.path_studio.nike.R.color.white)
                                        chip.setTextColor(resources.getColor(com.path_studio.nike.R.color.black, null))
                                    }else{
                                        chip.setChipBackgroundColorResource(com.path_studio.nike.R.color.black)
                                        chip.setTextColor(resources.getColor(com.path_studio.nike.R.color.white, null))
                                    }

                                    chip.setOnClickListener {
                                        //go to category result
                                    }

                                    binding.categoriesChipsContainer.addView(chip)
                                }
                            }
                        }
                        Status.ERROR -> {
                            Toast.makeText(context, "Something Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            viewModel.getProductsByCategory(1).observe(requireActivity(), { products ->
                if (products != null) {
                    when (products.status) {
                        Status.LOADING -> binding.progressBarCollection.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding.progressBarCollection.visibility = View.GONE
                            movieAdapter.submitList(products.data)
                        }
                        Status.ERROR -> {
                            binding.progressBarCollection.visibility = View.GONE
                            Toast.makeText(context, "Something Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(binding) {
                rvCollections.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
                rvCollections.setHasFixedSize(true)
                rvCollections.adapter = movieAdapter
            }
        }

        //scroll listener
        binding.scrollContainer.viewTreeObserver.addOnScrollChangedListener {
            val scrollY: Int = binding.scrollContainer.scrollY
            val navView = requireActivity().findViewById<View>(R.id.top_nav_container)

            if (scrollY in 0..70) {
                navView.visibility = View.VISIBLE
            }else{
                navView.visibility = View.INVISIBLE
            }
        }
    }

    private fun showHideDataIndicator(status: String){
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
            }
        }
    }

}