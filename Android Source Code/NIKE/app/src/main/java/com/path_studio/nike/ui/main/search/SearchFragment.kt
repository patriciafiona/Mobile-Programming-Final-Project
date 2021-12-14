package com.path_studio.nike.ui.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.path_studio.nike.R
import com.path_studio.nike.databinding.FragmentSearchBinding
import com.path_studio.nike.di.Injection

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding as FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //set binding
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showResult(false)

        //search field listener
        binding.searchField.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(name: String): Boolean {
                if (name.isNotEmpty() && name != "") {
                    binding.searchText.text = name
                    binding.skeleton.showSkeleton()
                    loadSearchResult(name)

                    showResult(true)
                }else{
                    showResult(false)
                }
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

        })

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

    private fun loadSearchResult(name: String){
        val searchViewModel = SearchViewModel(Injection.provideNikeRepository(requireActivity()))
        val results = searchViewModel.getSearchResult(name)

        val searchAdapter = SearchAdapter()

        results.observe(requireActivity(), { detail ->
            searchAdapter.setResult(detail)
        })

        with(binding){
            rvSearchResult.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            rvSearchResult.setHasFixedSize(true)
            rvSearchResult.adapter = searchAdapter
        }

        searchViewModel.getLoading().observe(requireActivity(), {
            if (it) {
                binding.skeleton.showSkeleton()
            }else{
                binding.skeleton.showOriginal()
            }
        })
    }

    private fun showResult(status: Boolean){
        with(binding){
            if (status){
                textView9.visibility = View.VISIBLE
                searchText.visibility = View.VISIBLE
                rvSearchResult.visibility = View.VISIBLE
            }else{
                textView9.visibility = View.GONE
                searchText.visibility = View.GONE
                rvSearchResult.visibility = View.GONE
            }
        }
    }

}