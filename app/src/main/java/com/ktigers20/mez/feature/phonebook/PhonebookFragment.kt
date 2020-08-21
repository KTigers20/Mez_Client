package com.ktigers20.mez.feature.phonebook

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ktigers20.mez.R
import com.ktigers20.mez.databinding.FragmentMainBinding
import com.ktigers20.mez.databinding.FragmentPhonebookBinding

class PhonebookFragment : Fragment(), PhonebookContract.View {
    private lateinit var phonebookBinding: FragmentPhonebookBinding
    private lateinit var presenter: PhonebookPresenter

    companion object {
        @JvmStatic
        fun newInstance() = PhonebookFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = PhonebookPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUpDataBinding(inflater, container)
        initView()
        return phonebookBinding.root
    }

    private fun setUpDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        phonebookBinding = FragmentPhonebookBinding.inflate(inflater, container, false)
        phonebookBinding.fragment = this
    }

    private fun initView() {

    }
}