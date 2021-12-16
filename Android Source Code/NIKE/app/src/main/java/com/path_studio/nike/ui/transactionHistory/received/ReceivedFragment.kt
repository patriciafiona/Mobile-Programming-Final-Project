package com.path_studio.nike.ui.transactionHistory.received

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.path_studio.nike.R
import com.path_studio.nike.databinding.FragmentReceivedBinding
import com.path_studio.nike.databinding.FragmentUserAccountBinding

class ReceivedFragment : Fragment() {

    private var _binding: FragmentReceivedBinding? = null
    private val binding get() = _binding as FragmentReceivedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentReceivedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}