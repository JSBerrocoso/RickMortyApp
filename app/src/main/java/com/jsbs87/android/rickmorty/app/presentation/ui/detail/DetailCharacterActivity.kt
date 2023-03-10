package com.jsbs87.android.rickmorty.app.presentation.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.jsbs87.android.rickmorty.app.R
import com.jsbs87.android.rickmorty.app.presentation.extension.replace
import com.jsbs87.android.rickmorty.app.presentation.platform.BaseActivity
import kotlinx.android.synthetic.main.activity_detail_character.*

class DetailCharacterActivity : BaseActivity() {

    companion object {
        fun openActivity(context: Context, externalId: Int) {
            val intent = Intent(context, DetailCharacterActivity::class.java)
            intent.putExtra(DetailCharacterFragment.EXTRA_EXTERNAL_ID, externalId)
            context.startActivity(intent)
        }
    }

    override fun layoutId(): Int = R.layout.activity_detail_character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        setOnBackButtonToolbar(true, toolbar)

        if (intent.hasExtra(DetailCharacterFragment.EXTRA_EXTERNAL_ID)) {
            replace(
                R.id.container_detail,
                DetailCharacterFragment.newInstance(intent.getIntExtra(DetailCharacterFragment.EXTRA_EXTERNAL_ID, DetailCharacterFragment.EXTRA_EMPTY_EXTERNAL_ID))
            )
        }
    }

}