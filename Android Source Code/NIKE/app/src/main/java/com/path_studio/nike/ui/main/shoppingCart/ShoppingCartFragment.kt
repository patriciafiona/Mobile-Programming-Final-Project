package com.path_studio.nike.ui.main.shoppingCart

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.path_studio.nike.R
import com.path_studio.nike.data.source.local.entity.CartDetailEntity
import com.path_studio.nike.data.source.local.entity.TransactionEntity
import com.path_studio.nike.databinding.FragmentShoppingCartBinding
import com.path_studio.nike.utils.Utils.getNumberThousandFormat
import com.path_studio.nike.viewModel.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class ShoppingCartFragment : Fragment() {

    private var _binding: FragmentShoppingCartBinding? = null
    private val binding get() = _binding as FragmentShoppingCartBinding

    private lateinit var prefs: SharedPreferences
    private var tempTotal = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //set binding
        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get data from database
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel =
                ViewModelProvider(requireActivity(), factory)[ShoppingCartViewModel::class.java]

            val cartAdapter = ShoppingCartAdapter(requireActivity(), viewModel)
            viewModel.getShoppingCartData().observe(requireActivity() , { products ->
                binding.progressBar.visibility = View.GONE
                if (products.isEmpty()) {
                    showHideDataIndicator(true)
                    binding.totalPrice.text = "Rp ${getNumberThousandFormat(0.0)}"

                    setCheckoutBtn(true, null, viewModel)
                }else {
                    showHideDataIndicator(false)

                    tempTotal = 0.0
                    for(data in products){
                        if(data.discount > 0){
                            tempTotal += data.quantity.toDouble() * (data.price - (data.price * data.discount / 100))
                        }else if(data.discount == 0) {
                            tempTotal += data.quantity.toDouble() * data.price
                        }
                    }
                    binding.totalPrice.text = "Rp ${getNumberThousandFormat(tempTotal)}"

                    setCheckoutBtn(false, products, viewModel)
                }
                cartAdapter.setProducts(products)
                cartAdapter.notifyDataSetChanged()
            })

            with(binding.rvShoppingCart) {
                layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
                adapter = cartAdapter
            }

            prefs = requireActivity().getSharedPreferences("com.path_studio.nike", AppCompatActivity.MODE_PRIVATE)
            if(prefs.getBoolean("isLogin", false) && prefs.getString("userAddress", "")?.isEmpty() == false) {
                binding.locationIcon.isVisible = true
                binding.userAddress.isVisible = true

                binding.userAddress.text = prefs.getString("userAddress", "").toString()
            }else{
                binding.locationIcon.isVisible = false
                binding.userAddress.isVisible = false
            }
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
    }

    @SuppressLint("SimpleDateFormat")
    private fun setCheckoutBtn(isEmpty: Boolean, cartProducts: PagedList<CartDetailEntity>?, viewModel: ShoppingCartViewModel){
        binding.btnCheckout.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.rvShoppingCart.visibility = View.GONE
            if(isEmpty){
                Toast.makeText(
                    requireActivity(),
                    "Empty Shopping Cart",
                    Toast.LENGTH_LONG
                ).show()
                binding.progressBar.visibility = View.GONE
                binding.rvShoppingCart.visibility = View.VISIBLE
            }else{
                //insert product one by one
                if (prefs.getBoolean("isLogin", false)) {
                    if(cartProducts != null) {
                        val sdf = SimpleDateFormat("ddMyyyyhhmmss")
                        val currentDateTime = sdf.format(Date())
                        val userId = prefs.getInt("userId", 0)
                        val transactionId = "TRS${userId}${cartProducts.size}${currentDateTime}"
                        for ((i, data) in cartProducts.withIndex()) {
                            val transactionData = TransactionEntity(
                                null,
                                transactionId,
                                userId,
                                data.productId.toInt(),
                                data.quantity,
                                data.colorId,
                                data.discount,
                                data.price,
                                tempTotal,
                                cartProducts.size,
                                data.size,
                                1
                            )
                            val userEmail = prefs.getString("userEmail", "").toString()

                            viewModel.insertTransaction(transactionData, userEmail)
                                .observe(requireActivity(), { status ->
                                    when (status) {
                                        "success" -> {
                                            //delete data from shopping cart database
                                            viewModel.deleteProductToCart(data.id)

                                            if (i == cartProducts.size - 1) {
                                                Toast.makeText(
                                                    requireActivity(),
                                                    "Success to Process Transaction",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                            binding.progressBar.visibility = View.GONE
                                            binding.rvShoppingCart.visibility = View.VISIBLE
                                        }
                                        "failed" -> {
                                            Toast.makeText(
                                                requireActivity(),
                                                "Failed to Checkout",
                                                Toast.LENGTH_LONG
                                            ).show()
                                            binding.progressBar.visibility = View.GONE
                                            binding.rvShoppingCart.visibility = View.VISIBLE
                                        }
                                        "user_not_found" -> {
                                            Toast.makeText(
                                                requireActivity(),
                                                "User Not Found For This Transaction",
                                                Toast.LENGTH_LONG
                                            ).show()
                                            binding.progressBar.visibility = View.GONE
                                            binding.rvShoppingCart.visibility = View.VISIBLE
                                        }
                                    }
                                })
                        }
                    }
                }else{
                    Toast.makeText(
                        requireActivity(),
                        "Connect to Process Transaction",
                        Toast.LENGTH_LONG
                    ).show()
                    binding.progressBar.visibility = View.GONE
                    binding.rvShoppingCart.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun showHideDataIndicator(isShow: Boolean) {
        with(binding) {
            if (isShow) {
                noProductImg.visibility = View.VISIBLE
                noProductTitle.visibility = View.VISIBLE
                noProductSubtitle.visibility = View.VISIBLE
            } else {
                noProductImg.visibility = View.GONE
                noProductTitle.visibility = View.GONE
                noProductSubtitle.visibility = View.GONE
            }
        }
    }

}