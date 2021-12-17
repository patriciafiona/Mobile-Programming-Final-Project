package com.path_studio.nike.ui.main.home

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.path_studio.nike.R
import com.path_studio.nike.databinding.FragmentHomeBinding
import com.path_studio.nike.ui.main.home.adapter.ProductMDAdapter
import com.path_studio.nike.ui.main.home.adapter.ProductRotateXLAdapter
import com.path_studio.nike.ui.seeAllProduct.SeeAllProductActivity
import com.path_studio.nike.viewModel.ViewModelFactory
import com.path_studio.nike.vo.Status

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding

    private lateinit var viewModel:HomeViewModel
    private var selectedCategory = 1

    private val officialWebLink = "https://www.nike.com/"

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
            val navView = activity?.findViewById<View>(R.id.top_nav_container)

            if (scrollY in 0..70) {
                navView?.visibility = View.VISIBLE
            }else{
                navView?.visibility = View.INVISIBLE
            }
        }

        //chip group listener
        with(binding){
            categoriesChipsContainer.setOnCheckedChangeListener { chipGroup, _ ->
                prepareCollectionRV(viewModel, chipGroup.checkedChipId)
                prepareLatestRV(viewModel, chipGroup.checkedChipId)
                prepareTypeRV(rvBasketballShoes, "basket", viewModel, chipGroup.checkedChipId, "%basket%")
                prepareTypeRV(rvHighTopShoes, "high tops", viewModel, chipGroup.checkedChipId, "%high tops%")
                prepareTypeRV(rvSneakersShoes, "sneakers", viewModel, chipGroup.checkedChipId, "%sneakers%")
            }
        }

        //see all button listener
        binding.btnSeeAll.setOnClickListener {
            val intent = Intent(requireActivity(), SeeAllProductActivity::class.java)
            startActivity(intent)
        }

        //bottom banner button link
        binding.buttonOfficialWebsite.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(officialWebLink))
            startActivity(browserIntent)
        }

    }

    @SuppressLint("UseCompatLoadingForColorStateLists")
    private fun prepareCategoryChips(){
        if(activity != null) {
            viewModel.getAllCategory().observe(requireActivity(), { categories ->
                if (categories != null) {
                    when (categories.status) {
                        Status.LOADING -> {}
                        Status.SUCCESS -> {
                            val catData = categories.data
                            if (catData != null && catData.size > 0) {
                                var isFirstCategory = true
                                for (d in catData) {
                                    val chip = Chip(activity)
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

                                    if (isFirstCategory) {
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
    }

    private fun prepareCollectionRV(viewModel: HomeViewModel, categoryId: Int){
        val collectionAdapter = ProductRotateXLAdapter(viewModel)

        viewModel.getProductsByCategoryWithLimit(requireActivity(), categoryId, 5).observe(requireActivity(), { products ->
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

    private fun prepareLatestRV(viewModel: HomeViewModel, categoryId: Int){
        val adapter = ProductMDAdapter()

        viewModel.getLatestProductWithLimit(requireActivity(), categoryId, 5).observe(requireActivity(), { products ->
            if (products != null) {
                when (products.status) {
                    Status.LOADING -> binding.progressBarBasketball.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        showHideDataIndicator("latest", true)
                        binding.progressBarBasketball.visibility = View.GONE
                        adapter.submitList(products.data)
                    }
                    Status.ERROR -> {
                        binding.progressBarBasketball.visibility = View.GONE
                        Toast.makeText(context, "Something Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                showHideDataIndicator("latest", false)
            }
        })

        with(binding){
            rvLatestShoes.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            rvLatestShoes.setHasFixedSize(true)
            rvLatestShoes.adapter = adapter
        }

    }

    private fun prepareTypeRV(rv: RecyclerView, status: String, viewModel: HomeViewModel, categoryId: Int, typeName: String){
        val adapter = ProductMDAdapter()

        viewModel.getProductsByCategoryAndTypeWithLimit(requireActivity(), categoryId, typeName, 5).observe(requireActivity(), { products ->
            if (products != null) {
                when (products.status) {
                    Status.LOADING -> binding.progressBarBasketball.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        showHideDataIndicator(status, true)
                        binding.progressBarBasketball.visibility = View.GONE
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