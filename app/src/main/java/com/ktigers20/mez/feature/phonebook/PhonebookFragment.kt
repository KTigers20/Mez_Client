package com.ktigers20.mez.feature.phonebook

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.widget.RxTextView
import com.ktigers20.mez.R
import com.ktigers20.mez.data.entity.PhoneBookInfo
import com.ktigers20.mez.databinding.FragmentMainBinding
import com.ktigers20.mez.databinding.FragmentPhonebookBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import org.koin.android.ext.android.get
import java.util.concurrent.TimeUnit

class PhonebookFragment : Fragment(), PhonebookContract.View {
    private lateinit var phonebookBinding: FragmentPhonebookBinding
    private lateinit var presenter: PhonebookPresenter
    private lateinit var mPhoneBookInfoList: ArrayList<PhoneBookInfo>

    private lateinit var mPhoneBookAdapter: PhonebookAdapter
    private val compositeDisposable = CompositeDisposable()

    companion object {
        @JvmStatic
        fun newInstance() = PhonebookFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = PhonebookPresenter(this, get())
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
        //1초 미만 입력시 서버 통신
        RxTextView.textChanges(phonebookBinding.searchEditText)
            .debounce(1000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                presenter.getPhoneBookInfoByName(it.toString(),0)
            }.addTo(compositeDisposable)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }


    override fun setPhoneBookList(phoneBookList: ArrayList<PhoneBookInfo>) {
        TODO("Not yet implemented")
    }

    override fun setPhoneBookPageIsEnd(isEnd: Boolean) {
        TODO("Not yet implemented")
    }
}