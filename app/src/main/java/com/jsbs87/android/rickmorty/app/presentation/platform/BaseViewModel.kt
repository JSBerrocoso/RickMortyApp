package com.jsbs87.android.rickmorty.app.presentation.platform

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.jsbs87.android.rickmorty.app.domain.exception.Failure

abstract class BaseViewModel : ViewModel() {

    var failure = SingleLiveEvent<Failure>()
    var loading = SingleLiveEvent<Triple<Boolean, Boolean?, Int?>>()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }

    protected fun showLoading(@StringRes msg: Int? = null) {
        loading.value = Triple(true, null, msg)
    }

    protected fun hideLoading(success: Boolean? = null, @StringRes msg: Int? = null) {
        loading.value = Triple(false, success, msg)
    }

    fun isLoading(): Boolean {
        return loading.value?.first ?: false
    }
}
