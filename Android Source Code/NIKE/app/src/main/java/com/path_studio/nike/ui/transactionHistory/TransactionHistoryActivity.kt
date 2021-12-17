package com.path_studio.nike.ui.transactionHistory

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.path_studio.nike.R
import com.path_studio.nike.databinding.ActivityTransactionHistoryBinding

class TransactionHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionHistoryBinding

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.pending_payment,
            R.string.packed,
            R.string.onDelivery,
            R.string.received
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set transaction tab
        val sectionsPagerAdapter = TransactionPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}