package com.ktigers20.mez.feature.settings

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ktigers20.mez.R
import com.ktigers20.mez.databinding.FragmentSearchBinding
import com.ktigers20.mez.databinding.FragmentSettingBinding
import com.ktigers20.mez.feature.search.SearchFragment
import com.ktigers20.mez.feature.search.SearchPresenter


class SettingFragment : Fragment(), SettingContract.View {
    private lateinit var settingsBinding: FragmentSettingBinding
    private lateinit var presenter: SettingPresenter

    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = SettingPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUpDataBinding(inflater, container)
        initView()
        return settingsBinding.root
    }

    private fun setUpDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        settingsBinding = FragmentSettingBinding.inflate(inflater, container, false)
        settingsBinding.fragment = this
    }

    private fun initView() {

    }

}