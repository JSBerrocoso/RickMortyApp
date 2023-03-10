package com.jsbs87.android.rickmorty.app.presentation.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.jsbs87.android.rickmorty.app.R
import com.jsbs87.android.rickmorty.app.presentation.extension.replace
import com.jsbs87.android.rickmorty.app.presentation.platform.BaseActivity
import com.jsbs87.android.rickmorty.app.presentation.ui.detail.DetailCharacterActivity
import com.jsbs87.android.rickmorty.app.presentation.ui.detail.DetailCharacterFragment
import com.jsbs87.android.rickmorty.app.presentation.util.SearcheableView
import com.jsbs87.android.rickmorty.app.presentation.util.TouchableCharacterView
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : BaseActivity(), TouchableCharacterView {

    override fun layoutId(): Int = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(
            navController,
            AppBarConfiguration(setOf(R.id.navigation_films))
        )
        nav_view.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        val searchMenuItem = menu?.findItem(R.id.search_view)
        val searchView = searchMenuItem?.actionView as SearchView
        configureSearch(searchView, searchMenuItem)
        return true
    }

    private fun configureSearch(
        searchView: SearchView,
        searchMenuItem: MenuItem
    ) {
        searchView.setOnQueryTextFocusChangeListener { _, queryTextFocused ->
            if (!queryTextFocused) {
                searchMenuItem.collapseActionView()
                searchView.setQuery("", false)
            }
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    val fragment = getForegroundFragment()
                    if (fragment != null && fragment is SearcheableView) {
                        fragment.search(newText)
                    }
                }
                return true
            }
        })
    }

    fun getForegroundFragment(): Fragment? {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        return if (navHostFragment == null) {
            null
        } else {
            navHostFragment.childFragmentManager.fragments[0]
        }
    }

    override fun onClickCharacter(character: com.jsbs87.android.rickmorty.app.domain.model.Character) {
        if (container_detail_character_land != null) {
            replace(
                R.id.container_detail_character_land,
                DetailCharacterFragment.newInstance(character.id)
            )
        } else {
            DetailCharacterActivity.openActivity(this, character.id)
        }
    }

}