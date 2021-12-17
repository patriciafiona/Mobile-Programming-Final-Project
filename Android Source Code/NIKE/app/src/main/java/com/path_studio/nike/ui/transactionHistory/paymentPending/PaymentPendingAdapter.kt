package com.path_studio.nike.ui.transactionHistory.paymentPending

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.path_studio.nike.data.source.local.entity.TransactionEntity
import com.path_studio.nike.databinding.ItemRowTransactionBinding
import com.path_studio.nike.ui.transactionHistory.TransactionHistoryViewModel
import com.path_studio.nike.utils.Utils

class PaymentPendingAdapter(private val viewModel: TransactionHistoryViewModel, private val activity: Activity): RecyclerView.Adapter<PaymentPendingAdapter.ItemHolder>() {
    private var listResult = ArrayList<TransactionEntity>()

    @SuppressLint("NotifyDataSetChanged")
    fun setResult(res: List<TransactionEntity>) {
        this.listResult.clear()
        this.listResult.addAll(res)
        this.notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        this.listResult.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listResult.size)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemBinding = ItemRowTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(itemBinding, activity, viewModel)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(listResult[position])
    }

    override fun getItemCount(): Int = this.listResult.size


    inner class ItemHolder(private val binding: ItemRowTransactionBinding, private val activity: Activity,
                     private val viewModel: TransactionHistoryViewModel) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(transaction: TransactionEntity) {
            with(binding) {
                transactionId.text = transaction.transaction_id
                quantityTransaction.text = "${transaction.totalAllProduct} products"
                totalPrice.text = "Rp ${Utils.getNumberThousandFormat(transaction.totalAllPrice)}"

                itemView.setOnClickListener {
                    //logic
                }

                btnCancel.setOnClickListener {
                    onItemClickCallback.onItemCancelClicked(transaction, bindingAdapterPosition)
                }

                btnPay.setOnClickListener {
                    Toast.makeText(
                        activity,
                        "Service Not Available Now",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemCancelClicked(selectedTransaction: TransactionEntity?, position: Int?)
    }
}