package com.ktigers20.mez.feature.tab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ktigers20.mez.R
import com.ktigers20.mez.databinding.ActivityTabBinding
import com.ktigers20.mez.feature.main.MainFragment
import com.ktigers20.mez.feature.phonebook.PhonebookFragment
import com.ktigers20.mez.feature.search.SearchFragment
import com.ktigers20.mez.feature.settings.SettingFragment

class TabActivity : AppCompatActivity(), TabContract.View {
    private lateinit var mainBinding: ActivityTabBinding
    private lateinit var presenter: TabPresenter
    private var fragments: ArrayList<Fragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPresenter()
        setUpDataBinding()
        initView()
    }

    private fun initPresenter() {
        presenter = TabPresenter(this)
    }

    private fun setUpDataBinding() {
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_tab)
        mainBinding.activity = this
    }

    private fun initView() {
        fragments.apply {
            addAll(
                listOf(
                    MainFragment.newInstance(),
                    SearchFragment.newInstance(),
                    PhonebookFragment.newInstance(),
                    SettingFragment.newInstance()
                )
            )
        }.map {
            it.apply {
                supportFragmentManager.beginTransaction()
                    .add(R.id.main_container, it).commit()
            }
        }.run {
            loadFragment(0)
        }
    }

    val listener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.bottom_tab_main -> {
                loadFragment(0)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_tab_search -> {
                loadFragment(1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_tab_phonebook -> {
                loadFragment(2)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_tab_settings -> {
                loadFragment(3)
                return@OnNavigationItemSelectedListener true
            }
        }
        return@OnNavigationItemSelectedListener false
    }

    private fun loadFragment(index: Int) =
        fragments.map {
            it.apply { supportFragmentManager.beginTransaction().hide(this).commit() }
        }[index].also {
            supportFragmentManager.beginTransaction().show(it).commit()
        }
}
