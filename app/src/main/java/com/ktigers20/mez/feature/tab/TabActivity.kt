package com.ktigers20.mez.feature.tab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ktigers20.mez.R
import com.ktigers20.mez.databinding.ActivityTabBinding
import com.ktigers20.mez.domain.utils.toastShort
import com.ktigers20.mez.feature.main.MainFragment

class TabActivity : AppCompatActivity(), TabContract.View {
    private lateinit var mainBinding: ActivityTabBinding
    private lateinit var presenter: TabPresenter

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
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, MainFragment.newInstance()).commit()
    }

    val listener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.bottom_tab_main -> {
                this.toastShort("메인")
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_tab_search -> {
                this.toastShort("조회")
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_tab_phonebook -> {
                this.toastShort("비상연락망입니다.")
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_tab_settings -> {
                this.toastShort("세팅")
                return@OnNavigationItemSelectedListener true
            }
        }
        return@OnNavigationItemSelectedListener false
    }

}