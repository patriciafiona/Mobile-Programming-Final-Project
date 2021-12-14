package com.path_studio.nike.ui.main.shoppingCart

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.path_studio.nike.R
import com.path_studio.nike.data.source.local.entity.CartDetailEntity
import com.path_studio.nike.data.source.local.entity.CartEntity
import com.path_studio.nike.data.source.local.entity.ProductEntity
import com.path_studio.nike.databinding.ItemGridProductRotateMdBinding
import com.path_studio.nike.databinding.ItemRowShoppingCartBinding
import com.path_studio.nike.ui.detailProduct.DetailProductActivity
import com.path_studio.nike.ui.main.favorite.FavoriteAdapter
import com.path_studio.nike.ui.main.favorite.FavoriteViewModel
import com.path_studio.nike.utils.Utils
import java.util.ArrayList
import android.widget.Toast

import com.path_studio.nike.ui.main.MainActivity

import android.content.DialogInterface


class ShoppingCartAdapter(private val activity: Activity, private val viewModel: ShoppingCartViewModel): RecyclerView.Adapter<ShoppingCartAdapter.ItemViewHolder>(){
    private val listProduct = ArrayList<CartDetailEntity>()

    @SuppressLint("NotifyDataSetChanged")
    fun setProducts(products: List<CartDetailEntity>?) {
        if (products == null) return
        this.listProduct.clear()
        this.listProduct.addAll(products)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCartAdapter.ItemViewHolder {
        val itemsBinding = ItemRowShoppingCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(viewModel, itemsBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val product = listProduct[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = listProduct.size

    inner class ItemViewHolder(private val viewModel: ShoppingCartViewModel, private val binding: ItemRowShoppingCartBinding
    ): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(product: CartDetailEntity) {
            with(binding) {
                rvProductName.text = product.name
                rvProductCategoryName.text = "${product.categoryName} Shoes"
                rvProductPriceAfter.text = "Rp ${Utils.getNumberThousandFormat(product.price)}"
                rvProductColorName.text = product.colorDescription
                rvProductSize.text = "${product.size} Europe"
                rvProductColor.setCardBackgroundColor(Color.parseColor(product.colorCode))
                rvQuantityField.setText(product.quantity.toString())

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

                rvBtnDelete.setOnClickListener {
                    AlertDialog.Builder(activity)
                        .setTitle("Remove Product From Cart")
                        .setMessage("Do you really want to remove this product?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes
                        ) { _, _ ->
                            //delete from cart database
                            viewModel.deleteProductToCart(product.id)
                        }
                        .setNegativeButton(android.R.string.cancel, null).show()
                }

                rvBtnRemove.setOnClickListener {
                    val currentQuantity = rvQuantityField.text.toString().toInt()
                    val afterProcess:Int
                    if(currentQuantity > 1){
                        afterProcess = currentQuantity - 1
                        rvQuantityField.setText(afterProcess.toString())
                    }else{
                        afterProcess = 1
                        rvQuantityField.setText("1")
                    }

                    //update the database
                    val cartData = CartEntity(
                        product.id.toLong(),
                        product.productDetailId,
                        afterProcess,
                        product.size
                    )

                    viewModel.updateProductToCart(cartData)
                }

                rvBtnAdd.setOnClickListener {
                    val currentQuantity = rvQuantityField.text.toString().toInt()
                    val afterProcess:Int

                    if(currentQuantity < product.stock){
                        afterProcess = currentQuantity + 1
                        rvQuantityField.setText(afterProcess.toString())
                    }else{
                        afterProcess = product.stock
                        rvQuantityField.setText(afterProcess.toString())
                    }

                    //update the database
                    val cartData = CartEntity(
                        product.id.toLong(),
                        product.productDetailId,
                        afterProcess,
                        product.size
                    )

                    viewModel.updateProductToCart(cartData)
                }
            }
        }

    }
}