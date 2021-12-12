package com.path_studio.nike.ui.detailProduct.bottomSheet

import android.view.View

interface OnBottomSheetCallbacks {
    fun onStateChanged(bottomSheet: View, newState: Int)
}