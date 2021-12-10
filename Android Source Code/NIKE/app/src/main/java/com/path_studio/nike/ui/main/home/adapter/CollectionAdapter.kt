package com.path_studio.nike.ui.main.home.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.path_studio.nike.R
import com.path_studio.nike.data.source.local.entity.ProductEntity
import com.path_studio.nike.databinding.ItemRowProductRotateXlBinding
import com.path_studio.nike.utils.Utils.getNumberThousandFormat

class CollectionAdapter: PagedListAdapter<ProductEntity, CollectionAdapter.ItemViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductEntity>() {
            override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemsBinding = ItemRowProductRotateXlBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(itemsBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    class ItemViewHolder(private val binding: ItemRowProductRotateXlBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(product: ProductEntity) {
            with(binding) {
                rvProductName.text = product.name
                rvProductCategoryName.text = "${product.categoryName} Shoes"
                rvProductPriceAfter.text = "Rp ${getNumberThousandFormat(product.price)}"

                rvFavBtn.setOnClickListener {
                    //logic
                }

                itemView.setOnClickListener {
                    // logic
                }

                val posterURL = "http://10.0.2.2:8080/NIKE/assets/images/products/${product.photo01}"
                Glide.with(itemView.context)
                    .load(posterURL)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error_img))
                    .into(rvProductImg)
            }
        }
    }
}