package com.path_studio.nike.ui.transactionHistory.packed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.path_studio.nike.databinding.FragmentPackedBinding

class PackedFragment : Fragment() {

    private var _binding: FragmentPackedBinding? = null
    private val binding get() = _binding as FragmentPackedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPackedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}