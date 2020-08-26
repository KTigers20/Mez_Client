package com.ktigers20.mez.feature.phonebook

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.ktigers20.mez.data.entity.PhoneBookInfo
import com.ktigers20.mez.databinding.FragmentPhonebookBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import org.koin.android.ext.android.get
import java.util.concurrent.TimeUnit

class PhonebookFragment : Fragment(), PhonebookContract.View {
    private val compositeDisposable = CompositeDisposable()

    private lateinit var phonebookBinding: FragmentPhonebookBinding
    private lateinit var presenter: PhonebookPresenter
    private var mPhoneBookInfoList = ArrayList<PhoneBookInfo>()

    private lateinit var mPhoneBookAdapter: PhonebookAdapter
    private var phoneBookPage: Long = 0
    private var phoneBookContentSize = 0
    private var phoneBookIsEnd = false

    val phoneBookRecyclerViewScrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy >= 0 && phoneBookContentSize > 0) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    if (layoutManager.findLastCompletelyVisibleItemPosition() >= phoneBookContentSize - 1 && !phoneBookIsEnd) {
                        callPhoneBookApi(phonebookBinding.searchEditText.text.toString())
                    }
                }
            }
        }

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

    override fun onResume() {
        super.onResume()
        initPageInfo()
    }

    private fun setUpDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        phonebookBinding = FragmentPhonebookBinding.inflate(inflater, container, false)
        phonebookBinding.fragment = this
    }

    override fun setPhoneBookList(phoneBookList: ArrayList<PhoneBookInfo>) {
        mPhoneBookInfoList.addAll(phoneBookList)
        //phoneBookContentSize += phoneBookList.size
        phoneBookContentSize = mPhoneBookInfoList.size
        if (phoneBookContentSize != 0) {
            if (phoneBookPage < 1) {
                mPhoneBookAdapter = PhonebookAdapter(phoneBookList)
                phonebookBinding.recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = mPhoneBookAdapter
                }
            } else {
                mPhoneBookAdapter.submitList(phoneBookList)
            }
        }
        phoneBookPage++
    }

    override fun setPhoneBookPageIsEnd(isEnd: Boolean) {
        this.phoneBookIsEnd = isEnd
    }

    private fun callPhoneBookApi(str: String) {
        when(phonebookBinding.phoneBookTabLayout.selectedTabPosition) {
            0 -> {
                presenter.getPhoneBookInfoByName(str.trim(), phoneBookPage)
            }
            1 -> {
                presenter.getPhoneBookInfoByJob(str.trim(), phoneBookPage)
            }
        }
    }

    private fun initPageInfo() {
        mPhoneBookInfoList.clear()
        phoneBookPage = 0
        phoneBookContentSize = 0
        phoneBookIsEnd = false
    }

    private fun initView() {
        RxTextView.textChanges(phonebookBinding.searchEditText)
            .debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                initPageInfo()
                mPhoneBookAdapter = PhonebookAdapter(mPhoneBookInfoList)
                callPhoneBookApi(it.toString())
            }.addTo(compositeDisposable)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

}
