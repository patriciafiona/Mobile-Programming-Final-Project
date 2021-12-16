package com.path_studio.nike.ui.transactionHistory

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.path_studio.nike.data.NikeRepository
import com.path_studio.nike.data.source.local.entity.TransactionEntity

class TransactionHistoryViewModel(private val nikeRepository: NikeRepository): ViewModel() {
    fun getUserTransactionByStatus(userId: Int, status: Int):LiveData<List<TransactionEntity>> =
            nikeRepository.getUserTransactionByStatus(userId, status)
    fun deleteOrderByTransactionId(transaction_id: String): LiveData<String> =
        nikeRepository.deleteTransaction(transaction_id)
}