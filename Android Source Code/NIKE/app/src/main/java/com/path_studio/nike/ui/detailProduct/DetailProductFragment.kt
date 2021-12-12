package com.path_studio.nike.ui.detailProduct

import android.annotation.SuppressLint
import android.app.ActionBar
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
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
            val productId = extras.getLong(DetailProductActivity.EXTRA_PRODUCT)
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
                                    productName.text = productData[0]?.name ?: ""
                                    productCategory.text = "${productData[0]?.categoryName}'s Shoes"
                                    productPriceAfter.text = "Rp ${productData[0]?.let {
                                        getNumberThousandFormat(
                                            it.price)
                                    }}"
                                    productRating.text = productData[0]?.rating.toString()

                                    //show color selection
                                    val listColor = ArrayList<String>()
                                    for (d in productData){
                                        listColor.add(d.colorCode)
                                    }

                                    for (colorCode in listColor){
                                        val colorView = CardView(requireActivity())

                                        val params: ActionBar.LayoutParams = ActionBar.LayoutParams(
                                            ActionBar.LayoutParams.WRAP_CONTENT,
                                            ActionBar.LayoutParams.WRAP_CONTENT
                                        )
                                        params.setMargins(0, 0, 0, 0)

                                        colorView.layoutParams = ActionBar.LayoutParams(params)

                                        colorView.layoutParams.height = 100
                                        colorView.layoutParams.width = 100
                                        colorView.radius = 30f
                                        colorView.setBackgroundColor(Color.parseColor(colorCode))

                                        colorContainer.addView(colorView)
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