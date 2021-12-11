package com.path_studio.nike.ui.main.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.path_studio.moviecatalogue.vo.Status
import com.path_studio.nike.databinding.FragmentHomeBinding
import com.path_studio.nike.ui.main.home.adapter.ProductRotateXLAdapter
import com.path_studio.nike.viewModel.ViewModelFactory

import com.google.android.material.chip.ChipDrawable
import com.path_studio.nike.R
import com.path_studio.nike.ui.main.home.adapter.ProductMDAdapter


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
        with(binding){
            categoriesChipsContainer.setOnCheckedChangeListener { chipGroup, id ->
                prepareCollectionRV(viewModel, chipGroup.checkedChipId)
                prepareTypeRV(rvBasketballShoes, "basket", viewModel, chipGroup.checkedChipId, "%basket%")
                prepareTypeRV(rvHighTopShoes, "high tops", viewModel, chipGroup.checkedChipId, "%high tops%")
                prepareTypeRV(rvSneakersShoes, "sneakers", viewModel, chipGroup.checkedChipId, "%sneakers%")
            }
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
        val collectionAdapter = ProductRotateXLAdapter()

        viewModel.getProductsByCategoryWithLimit(categoryId, 5).observe(requireActivity(), { products ->
            if (products != null) {
                when (products.status) {
                    Status.LOADING -> binding.progressBarCollection.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        showHideDataIndicator("collection", true)
                        binding.progressBarCollection.visibility = View.GONE

                        //add empty data for show more button
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

    private fun prepareTypeRV(rv: RecyclerView, status: String, viewModel: HomeViewModel, categoryId: Int, typeName: String){
        val adapter = ProductMDAdapter()

        viewModel.getProductsByCategoryAndTypeWithLimit(categoryId, typeName, 5).observe(requireActivity(), { products ->
            if (products != null) {
                when (products.status) {
                    Status.LOADING -> binding.progressBarBasketball.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        showHideDataIndicator(status, true)
                        binding.progressBarBasketball.visibility = View.GONE

                        //add empty data for show more button
                        adapter.submitList(products.data)
                    }
                    Status.ERROR -> {
                        binding.progressBarBasketball.visibility = View.GONE
                        Toast.makeText(context, "Something Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                showHideDataIndicator(status, false)
            }
        })

        rv.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        rv.setHasFixedSize(true)
        rv.adapter = adapter
    }

    private fun showHideDataIndicator(status: String, hide: Boolean){
        with(binding){
            when(status){
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
                "basket" ->{
                    if (hide){
                        noBasketIcon.visibility = View.GONE
                        noBasketTxt.visibility = View.GONE
                    }else{
                        noBasketIcon.visibility = View.VISIBLE
                        noBasketTxt.visibility = View.VISIBLE
                    }
                }
                "high tops" ->{
                    if (hide){
                        noHighTopIcon.visibility = View.GONE
                        noHighTopTxt.visibility = View.GONE
                    }else{
                        noHighTopIcon.visibility = View.VISIBLE
                        noHighTopTxt.visibility = View.VISIBLE
                    }
                }
                "sneakers" ->{
                    if (hide){
                        noSneakerIcon.visibility = View.GONE
                        noSneakerTxt.visibility = View.GONE
                    }else{
                        noSneakerIcon.visibility = View.VISIBLE
                        noSneakerTxt.visibility = View.VISIBLE
                    }
                }
                else ->{}
            }
        }
    }

}