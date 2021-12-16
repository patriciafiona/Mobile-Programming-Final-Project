package com.path_studio.nike.ui.transactionHistory

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.path_studio.nike.ui.main.home.HomeFragment
import com.path_studio.nike.ui.transactionHistory.onDelivery.OnDeliveryFragment
import com.path_studio.nike.ui.transactionHistory.packed.PackedFragment
import com.path_studio.nike.ui.transactionHistory.paymentPending.PaymentPendingFragment
import com.path_studio.nike.ui.transactionHistory.received.ReceivedFragment

class TransactionPagerAdapter (activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = PaymentPendingFragment()
            1 -> fragment = PackedFragment()
            2 -> fragment = OnDeliveryFragment()
            3 -> fragment = ReceivedFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 4
    }
}