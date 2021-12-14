package com.path_studio.nike.ui.main.favorite

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.path_studio.nike.R
import com.path_studio.nike.data.source.local.entity.ProductEntity
import com.path_studio.nike.databinding.ItemGridProductRotateMdBinding
import com.path_studio.nike.ui.detailProduct.DetailProductActivity
import com.path_studio.nike.utils.Utils
import java.util.*

class FavoriteAdapter(private val viewModel: FavoriteViewModel): RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>(){
    private val listFav = ArrayList<ProductEntity>()

    @SuppressLint("NotifyDataSetChanged")
    fun setFavProducts(products: List<ProductEntity>?) {
        if (products == null) return
        this.listFav.clear()
        this.listFav.addAll(products)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemsBinding = ItemGridProductRotateMdBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(viewModel, itemsBinding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val product = listFav[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = listFav.size

    inner class FavoriteViewHolder(private val viewModel:FavoriteViewModel,
                                   private val binding: ItemGridProductRotateMdBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(product: ProductEntity) {
            with(binding) {
                rvProductName.text = product.name
                rvProductCategoryName.text = "${product.categoryName} Shoes"

                if(product.discount > 0){
                    val afterDiscount: Double = product.price - (product.price * product.discount / 100)
                    rvProductPriceBefore.text = "Rp ${Utils.getNumberThousandFormat(product.price)}"
                    rvProductPriceAfter.text = "Rp ${Utils.getNumberThousandFormat(afterDiscount)}"
                    rvProductPriceBefore.visibility = View.VISIBLE
                }else{
                    rvProductPriceAfter.text = "Rp ${Utils.getNumberThousandFormat(product.price)}"
                    rvProductPriceBefore.visibility = View.INVISIBLE
                }

                setFavoriteState(rvFavBtn, product.favorite)

                rvFavBtn.setOnClickListener {
                    viewModel.setFavorite(product)
                    setFavoriteState(rvFavBtn, !product.favorite)
                }

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailProductActivity::class.java)
                    intent.putExtra(DetailProductActivity.EXTRA_PRODUCT, product.productId)
                    intent.putExtra(DetailProductActivity.EXTRA_PRODUCT_POS, 0)
                    itemView.context.startActivity(intent)
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