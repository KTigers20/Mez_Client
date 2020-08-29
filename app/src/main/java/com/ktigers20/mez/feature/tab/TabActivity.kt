package com.ktigers20.mez.feature.tab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ktigers20.mez.R
import com.ktigers20.mez.databinding.ActivityTabBinding
import com.ktigers20.mez.domain.globalconst.Consts
import com.ktigers20.mez.feature.main.MainFragment
import com.ktigers20.mez.feature.phonebook.PhonebookFragment
import com.ktigers20.mez.feature.search.SearchFragment
import com.ktigers20.mez.feature.settings.SettingFragment
import com.ktigers20.mez.userInfo.UserInfo

class TabActivity : AppCompatActivity(), TabContract.View {
    private lateinit var mainBinding: ActivityTabBinding
    private lateinit var presenter: TabPresenter
    lateinit var listener: BottomNavigationView.OnNavigationItemSelectedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPresenter()
        setUpDataBinding()
        initView()
    }

    private fun initPresenter() {
        presenter = TabPresenter(this)
        listener = presenter.listener
    }

    private fun setUpDataBinding() {
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_tab)
        mainBinding.activity = this
    }

    private fun initView() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, MainFragment.newInstance()).commit()
    }

    override fun loadFragment(index: Int) =
        when (index) {
            0 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, MainFragment.newInstance()).commit()
            }
            1 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, SearchFragment.newInstance()).commit()
            }
            2 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, PhonebookFragment.newInstance()).commit()
            }
            3 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, SettingFragment.newInstance()).commit()
            }
            else -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, MainFragment.newInstance()).commit()
            }
        }

}
