package com.jsbs87.android.rickmorty.app.presentation.ui.characters

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.jsbs87.android.rickmorty.app.R
import com.jsbs87.android.rickmorty.app.presentation.adapter.RickyMortyChaptersAdapter
import com.jsbs87.android.rickmorty.app.presentation.extension.failure
import com.jsbs87.android.rickmorty.app.presentation.extension.observe
import com.jsbs87.android.rickmorty.app.presentation.platform.BaseFragment
import com.jsbs87.android.rickmorty.app.presentation.util.SearcheableView
import kotlinx.android.synthetic.main.fragment_character.*
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel

class CharactersFragment : BaseFragment(), SearcheableView {

    private val viewModel by lifecycleScope.viewModel<CharactersCharactersViewModel>(this)

    private val characterAdapter =
        RickyMortyChaptersAdapter { character ->
            onClickCharacter(character)
        }

    override fun layoutId(): Int = R.layout.fragment_character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe(viewModel.films, ::handleCharacters)
        observe(viewModel.filteredElements, ::handleCharacters)
        observe(viewModel.loading, ::handleLoading)
        failure(viewModel.failure, ::showError)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.apply {
            layoutManager = GridLayoutManager(activity, 3)
            adapter = characterAdapter
        }
        viewModel.loadCharacters()    }

    private fun handleCharacters(characters: List<com.jsbs87.android.rickmorty.app.domain.model.Character>?) {
        if (characters?.isEmpty()!!) {
            showEmptyViews()
        } else {
            showCharactersViews()
            characterAdapter.addAll(characters)
        }
    }

    private fun showCharactersViews() {
        empty_characters_container.visibility = View.GONE
        recycler_view.visibility = View.VISIBLE
    }

    private fun showEmptyViews() {
        empty_characters_container.visibility = View.VISIBLE
        recycler_view.visibility = View.GONE
    }

    override fun search(newText: String) {
        viewModel.searchText = newText
    }
}