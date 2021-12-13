package com.path_studio.nike.ui.detailProduct

import android.annotation.SuppressLint
import android.app.ActionBar
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.path_studio.nike.R
import com.path_studio.nike.data.source.local.entity.ProductEntity
import com.path_studio.nike.databinding.FragmentDetailProductBinding
import com.path_studio.nike.ui.detailProduct.bottomSheet.OnBottomSheetCallbacks
import com.path_studio.nike.ui.main.MainActivity
import com.path_studio.nike.utils.Utils.getNumberThousandFormat
import com.path_studio.nike.viewModel.ViewModelFactory
import com.path_studio.nike.vo.Status


class DetailProductFragment : BottomSheetDialogFragment(), OnBottomSheetCallbacks {
    private var _binding: FragmentDetailProductBinding? = null
    private val binding get() = _binding as FragmentDetailProductBinding
    private var currentState: Int = BottomSheetBehavior.STATE_HALF_EXPANDED

    private var nCurrentProductDetailPos = 0
    private var productId: Long = 0L
    private var isColorLoaded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //set binding
        _binding = FragmentDetailProductBinding.inflate(inflater, container, false)
        val view = binding.root

        //set bottomSheet Callbacks
        (activity as DetailProductActivity).setOnBottomSheetCallbacks(this)
        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //default, show half layout
        (activity as DetailProductActivity).closeBottomSheet()

        //get product ID and get the data
        val extras = requireActivity().intent.extras

        if (extras != null) {
            productId = extras.getLong(DetailProductActivity.EXTRA_PRODUCT)
            nCurrentProductDetailPos = extras.getInt(DetailProductActivity.EXTRA_PRODUCT_POS)
            if (productId != 0L) {
                val factory = ViewModelFactory.getInstance(requireActivity())
                val viewModel = ViewModelProvider(this, factory)[DetailProductViewModel::class.java]

                viewModel.getProductsByID(productId.toInt()).observe(this, { products ->
                    if (products != null) {
                        when (products.status) {
                            Status.LOADING -> binding.skeletonData.showSkeleton()
                            Status.SUCCESS -> {
                                binding.skeletonData.showOriginal()

                                //show data to UI
                                val productData = products.data!!

                                with(binding) {
                                    //main
                                    val navView = requireActivity().findViewById<View>(R.id.top_nav_container)

                                    //details
                                    productName.text = productData[nCurrentProductDetailPos]?.name ?: ""
                                    productCategory.text = "${productData[nCurrentProductDetailPos]?.categoryName}'s Shoes"
                                    productType.text = "${productData[nCurrentProductDetailPos]?.categoryName}'s Shoes"
                                    productStock.text = productData[nCurrentProductDetailPos]?.stock.toString()
                                    productProductColorDesc.text = productData[nCurrentProductDetailPos]?.colorDescription
                                    productPriceAfter.text = "Rp ${productData[nCurrentProductDetailPos]?.let {
                                        getNumberThousandFormat(
                                            it.price)
                                    }}"
                                    productRating.text = productData[nCurrentProductDetailPos]?.rating.toString()

                                    //show color selection
                                    if(!isColorLoaded) {
                                        val listColor = ArrayList<String>()
                                        for (d in productData) {
                                            listColor.add(d.colorCode)
                                        }

                                        colorContainer.removeAllViews()
                                        for (i in 0 until listColor.size) {
                                            val colorView = CardView(requireActivity())

                                            val params: RelativeLayout.LayoutParams =
                                                RelativeLayout.LayoutParams(30, 30)
                                            params.setMargins(0, 0, 25, 10)

                                            colorView.layoutParams =
                                                RelativeLayout.LayoutParams(params)
                                            colorView.minimumHeight = 10

                                            colorView.layoutParams.height = 100
                                            colorView.layoutParams.width = 100
                                            colorView.radius = TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP, 50f,
                                                context?.resources?.displayMetrics
                                            )
                                            colorView.setCardBackgroundColor(
                                                Color.parseColor(
                                                    listColor[i]
                                                )
                                            )

                                            colorView.setOnClickListener {
                                                //show category result
                                                val intent = Intent(
                                                    requireActivity(),
                                                    DetailProductActivity::class.java
                                                )
                                                intent.putExtra(
                                                    DetailProductActivity.EXTRA_PRODUCT,
                                                    productId
                                                )
                                                intent.putExtra(
                                                    DetailProductActivity.EXTRA_PRODUCT_POS,
                                                    i
                                                )
                                                requireActivity().finish()
                                                requireActivity().overridePendingTransition(0, 0)
                                                requireActivity().startActivity(intent)
                                            }

                                            colorContainer.addView(colorView)
                                        }
                                        isColorLoaded = true
                                    }
                                }
                            }
                            Status.ERROR -> {
                                binding.skeletonData.showOriginal()
                                Toast.makeText(requireActivity(), "Something Error", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
        }

        binding.indicatorImage.setOnClickListener {
            if (currentState == BottomSheetBehavior.STATE_EXPANDED) {
                (activity as DetailProductActivity).closeBottomSheet()
            } else if (currentState == BottomSheetBehavior.STATE_HALF_EXPANDED){
                (activity as DetailProductActivity).openBottomSheet()
            }
        }

    }

    override fun onStateChanged(bottomSheet: View, newState: Int) {
        currentState = newState
        when (newState) {
            BottomSheetBehavior.STATE_EXPANDED -> {
                binding.indicatorImage.setImageResource(R.drawable.ic_baseline_expand_more_black)
            }
            BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                binding.indicatorImage.setImageResource(R.drawable.ic_baseline_expand_less_black)
            }
        }
    }
}