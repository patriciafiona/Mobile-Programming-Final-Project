package com.path_studio.nike.ui.transactionHistory.onDelivery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.path_studio.nike.R
import com.path_studio.nike.databinding.FragmentOnDeliveryBinding
import com.path_studio.nike.databinding.FragmentReceivedBinding

class OnDeliveryFragment : Fragment() {

    private var _binding: FragmentOnDeliveryBinding? = null
    private val binding get() = _binding as FragmentOnDeliveryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOnDeliveryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}