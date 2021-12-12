package com.path_studio.nike.ui.detailProduct

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.path_studio.nike.R
import com.path_studio.nike.data.source.local.entity.ProductEntity
import com.path_studio.nike.data.source.remote.response.ProductResponseItem
import com.path_studio.nike.databinding.ActivityDetailProductBinding
import com.path_studio.nike.databinding.ActivityMainBinding
import com.path_studio.nike.ui.detailProduct.bottomSheet.OnBottomSheetCallbacks
import com.path_studio.nike.ui.main.home.HomeViewModel
import com.path_studio.nike.ui.main.home.adapter.ProductRotateXLAdapter
import com.path_studio.nike.viewModel.ViewModelFactory
import com.path_studio.nike.vo.Status
import java.util.*
import kotlin.collections.ArrayList

class DetailProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailProductBinding
    private var listener: OnBottomSheetCallbacks? = null

    private var nCurrentPage = 0
    private lateinit var mPager: ViewPager
    private lateinit var mDotLayout: LinearLayout
    private lateinit var mDosts: Array<TextView>

    companion object {
        const val EXTRA_PRODUCT = "extra_product"
        const val EXTRA_PRODUCT_POS = "extra_product_pos"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set backdrop
        configureBackdrop()

        //set slideshow
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = binding.imageSlideshow
        mDotLayout = binding.dotsLayoutHomeBanner

        addDotsIndicator(0)
        mPager.addOnPageChangeListener(viewListener)

        //get product ID and get the data
        val extras = intent.extras

        if (extras != null) {
            val productId = extras.getLong(EXTRA_PRODUCT)
            if (productId != 0L) {
                val factory = ViewModelFactory.getInstance(this)
                val viewModel = ViewModelProvider(this, factory)[DetailProductViewModel::class.java]

                viewModel.getProductsByID(productId.toInt()).observe(this, { products ->
                    if (products != null) {
                        when (products.status) {
                            Status.LOADING -> binding.skeleton.showSkeleton()
                            Status.SUCCESS -> {
                                binding.skeleton.showOriginal()

                                //show data to UI
                                val productData = products.data!!
                                with(binding) {
                                    topCategoryText.text = "${productData[0]?.categoryName}'s Shoes"
                                    productData[0]?.let { setFavoriteStateColor(it.favorite) }

                                    // The pager adapter, which provides the pages to the view pager widget.
                                    var imagesArr = arrayListOf<String?>()
                                    if (!productData[0]?.photo05.isNullOrBlank()){
                                        imagesArr = arrayListOf(
                                            productData[0]?.photo01,
                                            productData[0]?.photo02,
                                            productData[0]?.photo03,
                                            productData[0]?.photo04,
                                            productData[0]?.photo05
                                        )
                                    }else if(!productData[0]?.photo04.isNullOrBlank()){
                                        imagesArr = arrayListOf(
                                            productData[0]?.photo01,
                                            productData[0]?.photo02,
                                            productData[0]?.photo03,
                                            productData[0]?.photo04
                                        )
                                    }else if(!productData[0]?.photo03.isNullOrBlank()){
                                        imagesArr = arrayListOf(
                                            productData[0]?.photo01,
                                            productData[0]?.photo02,
                                            productData[0]?.photo03
                                        )
                                    }else if(!productData[0]?.photo02.isNullOrBlank()){
                                        imagesArr = arrayListOf(
                                            productData[0]?.photo01,
                                            productData[0]?.photo02
                                        )
                                    }

                                    val pagerAdapter = ProductSlidePagerAdapter(context=this@DetailProductActivity, images=imagesArr)
                                    mPager.adapter = pagerAdapter
                                }
                            }
                            Status.ERROR -> {
                                binding.skeleton.showOriginal()
                                Toast.makeText(this, "Something Error", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
        }

        binding.btnClose.setOnClickListener {
            onBackPressed()
        }
    }

    private var viewListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(i: Int, v: Float, i1: Int) {}

        override fun onPageSelected(i: Int) {
            addDotsIndicator(i)
            nCurrentPage = i
        }

        override fun onPageScrollStateChanged(i: Int) {}
    }

    private fun setFavoriteStateColor(isFav: Boolean){
        with(binding){
            if(isFav){
                btnFavorite.setImageResource(R.drawable.ic_baseline_favorite_red)
            }else{
                btnFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_black)
            }
        }
    }

    fun setOnBottomSheetCallbacks(onBottomSheetCallbacks: OnBottomSheetCallbacks) {
        this.listener = onBottomSheetCallbacks
    }

    fun closeBottomSheet() {
        mBottomSheetBehavior?.state = BottomSheetBehavior.STATE_HALF_EXPANDED
    }

    fun openBottomSheet() {
        mBottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private var mBottomSheetBehavior: BottomSheetBehavior<View?>? = null

    private fun configureBackdrop() {
        val fragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)

        fragment?.view?.let {
            BottomSheetBehavior.from(it).let { bs ->
                bs.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onSlide(bottomSheet: View, slideOffset: Float) {}

                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        listener?.onStateChanged(bottomSheet, newState)
                    }
                })

                bs.isFitToContents = false
                bs.expandedOffset = 200
                bs.state = BottomSheetBehavior.STATE_HALF_EXPANDED

                mBottomSheetBehavior = bs
            }
        }
    }

    private fun addDotsIndicator(position: Int) {
        mDotLayout.removeAllViews()

        mDosts = arrayOf(TextView(this), TextView(this), TextView(this))
        for (i in mDosts.indices) {
            mDosts[i] = TextView(this)
            mDosts[i].text = Html.fromHtml("&#8226;", 0)
            mDosts[i].textSize = 35f
            mDosts[i].setTextColor(ContextCompat.getColor(this, R.color.dim_gray_1))
            mDotLayout.addView(mDosts[i])
        }
        mDosts[position].setTextColor(ContextCompat.getColor(this, R.color.black))
    }

    private inner class ProductSlidePagerAdapter(val context: Context, val images:ArrayList<String?>): PagerAdapter() {
        private val mLayoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getCount(): Int {
            return images.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val itemView:View = mLayoutInflater.inflate(R.layout.item_slideshow_image, container, false)

            val img: ImageView = itemView.findViewById(R.id.slider_img)
            val posterURL =
                "http://10.0.2.2:8080/NIKE/assets/images/products/${images[position]}"
            Glide.with(itemView.context)
                .load(posterURL)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error_img)
                )
                .into(img)

            Objects.requireNonNull(container).addView(itemView)
            return itemView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View?)
        }
    }

}