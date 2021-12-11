package com.path_studio.nike.ui.main.home.adapter

import android.annotation.SuppressLint
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
import com.path_studio.nike.ui.main.home.HomeViewModel
import com.path_studio.nike.utils.Utils.getNumberThousandFormat

class ProductRotateXLAdapter(private val homeViewModel: HomeViewModel): PagedListAdapter<ProductEntity, ProductRotateXLAdapter.ItemViewHolder>(DIFF_CALLBACK) {
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
        return ItemViewHolder(homeViewModel, itemsBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val product = getItem(position)
        if (product != null) {
            holder.bind(product)
        }
    }

    class ItemViewHolder(private val viewModel: HomeViewModel, private val binding: ItemRowProductRotateXlBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(product: ProductEntity) {
            with(binding) {
                rvProductName.text = product.name
                rvProductCategoryName.text = "${product.categoryName} Shoes"
                rvProductPriceAfter.text = "Rp ${getNumberThousandFormat(product.price)}"

                setFavoriteState(rvFavBtn, product.favorite)

                rvFavBtn.setOnClickListener {
                    viewModel.setFavorite(product)
                    setFavoriteState(rvFavBtn, !product.favorite)
                }

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

        private fun setFavoriteState(btn:ImageButton, state: Boolean){
            if (state) {
                btn.setImageResource(R.drawable.ic_baseline_favorite_red)
            } else {
                btn.setImageResource(R.drawable.ic_baseline_favorite_border_black)
            }
        }
    }
}