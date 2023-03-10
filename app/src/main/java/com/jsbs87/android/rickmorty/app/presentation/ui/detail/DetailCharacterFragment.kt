package com.jsbs87.android.rickmorty.app.presentation.ui.detail

import android.os.Bundle
import android.view.View
import com.jsbs87.android.rickmorty.app.R
import com.jsbs87.android.rickmorty.app.presentation.extension.failure
import com.jsbs87.android.rickmorty.app.presentation.extension.loadUrl
import com.jsbs87.android.rickmorty.app.presentation.extension.observe
import com.jsbs87.android.rickmorty.app.presentation.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_detail_character.*
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel

class DetailCharacterFragment : BaseFragment() {

    private val SPAN_COLUMN: Int = 2
    private val viewModel by lifecycleScope.viewModel<DetailCharacterViewModel>(this)

    companion object {
        const val EXTRA_EXTERNAL_ID = "extra:externalId"
        const val EXTRA_EMPTY_EXTERNAL_ID = 0


        fun newInstance(
            entityId: Int
        ): DetailCharacterFragment {
            return DetailCharacterFragment().apply {
                arguments = Bundle().apply { putInt(EXTRA_EXTERNAL_ID, entityId) }
            }
        }
    }

    override fun layoutId(): Int = R.layout.fragment_detail_character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.externalId.value = arguments?.getInt(EXTRA_EXTERNAL_ID)

        observe(viewModel.character, ::handleCharacter)
        observe(viewModel.loading, ::handleLoading)
        failure(viewModel.failure, ::showError)
    }

    private fun handleCharacter(character: com.jsbs87.android.rickmorty.app.domain.model.Character?) {
        character?.name?.let {
            if (hasCollapsingToolbarLayout()) {
                setTitleCollapsingToolbarLayout(it)
            }else{
                title_character_header.text =it
            }
        }
        character?.status?.let {
            status_character_header.text = resources.getString(R.string.status, it)
        }
        character?.species?.let {
            species_character_header.text = resources.getString(R.string.species, it)
        }
        character?.gender?.let {
            gender_character_header.text = resources.getString(R.string.gender, it)
        }

        character?.image?.let {
            image_character_detail.loadUrl(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadDetail()
    }

}