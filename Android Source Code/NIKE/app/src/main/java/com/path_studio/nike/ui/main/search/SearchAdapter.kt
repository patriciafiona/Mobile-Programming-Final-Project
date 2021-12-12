package com.path_studio.nike.ui.main.search

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.path_studio.nike.R
import com.path_studio.nike.data.source.local.entity.ProductEntity
import com.path_studio.nike.databinding.ItemRowProductRotateXlBinding
import com.path_studio.nike.databinding.ItemRowProductWideBinding
import com.path_studio.nike.ui.main.home.HomeViewModel
import com.path_studio.nike.ui.main.home.adapter.ProductRotateXLAdapter
import com.path_studio.nike.utils.Utils

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.SuggestionHolder>() {
    private var listResult = ArrayList<ProductEntity>()

    fun setResult(res: List<ProductEntity>) {
        this.listResult.clear()
        this.listResult.addAll(res)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestionHolder {
        val itemSearchBinding = ItemRowProductWideBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuggestionHolder(itemSearchBinding)
    }

    override fun onBindViewHolder(holder: SuggestionHolder, position: Int) {
        holder.bind(listResult[position])
    }

    override fun getItemCount(): Int = this.listResult.size


    class SuggestionHolder(private val binding: ItemRowProductWideBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(product: ProductEntity) {
            with(binding) {
                rvProductName.text = product.name
                rvProductCategoryName.text = "${product.categoryName} Shoes"
                rvProductPrice.text = "Rp ${Utils.getNumberThousandFormat(product.price)}"

                itemView.setOnClickListener {
                    // logic
                }

                val posterURL =
                    "http://10.0.2.2:8080/NIKE/assets/images/products/${product.photo01}"
                Glide.with(itemView.context)
                    .load(posterURL)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error_img)
                    )
                    .into(rvProductImg)
            }
        }
    }
}