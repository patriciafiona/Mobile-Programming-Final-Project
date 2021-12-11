package com.path_studio.nike.ui.main.favorite

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.path_studio.nike.vo.Status
import com.path_studio.nike.R
import com.path_studio.nike.databinding.FragmentFavoriteBinding
import com.path_studio.nike.ui.main.MainActivity
import com.path_studio.nike.viewModel.ViewModelFactory

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding as FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //set binding
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get and show fav product
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(requireActivity(), factory)[FavoriteViewModel::class.java]

            val favAdapter = FavoriteAdapter(viewModel)
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getFavoriteProducts().observe(activity as MainActivity, { products ->
                binding.progressBar.visibility = View.GONE
                if (products.isEmpty())
                    showHideDataIndicator(true)
                else
                    showHideDataIndicator(false)
                favAdapter.setFavMovies(products)
                favAdapter.notifyDataSetChanged()
            })

            with(binding.rvFavorite) {
                layoutManager = GridLayoutManager(activity, 2)
                setHasFixedSize(true)
                adapter = favAdapter
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

    private fun showHideDataIndicator(isShow: Boolean){
        with(binding){
            if (isShow){
                noFavImg.visibility = View.VISIBLE
                noFavText01.visibility = View.VISIBLE
                noFavText02.visibility = View.VISIBLE
                btnCheckAll.visibility = View.VISIBLE

                rvFavorite.visibility = View.GONE
            }else{
                noFavImg.visibility = View.GONE
                noFavText01.visibility = View.GONE
                noFavText02.visibility = View.GONE
                btnCheckAll.visibility = View.GONE

                rvFavorite.visibility = View.VISIBLE
            }

        }
    }

}