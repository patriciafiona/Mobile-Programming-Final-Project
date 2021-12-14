package com.path_studio.nike.ui.main.shoppingCart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.path_studio.nike.R
import com.path_studio.nike.databinding.FragmentShoppingCartBinding
import com.path_studio.nike.utils.Utils.getNumberThousandFormat
import com.path_studio.nike.viewModel.ViewModelFactory

class ShoppingCartFragment : Fragment() {

    private var _binding: FragmentShoppingCartBinding? = null
    private val binding get() = _binding as FragmentShoppingCartBinding

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
                }
                cartAdapter.setProducts(products)
                cartAdapter.notifyDataSetChanged()
            })

            with(binding.rvShoppingCart) {
                layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
                adapter = cartAdapter
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