package com.path_studio.nike.ui.transactionHistory.paymentPending

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.path_studio.nike.data.source.local.entity.TransactionEntity
import com.path_studio.nike.databinding.FragmentPaymentPendingBinding
import com.path_studio.nike.ui.transactionHistory.TransactionHistoryViewModel
import com.path_studio.nike.viewModel.ViewModelFactory

class PaymentPendingFragment : Fragment() {
    private var _binding: FragmentPaymentPendingBinding? = null
    private val binding get() = _binding as FragmentPaymentPendingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPaymentPendingBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(requireActivity(), factory)[TransactionHistoryViewModel::class.java]

        val prefs = requireActivity().getSharedPreferences("com.path_studio.nike", AppCompatActivity.MODE_PRIVATE)
        val userId = prefs.getInt("userId", 0)

        showLoadingIndicator(true)
        val pendingPaymentAdapter = PaymentPendingAdapter(viewModel, requireActivity())
        viewModel.getUserTransactionByStatus(userId, 1).observe(requireActivity(), { data ->
            if (data.isNotEmpty()){
                pendingPaymentAdapter.setResult(data)
                pendingPaymentAdapter.notifyDataSetChanged()

                pendingPaymentAdapter.setOnItemClickCallback(object: PaymentPendingAdapter.OnItemClickCallback{
                    override fun onItemCancelClicked(selectedTransaction: TransactionEntity?, position: Int?) {
                        if (position != null) {
                            AlertDialog.Builder(activity)
                                .setTitle("Remove Order from Transaction")
                                .setMessage("Do you really want to remove this order?")
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setPositiveButton(android.R.string.yes
                                ) { _, _ ->
                                    //delete order
                                    if (selectedTransaction != null) {
                                        viewModel.deleteOrderByTransactionId(selectedTransaction.transaction_id)
                                    }
                                    pendingPaymentAdapter.removeItem(position)
                                }
                                .setNegativeButton(android.R.string.cancel, null).show()
                        }
                    }
                })

                with(binding.rvPendingPayment) {
                    layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = pendingPaymentAdapter
                }

                showLoadingIndicator(false)
                showNoDataIndicator(false)
            }else{
                showLoadingIndicator(false)
                showNoDataIndicator(true)
            }
        })
    }

    private fun showLoadingIndicator(isLoading: Boolean){
        binding.progressBar.isVisible = isLoading
        binding.rvPendingPayment.isVisible = !isLoading
    }

    private fun showNoDataIndicator(isShow: Boolean){
        with(binding){
            noPendingIcon.isVisible = isShow
            noPendingTxt.isVisible = isShow

            rvPendingPayment.isVisible = !isShow
        }
    }
}