package com.path_studio.nike.ui.welcome

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.path_studio.nike.R
import com.path_studio.nike.databinding.ActivityWelcomeBinding
import com.path_studio.nike.ui.main.MainActivity
import java.util.*


class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityWelcomeBinding

    private val imagesArr = arrayListOf(
        R.drawable.welcome_banner_01,
        R.drawable.welcome_banner_02,
        R.drawable.welcome_banner_03
    )
    private val textsArr = arrayListOf(
        "Boost \nyour style \nsense",
        "Keep \nRunning",
        "Get 20% \nDiscount \nNew Arrivals"
    )
    private var nCurrentPage = 0
    private lateinit var mPager: ViewPager
    private lateinit var mDotLayout: LinearLayout
    private lateinit var mDosts: Array<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = binding.welcomeSlideshow
        mDotLayout = binding.dotsLayoutHomeBanner

        addDotsIndicator(0)
        mPager.addOnPageChangeListener(viewListener)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ScreenSlidePagerAdapter(context=this, images=imagesArr, texts=textsArr)
        mPager.adapter = pagerAdapter

        with(binding){
            btnPrev.setOnClickListener {
                mPager.currentItem = nCurrentPage-1
            }
            btnGetStarted.setOnClickListener {
                val intent = Intent(this@WelcomeActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            btnNext.setOnClickListener {
                mPager.currentItem = nCurrentPage+1
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

    private var viewListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(i: Int, v: Float, i1: Int) {}

        override fun onPageSelected(i: Int) {
            addDotsIndicator(i)
            nCurrentPage = i

            //show or hide prev and next button
            with(binding){
                when (i) {
                    0 -> {
                        btnPrev.visibility = View.INVISIBLE
                        btnNext.visibility = View.VISIBLE
                    }
                    2 -> {
                        btnPrev.visibility = View.VISIBLE
                        btnNext.visibility = View.INVISIBLE
                    }
                    else -> {
                        btnPrev.visibility = View.VISIBLE
                        btnNext.visibility = View.VISIBLE
                    }
                }
            }

        }

        override fun onPageScrollStateChanged(i: Int) {}
    }

    private inner class ScreenSlidePagerAdapter(val context: Context, val images:ArrayList<Int>, val texts:ArrayList<String>): PagerAdapter() {
        private val mLayoutInflater:LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getCount(): Int {
            return images.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val itemView:View = mLayoutInflater.inflate(R.layout.item_welcome_page, container, false)

            val img:ImageView = itemView.findViewById(R.id.slider_img)
            img.setImageResource(images[position])

            val txtLeft:TextView = itemView.findViewById(R.id.slider_text_left)
            val txtRight:TextView = itemView.findViewById(R.id.slider_text_right)
            if(position != 1){
                txtLeft.text = texts[position]

                txtLeft.visibility = View.VISIBLE
                txtRight.visibility = View.GONE
            }else{
                txtRight.text = texts[position]

                txtLeft.visibility = View.GONE
                txtRight.visibility = View.VISIBLE
            }

            Objects.requireNonNull(container).addView(itemView)
            return itemView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View?)
        }
    }
}