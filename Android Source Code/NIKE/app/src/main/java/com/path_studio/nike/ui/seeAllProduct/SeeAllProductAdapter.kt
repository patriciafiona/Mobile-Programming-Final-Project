package com.path_studio.nike.ui.seeAllProduct

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.path_studio.nike.R
import com.path_studio.nike.data.source.local.entity.ProductEntity
import com.path_studio.nike.databinding.ItemGridProductRotateMdBinding
import com.path_studio.nike.ui.main.favorite.FavoriteAdapter
import com.path_studio.nike.ui.main.favorite.FavoriteViewModel
import com.path_studio.nike.utils.Utils
import java.util.ArrayList

class SeeAllProductAdapter(private val viewModel: SeeAllViewModel): RecyclerView.Adapter<SeeAllProductAdapter.ItemViewHolder>() {
    private val listFav = ArrayList<ProductEntity>()

    @SuppressLint("NotifyDataSetChanged")
    fun setProducts(products: List<ProductEntity>?) {
        if (products == null) return
        this.listFav.clear()
        this.listFav.addAll(products)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val itemsBinding = ItemGridProductRotateMdBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(viewModel, itemsBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val product = listFav[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = listFav.size

    inner class ItemViewHolder(
        private val viewModel: SeeAllViewModel,
        private val binding: ItemGridProductRotateMdBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(product: ProductEntity) {
            with(binding) {
                rvProductName.text = product.name
                rvProductCategoryName.text = "${product.categoryName} Shoes"
                rvProductPriceAfter.text = "Rp ${Utils.getNumberThousandFormat(product.price)}"

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

        private fun setFavoriteState(btn: ImageButton, state: Boolean) {
            if (state) {
                btn.setImageResource(R.drawable.ic_baseline_favorite_red)
            } else {
                btn.setImageResource(R.drawable.ic_baseline_favorite_border_black)
            }
        }

    }
}