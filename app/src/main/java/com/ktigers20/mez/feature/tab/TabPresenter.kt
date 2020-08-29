package com.ktigers20.mez.feature.tab

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ktigers20.mez.R

class TabPresenter(override val view: TabContract.View) : TabContract.Presenter {
    override val listener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.bottom_tab_main -> {
                view.loadFragment(0)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_tab_search -> {
                view.loadFragment(1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_tab_phonebook -> {
                view.loadFragment(2)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_tab_settings -> {
                view.loadFragment(3)
                return@OnNavigationItemSelectedListener true
            }
        }
        return@OnNavigationItemSelectedListener false
    }
}
