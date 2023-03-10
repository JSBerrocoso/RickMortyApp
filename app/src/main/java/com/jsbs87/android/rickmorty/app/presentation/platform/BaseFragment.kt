package com.jsbs87.android.rickmorty.app.presentation.platform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.jsbs87.android.rickmorty.app.R
import com.jsbs87.android.rickmorty.app.domain.exception.Failure
import com.jsbs87.android.rickmorty.app.presentation.extension.hideLoading
import com.jsbs87.android.rickmorty.app.presentation.extension.showLoading
import com.jsbs87.android.rickmorty.app.presentation.util.TouchableCharacterView

abstract class BaseFragment : Fragment() {

    abstract fun layoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(layoutId(), container, false)

    open fun handleLoading(loading: Triple<Boolean, Boolean?, Int?>?) {
        if (loading?.first == true) {
            showLoading(loading.third)
        } else {
            hideLoading(loading?.second, loading?.third)
        }
    }

    open fun showError(failure: Failure?) {
        when (failure) {
            is Failure.ServerError -> {
                showSnackBar(getString(R.string.server_error))
            }
            is Failure.NetworkConnection -> {
                showSnackBar( getString(R.string.network_error))
            }
            else -> (activity as? BaseActivity)?.showError(failure)
        }
    }

    fun setTitleCollapsingToolbarLayout(title: String) {
        when (activity) {
            is AppCompatActivity -> (activity as AppCompatActivity).findViewById<CollapsingToolbarLayout>(
                R.id.toolbar_layout
            ).title = title
//                (activity as AppCompatActivity).supportActionBar?.setTitle(title)
        }
    }

    fun setTitleToolbar(title: String) {
        when (activity) {
            is AppCompatActivity -> (activity as AppCompatActivity).supportActionBar?.setTitle(title)
        }
    }

    fun hasCollapsingToolbarLayout(): Boolean {
        val toolbar =
            (activity as AppCompatActivity).findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        return toolbar != null
    }

    fun showSnackBar(text: String) {
        when (activity) {
            is BaseActivity -> (activity as BaseActivity).showSnackBar(text)
        }
    }

    internal fun onClickCharacter(character: com.jsbs87.android.rickmorty.app.domain.model.Character) {
        if (activity is TouchableCharacterView) {
            (activity as TouchableCharacterView).onClickCharacter(character)
        }
    }
}