package com.path_studio.nike.ui.detailProduct

import android.annotation.SuppressLint
import android.app.ActionBar
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
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
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.path_studio.nike.R
import com.path_studio.nike.data.source.local.entity.ProductEntity
import com.path_studio.nike.databinding.FragmentDetailProductBinding
import com.path_studio.nike.ui.detailProduct.bottomSheet.OnBottomSheetCallbacks
import com.path_studio.nike.ui.main.MainActivity
import com.path_studio.nike.utils.Utils.getNumberThousandFormat
import com.path_studio.nike.viewModel.ViewModelFactory
import com.path_studio.nike.vo.Status
import android.text.InputFilter
import android.text.TextWatcher
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.path_studio.nike.data.source.local.entity.CartEntity


class DetailProductFragment : BottomSheetDialogFragment(), OnBottomSheetCallbacks {
    private var _binding: FragmentDetailProductBinding? = null
    private val binding get() = _binding as FragmentDetailProductBinding
    private var currentState: Int = BottomSheetBehavior.STATE_HALF_EXPANDED

    private var nCurrentProductDetailPos = 0
    private var productId: Long = 0L
    private var isColorLoaded = false

    private var selectedSize = 0
    private var currentQuantity = 1
    private var isAddProduct = true

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

                //get product details
                viewModel.getProductsByID(productId.toInt()).observe(this, { products ->
                    if (products != null) {
                        when (products.status) {
                            Status.LOADING -> binding.skeletonData.showSkeleton()
                            Status.SUCCESS -> {
                                val productData = products.data!!

                                //check product in cart
                                viewModel.checkProductInCart(productData[nCurrentProductDetailPos]?.productDetailId!!)
                                    .observe(requireActivity(), { products ->
                                        isAddProduct = products.isEmpty()

                                        if (products.isNotEmpty() && products.size > 0) {
                                            binding.quantityField.setText(products[0]?.quantity.toString())

                                            currentQuantity = products[0]?.quantity ?: 1

                                            //show product sizes with database checking
                                            if(productData[nCurrentProductDetailPos]?.categoryId == 3 || productData[nCurrentProductDetailPos]?.categoryId == 4) {
                                                //kids size
                                                selectedSize = products[0]?.size ?: 25
                                                for (num in 23..35) {
                                                    createChips(num)
                                                }
                                            }else if(productData[nCurrentProductDetailPos]?.categoryId == 1){
                                                //mens shoes
                                                selectedSize = products[0]?.size ?: 40
                                                for (num in 38..51) {
                                                    createChips(num)
                                                }
                                            }else if(productData[nCurrentProductDetailPos]?.categoryId == 1){
                                                //womens shoes
                                                selectedSize = products[0]?.size ?: 38
                                                for (num in 34..46) {
                                                    createChips(num)
                                                }
                                            }
                                        }else{
                                            //show product sizes without checking database
                                            if(productData[nCurrentProductDetailPos]?.categoryId == 3 || productData[nCurrentProductDetailPos]?.categoryId == 4) {
                                                //kids size
                                                selectedSize = 23
                                                for (num in 23..35) {
                                                    createChips(num)
                                                }
                                            }else if(productData[nCurrentProductDetailPos]?.categoryId == 1){
                                                //mens shoes
                                                selectedSize = 38
                                                for (num in 38..51) {
                                                    createChips(num)
                                                }
                                            }else if(productData[nCurrentProductDetailPos]?.categoryId == 1){
                                                //womens shoes
                                                selectedSize = 34
                                                for (num in 34..46) {
                                                    createChips(num)
                                                }
                                            }
                                        }

                                        if(isAddProduct) {
                                            binding.btnAddRemove.text = requireActivity().resources.getString(R.string.add_to_bag)
                                        }else{
                                            binding.btnAddRemove.text = requireActivity().resources.getString(R.string.update_to_bag)
                                        }

                                        binding.btnAddRemove.setOnClickListener {
                                            currentQuantity = binding.quantityField.text.toString().toInt()

                                            val cartData: CartEntity
                                            if (products.isNotEmpty() && products.size > 0) {
                                                cartData =
                                                    CartEntity(
                                                        products[0]?.id?.toLong() ?: 0,
                                                        productData[nCurrentProductDetailPos]?.productDetailId!!,
                                                        currentQuantity,
                                                        selectedSize
                                                    )
                                            }else{
                                                cartData =
                                                    CartEntity(
                                                         0,
                                                        productData[nCurrentProductDetailPos]?.productDetailId!!,
                                                        currentQuantity,
                                                        selectedSize
                                                    )
                                            }

                                            if(isAddProduct){
                                                viewModel.insertProductToCart(cartData)
                                                binding.btnAddRemove.text = requireActivity().resources.getString(R.string.update_to_bag)
                                                isAddProduct = false
                                                Toast.makeText(requireContext(),"Product Added to Cart",Toast. LENGTH_SHORT).show()
                                            }else{
                                                viewModel.updateProductToCart(cartData)
                                                Toast.makeText(requireContext(),"Product Updated to Cart",Toast. LENGTH_SHORT).show()
                                            }
                                        }
                                })

                                //show data to UI
                                showDatatoUI(productData)
                                binding.skeletonData.showOriginal()
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

        binding.sizeChipsContainer.setOnCheckedChangeListener { chipGroup, _ ->
            selectedSize = chipGroup.checkedChipId
        }

    }

    @SuppressLint("SetTextI18n")
    private fun showDatatoUI(productData: PagedList<ProductEntity>){
        with(binding) {
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

            productDetail.text = productData[nCurrentProductDetailPos]?.description

            //Quantity listener
            binding.quantityField.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}

                override fun beforeTextChanged(s: CharSequence, start: Int,
                                               count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence, start: Int,
                                           before: Int, count: Int) {
                    val quantity = s.toString()
                    if(quantity.isNotEmpty()) {
                        if (quantity.toInt() > 0 && quantity.toInt() <= productData[nCurrentProductDetailPos]?.stock!!) {
                            currentQuantity = s.toString().toInt()
                        }else if(quantity.toInt() == 0){
                            currentQuantity = 1
                        }else if(quantity.toInt() == productData[nCurrentProductDetailPos]?.stock!!.toInt() + 1){
                            currentQuantity = productData[nCurrentProductDetailPos]?.stock!!
                        }
                    }

                }
            })

            binding.btnRemove.setOnClickListener {
                val currentQuantity = binding.quantityField.text.toString().toInt()
                if(currentQuantity > 1){
                    binding.quantityField.setText((currentQuantity - 1).toString())
                }else{
                    binding.quantityField.setText("1")
                }
            }

            binding.btnAdd.setOnClickListener {
                val currentQuantity = binding.quantityField.text.toString().toInt()
                if(currentQuantity < productData[nCurrentProductDetailPos]?.stock!!){
                    binding.quantityField.setText((currentQuantity + 1).toString())
                }else{
                    binding.quantityField.setText(productData[nCurrentProductDetailPos]?.stock!!.toString())
                }
            }
        }
    }

    private fun createChips(num: Int){
        val chip = Chip(requireActivity())
        val chipDrawable = ChipDrawable.createFromAttributes(
            requireActivity(),
            null,
            0,
            R.style.CustomChipChoice
        )
        chip.setChipDrawable(chipDrawable)
        chip.isClickable = true

        val chipId = num
        chip.id = chipId
        chip.tag = chipId

        chip.text = num.toString()
        chip.setTextColor(resources.getColorStateList(R.color.text_color_chip_state_list))
        chip.isCloseIconVisible = false

        chip.setOnClickListener {
            binding.sizeChipsContainer.check(chipId)
        }

        binding.sizeChipsContainer.addView(chip)

        if(num == selectedSize){
            binding.sizeChipsContainer.check(chip.id)
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