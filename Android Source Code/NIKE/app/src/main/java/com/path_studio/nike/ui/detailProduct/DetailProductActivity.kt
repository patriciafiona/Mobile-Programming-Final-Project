package com.path_studio.nike.ui.detailProduct

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.path_studio.nike.R
import com.path_studio.nike.databinding.ActivityDetailProductBinding
import com.path_studio.nike.ui.detailProduct.bottomSheet.OnBottomSheetCallbacks
import com.path_studio.nike.ui.detailProduct.imagePreview.ImageViewActivity
import com.path_studio.nike.viewModel.ViewModelFactory
import com.path_studio.nike.vo.Status
import java.util.*

class DetailProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailProductBinding
    private var listener: OnBottomSheetCallbacks? = null

    private var nCurrentPage = 0
    private lateinit var mPager: ViewPager
    private lateinit var mDotLayout: LinearLayout
    private lateinit var mDosts: Array<TextView>

    private var nCurrentProductDetailPos = 0

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
            nCurrentProductDetailPos = extras.getInt(EXTRA_PRODUCT_POS)
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
                                    topCategoryText.text = "${productData[nCurrentProductDetailPos]?.categoryName}'s Shoes"
                                    productData[nCurrentProductDetailPos]?.let { setFavoriteStateColor(it.favorite) }

                                    btnFavorite.setOnClickListener {
                                        //clear the color btn
                                        val detailFragment =
                                            supportFragmentManager.findFragmentById(R.id.productDetailContainer) as DetailProductFragment?
                                        val colorView = detailFragment?.view?.findViewById<GridView>(R.id.color_container)
                                        colorView?.removeAllViews()

                                        productData[nCurrentProductDetailPos]?.let { it1 ->
                                            viewModel.setFavorite(
                                                it1
                                            )
                                        }
                                        setFavoriteStateColor(!productData[nCurrentProductDetailPos]?.favorite!!)
                                    }

                                    // The pager adapter, which provides the pages to the view pager widget.
                                    var imagesArr = arrayListOf<String?>()
                                    if (!productData[nCurrentProductDetailPos]?.photo05.isNullOrBlank()){
                                        imagesArr = arrayListOf(
                                            productData[nCurrentProductDetailPos]?.photo01,
                                            productData[nCurrentProductDetailPos]?.photo02,
                                            productData[nCurrentProductDetailPos]?.photo03,
                                            productData[nCurrentProductDetailPos]?.photo04,
                                            productData[nCurrentProductDetailPos]?.photo05
                                        )
                                    }else if(!productData[nCurrentProductDetailPos]?.photo04.isNullOrBlank()){
                                        imagesArr = arrayListOf(
                                            productData[nCurrentProductDetailPos]?.photo01,
                                            productData[nCurrentProductDetailPos]?.photo02,
                                            productData[nCurrentProductDetailPos]?.photo03,
                                            productData[nCurrentProductDetailPos]?.photo04
                                        )
                                    }else if(!productData[nCurrentProductDetailPos]?.photo03.isNullOrBlank()){
                                        imagesArr = arrayListOf(
                                            productData[nCurrentProductDetailPos]?.photo01,
                                            productData[nCurrentProductDetailPos]?.photo02,
                                            productData[nCurrentProductDetailPos]?.photo03
                                        )
                                    }else if(!productData[nCurrentProductDetailPos]?.photo02.isNullOrBlank()){
                                        imagesArr = arrayListOf(
                                            productData[nCurrentProductDetailPos]?.photo01,
                                            productData[nCurrentProductDetailPos]?.photo02
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

            img.setOnClickListener {
                val intent = Intent(this@DetailProductActivity, ImageViewActivity::class.java)
                intent.putExtra("img_url", posterURL)
                startActivity(intent)
            }

            Objects.requireNonNull(container).addView(itemView)
            return itemView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View?)
        }
    }

}