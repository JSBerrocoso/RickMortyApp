package com.jsbs87.android.rickmorty.app.presentation.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.jsbs87.android.rickmorty.app.presentation.platform.BaseActivity
import com.jsbs87.android.rickmorty.app.presentation.platform.BaseFragment
import com.jsbs87.android.rickmorty.app.presentation.util.LoadingHandler


fun BaseFragment.showLoading(msg: Int? = null) {
    if (activity is LoadingHandler) {
        (activity as LoadingHandler).showLoading()
    }
}

fun BaseFragment.hideLoading(success: Boolean? = null, msg: Int? = null) {
    if (activity is LoadingHandler) {
        (activity as LoadingHandler).hideLoading()
    }
}

fun BaseFragment.showDefaultError() {
    if ((activity is BaseActivity)) {
        (activity as BaseActivity).showGenericError()
    }
}

fun AppCompatActivity.replace(id: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(id, fragment)
        .commit()
}